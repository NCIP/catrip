package gov.nih.nci.cagrid.data.cql.cacore;



import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.ExternalObjects;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.cqlquery.ReturnAttributes;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.MalformedQueryException;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.LazyCQLQueryProcessor;


import gov.nih.nci.cagrid.data.cql.tools.ResultObjectAssembler;

import gov.nih.nci.cagrid.data.cql.tools.ToolUtil;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsUtil;
import gov.nih.nci.common.util.HQLCriteria;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;

import java.io.StringWriter;

import java.io.Writer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import org.globus.wsrf.encoding.ObjectSerializer;

import org.hibernate.Session;

import org.hibernate.proxy.HibernateProxyHelper;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


/**
 *  HQLCoreQueryProcessor
 *  Implementation of CQL against a caCORE data source using HQL queries
 *
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * @author Srini Akkala
 *
 * @created May 2, 2006
 * @version $Id: LocalHQLCoreQueryProcessor.java,v 1.8 2007-02-25 16:54:27 srakkala Exp $
 */
public class LocalHQLCoreQueryProcessor extends LazyCQLQueryProcessor {
	public static final String DEFAULT_LOCALHOST_CACORE_URL = "http://localhost:8080/cacore31/server/HTTPServer";
	public static final String APPLICATION_SERVICE_URL = "appserviceUrl";
        public static final String HIBERNATE_CONFIG_FILE = "hibernateConfigFile";

	private static Logger LOG = Logger.getLogger(LocalHQLCoreQueryProcessor.class);
        private static Map cache = null;

	//private ApplicationService coreService;
	private StringBuffer wsddContents;

	public LocalHQLCoreQueryProcessor() {
		super();
	}


    private InputStream getWsdd() throws Exception {
           
            if (getConfiguredWsddStream() != null) {
                    if (wsddContents == null) {
                            wsddContents = Utils.inputStreamToStringBuffer(getConfiguredWsddStream());
                    }
                    return new ByteArrayInputStream(wsddContents.toString().getBytes());
            } else {
                    return null;
            }

    }

        private CQLQueryResults processResults(CQLQuery cqlQuery,List coreResultsList) 
                    throws MalformedQueryException, QueryProcessingException {
            InputStream configStream = null;
            try {
                    configStream = getWsdd();
            } catch (Exception ex) {
                    throw new QueryProcessingException(ex);
            }
            CQLQueryResults results = null;
            // decide on type of results
            boolean objectResults = cqlQuery.getQueryModifier() == null ||
                    (!cqlQuery.getQueryModifier().isCountOnly()
                            && cqlQuery.getQueryModifier().getAttributeNames() == null
                            && cqlQuery.getQueryModifier().getDistinctAttribute() == null);
            if (objectResults) {
                    results = CQLQueryResultsUtil.createQueryResults(
                            coreResultsList, cqlQuery.getTarget().getName(), configStream);
            } else {
                    QueryModifier mod = cqlQuery.getQueryModifier();
                    if (mod.isCountOnly()) {
                            // parse the value as a string to long.  This covers returning
                            // integers, shorts, and longs
                            Long val = Long.valueOf(coreResultsList.get(0).toString());
                            results = CQLQueryResultsUtil.createCountQueryResults(
                                    val.longValue(), cqlQuery.getTarget().getName());
                    } else {
                            // attributes distinct or otherwise
                            String[] names = null;
                            if (mod.getDistinctAttribute() != null) {
                                    names = new String[] {mod.getDistinctAttribute()};
                            } else {
                                    names = mod.getAttributeNames();
                            }
                            results = CQLQueryResultsUtil.createAttributeQueryResults(
                                    coreResultsList, cqlQuery.getTarget().getName(), names);
                    }
            }
            return results;
        }
	public CQLQueryResults processQuery(CQLQuery cqlQuery)
		throws MalformedQueryException, QueryProcessingException {

		CQLQueryResults results = queryCoreService(cqlQuery);
	    
		return results;
	}


	public Iterator processQueryLazy(CQLQuery cqlQuery)
		throws MalformedQueryException, QueryProcessingException {
		List coreResultsList = null;
		return coreResultsList.iterator();
	}

