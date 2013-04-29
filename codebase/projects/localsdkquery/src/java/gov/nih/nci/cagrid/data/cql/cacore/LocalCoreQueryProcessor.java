/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.cacore;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.MalformedQueryException;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.LazyCQLQueryProcessor;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsUtil;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.cagrid.data.InitializationException;

import gov.nih.nci.cagrid.data.cql.cacore.experimental.CQL2DetachedCriteria;



import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import java.util.Set;

import org.apache.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

/**
 *  HQLCoreQueryProcessor
 *  Implementation of CQL against a caCORE data source using HQL queries
 *
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 *
 * @created May 2, 2006
 * @version $Id: LocalCoreQueryProcessor.java,v 1.2 2006-11-16 20:49:38 srakkala Exp $
 */
public class LocalCoreQueryProcessor extends LazyCQLQueryProcessor {
	public static final String DEFAULT_LOCALHOST_CACORE_URL = "http://localhost:8080/cacore31/server/HTTPServer";
	public static final String APPLICATION_SERVICE_URL = "appserviceUrl";
        public static final String HIBERNATE_CONFIG_FILE = "hibernateConfigFile";
	
	private static Logger LOG = Logger.getLogger(LocalCoreQueryProcessor.class);

	private StringBuffer wsddContents; 
	
	public LocalCoreQueryProcessor() {
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
	

	public CQLQueryResults processQuery(CQLQuery cqlQuery) 
		throws MalformedQueryException, QueryProcessingException {
		InputStream configStream = null;
		try {
			configStream = getWsdd();
		} catch (Exception ex) {
			throw new QueryProcessingException(ex);
		}
		List coreResultsList = queryCoreService(cqlQuery);
		CQLQueryResults results = null;
		// decide on type of results
		boolean objectResults = cqlQuery.getQueryModifier() == null ||
			(!cqlQuery.getQueryModifier().isCountOnly() 
				&& cqlQuery.getQueryModifier().getAttributeNames() == null 
				&& cqlQuery.getQueryModifier().getDistinctAttribute() == null);
		if (objectResults) {
			System.out.println("FORMING results");
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
                System.out.println("RETURNING  results");
		return results;
	}
	
	
	public Iterator processQueryLazy(CQLQuery cqlQuery) 
		throws MalformedQueryException, QueryProcessingException {
		List coreResultsList = queryCoreService(cqlQuery);
		return coreResultsList.iterator();
	}
	
	private List queryCoreService(CQLQuery query) 
		throws MalformedQueryException, QueryProcessingException {
                /*
                Properties p = getConfiguredParameters();
                Enumeration e = p.keys();
	    
                while (e.hasMoreElements()){
                    System.out.println(e.nextElement().toString());
                }
                */
                //String hibernateCfgFile = getConfiguredParameters().getProperty(HIBERNATE_CONFIG_FILE);
                
                String hibernateCfgFile = "hibernate.cfg.xml";
                System.out.println("hibernateCfgFile : " + hibernateCfgFile);
		
                DetachedCriteria objectCriteria = CQL2DetachedCriteria.translate(query);
                
                //HQLCriteria hqlCriteria = new HQLCriteria(CQL2HQL.translate(query));
               // Session session = HibernateUtil.currentSession(hibernateCfgFile);
                Session session = null;
                Criteria hqlCriteria = objectCriteria.getExecutableCriteria(session);
                
            
            
		//System.out.println("Executing LOCAL HQL: " + hqlCriteria.getHqlString());
		//LOG.debug("Executing HQL:" + hqlCriteria.getHqlString());
		List targetObjects = null;
		try {
                        targetObjects = hqlCriteria.list();
		} catch (Exception ex) {
			throw new QueryProcessingException("Error invoking core query method: " + ex.getMessage(), ex);
		} finally {
                    HibernateUtil.closeSession();
                }
                
                System.out.println("Closing Session " + targetObjects.size());
	        HibernateUtil.closeSession();
                
		return targetObjects;
	}
	
	public Properties getRequiredParameters() {
		Properties params = new Properties();
                params.setProperty(APPLICATION_SERVICE_URL, DEFAULT_LOCALHOST_CACORE_URL);
                params.setProperty(HIBERNATE_CONFIG_FILE, "hibernate.cfg.xml");
		return params;
	}
}