    private Document loadDocument(String filename){
        Document doc = null;
        try {
            SAXBuilder builder = new SAXBuilder();
           // InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            doc = builder.build(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

	private CQLQueryResults queryCoreService(CQLQuery query)
		throws MalformedQueryException, QueryProcessingException {
	   /*
            try {
	        Writer w = new FileWriter(new File("C:\\tmp\\qry.txt"));        
                javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
	        ObjectSerializer.serialize(w,query,q);        
	        
	        //String qry = w.toString();
                //System.out.println(qry);
	    } catch (Exception e) {
	            LOG.error("Problem in debug printout of CQL query:" + e.getMessage(), e);
	    }              
             */   
                CQLQueryResults results = null;
		String hibernateCfgFile = getConfiguredParameters().getProperty(HIBERNATE_CONFIG_FILE);
		//String hibernateCfgFile ="hibernate.cfg.xml";
                String xpathStr = "/hibernate-configuration/session-factory/property";
                String dialect = "";
                String dataBaseURL="";
                String schemaOrUser="";
                try {
                    XPath xpath = XPath.newInstance(xpathStr);
                     List GraphObjectsNodes = xpath.selectNodes(loadDocument(hibernateCfgFile));
                    for (int i=0;i<GraphObjectsNodes.size();i++){
                        Element objectElement = (Element)GraphObjectsNodes.get(i);
                        String attrValue = objectElement.getAttributeValue("name");
                        if (attrValue.equals("dialect")) {
                            dialect = objectElement.getText();
                        } else if (attrValue.equals("connection.url")) {
                            dataBaseURL = objectElement.getText();
                        } else if (attrValue.equals("connection.username")) {
                            schemaOrUser = objectElement.getText();
                        }
                    }


                } catch (JDOMException e) {
                     e.printStackTrace();
                     throw new QueryProcessingException("Error parsing  : " + hibernateCfgFile + e.getMessage(), e);
                }
                // generate the HQL to perform the query
		String hql = null;
          
                LocalCQL2HQL cql2hql = new LocalCQL2HQL(dialect);

                // convert return attributes in QMs
                 String returnAttrbs[] = null;
                ReturnAttributes ra = query.getTarget().getReturnAttributes();
                 if(ra != null ) {
                     QueryModifier qm = new QueryModifier();
                     returnAttrbs = ra.getReturnAttribute();
                     String returnAttrbsWithId[] = new String[returnAttrbs.length+1];
                     returnAttrbsWithId[0] = "id";
                     for (int i=0; i<returnAttrbs.length;i++) {
                         returnAttrbsWithId[i+1] = returnAttrbs[i];
                     }
                     qm.setAttributeNames(returnAttrbsWithId);
                     query.setQueryModifier(qm);
                 }  

                 
	    // see if the target has subclasses
	    //boolean subclassesDetected = false;
	    boolean subclassesDetected = LocalSubclassCheckCache.hasClassProperty(query.getTarget().getName(),hibernateCfgFile,dataBaseURL,schemaOrUser);

		if (subclassesDetected && ra == null) {
			// simplify the query by removing modifiers
			CQLQuery simpleQuery = new CQLQuery();
			simpleQuery.setTarget(query.getTarget());
			hql = cql2hql.translate(simpleQuery,true);
		} else {
			hql = cql2hql.translate(query, false);
		}
               
		//System.out.println( hql);
		//LOG.debug("Executing HQL:" + hql);
                List targetObjects = null;

		// process the query
		HQLCriteria hqlCriteria = new HQLCriteria(hql);
                Session session = null;

		try {
                    
                    session = HibernateUtil.currentSession(hibernateCfgFile,dataBaseURL,schemaOrUser);
                    System.out.println( hqlCriteria.getHqlString());
                    System.out.println( "Executing main target Object Query .." ) ; 
                    targetObjects = session.createQuery(hqlCriteria.getHqlString()).list();
                } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new QueryProcessingException("Error executing HQL : " + hqlCriteria.getHqlString() + ex.getMessage(), ex);
                }  finally {
                    HibernateUtil.closeSession();
                }
                
                // BUILD OBJECTS 
                try {
                    if (returnAttrbs != null ) {
                        targetObjects = ToolUtil.buildObjcets(targetObjects,returnAttrbs,query.getTarget().getName());
                    } else {
                        Set uniqueTargetObjects = new HashSet(targetObjects);
                        targetObjects = new ArrayList(uniqueTargetObjects);
    
                   }                    
                } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new QueryProcessingException("Error building target Objects  : " + ex.getMessage(), ex);
                }
                query.setQueryModifier(null);
                    

                
                // IF CLIENT ASKS FOR ANT ATTRIBUTES FROM OTHER OBJECTS ...
                // BUILD NECESSARY CQLS ..

                if (cql2hql.isReturnAttributes()) {
                    try {
                        System.out.println( "Building session for sub queries .. " ) ; 
                        session = HibernateUtil.currentSession(hibernateCfgFile,dataBaseURL,schemaOrUser);
              //          ResultObjectAssembler assembler = new ResultObjectAssembler(session,dialect);
                        ResultObjectAssembler assembler = new ResultObjectAssembler(hibernateCfgFile,dataBaseURL,schemaOrUser,dialect);
                        targetObjects = assembler.buildResultObjects(targetObjects,query);
                        HibernateUtil.closeSession();
                        System.out.println("# OF QRYS FIRED -- " + assembler.getQryCount()+1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                         throw new QueryProcessingException("Error building return attributes : " + ex.getMessage(), ex);
                    }  finally {
                        
                        HibernateUtil.closeSession();
                    }
                }

		    
                    ExternalObjects eo = query.getExternalObjects();
                    if (eo != null) {
                        targetObjects.add(eo);
                    }
                    results = processResults(query,targetObjects);
                    
                    
                    

                
		// possibly post-process the query
		if (subclassesDetected && query.getQueryModifier() != null) {
			try {
				targetObjects = applyQueryModifiers(targetObjects, query.getQueryModifier());
			} catch (Exception ex) {
				throw new QueryProcessingException("Error applying query modifiers: " + ex.getMessage(), ex);
			}
		}

		return results;
	}


	private List applyQueryModifiers(List rawObjects, QueryModifier mods) throws Exception {
		List processed = new LinkedList();
		Iterator rawIter = rawObjects.iterator();
		if (mods.getDistinctAttribute() != null) {
			Set distinctValues = new HashSet();
			while (rawIter.hasNext()) {
				Object o = rawIter.next();
				Object value = accessNamedProperty(o, mods.getDistinctAttribute());
				distinctValues.add(value);
			}
			// convert the single objects to object arrays
			Iterator distinctIter = distinctValues.iterator();
			while (distinctIter.hasNext()) {
				processed.add(new Object[] {distinctIter.next()});
			}
		} else if (mods.getAttributeNames() != null) {
			String[] names = mods.getAttributeNames();
			while (rawIter.hasNext()) {
				Object o = rawIter.next();
				Object[] values = new Object[names.length];
				for (int i = 0; i < names.length; i++) {
					values[i] = accessNamedProperty(o, names[i]);
				}
				processed.add(values);
			}
		} else {
			processed = rawObjects;
		}

		if (mods.isCountOnly()) {
			List countList = new ArrayList(1);
			countList.add(new Integer(processed.size()));
			processed = countList;
		}
		return processed;
	}


	private Object accessNamedProperty(Object o, String name) throws Exception {
		Field[] fields = o.getClass().getFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(name)
				&& Modifier.isPublic(fields[i].getModifiers())) {
				return fields[i].get(o);
			}
		}
		// no fields?  check methods for getters
		Method[] methods = o.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.startsWith("get") && methods[i].getParameterTypes().length == 0) {
				// strip off the 'get'
				String fieldName = methodName.substring(3);
				if (fieldName.length() == 1) {
					fieldName = String.valueOf(Character.toLowerCase(fieldName.charAt(0)));
				} else {
					fieldName = String.valueOf(Character.toLowerCase(fieldName.charAt(0)))
						+ fieldName.substring(1);
				}
				if (fieldName.equals(name)) {
					return methods[i].invoke(o, new Object[] {});
				}
			}
		}
		throw new NoSuchFieldException("No field " + name + " found on " + o.getClass().getName());
	}


    public Properties getRequiredParameters() {
            Properties params = new Properties();
            params.setProperty(APPLICATION_SERVICE_URL, DEFAULT_LOCALHOST_CACORE_URL);
            params.setProperty(HIBERNATE_CONFIG_FILE, "hibernate.cfg.xml");
            return params;
            
             
    }
}
