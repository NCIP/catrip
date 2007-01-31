package gov.nih.nci.cagrid.fqp.processor;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.cqlresultset.CQLAttributeResult;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.cqlresultset.TargetAttribute;
import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.cagrid.dcql.Group;
import gov.nih.nci.cagrid.dcql.JoinCondition;
import gov.nih.nci.cagrid.dcql.Object;
import gov.nih.nci.cagrid.fqp.processor.exceptions.FederatedQueryProcessingException;
import gov.nih.nci.cagrid.fqp.processor.exceptions.RemoteDataServiceException;

import gov.nih.nci.cagrid.fqp.tools.CQLScanner;
import gov.nih.nci.cagrid.fqp.tools.DataGroup;
import gov.nih.nci.cagrid.fqp.tools.ResultsParser;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import org.apache.axis.message.MessageElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;

import org.xml.sax.InputSource;


/**
 * FederatedQueryProcessor decomposes the DCQL into individual CQLs. Each
 * individual CQL is executed by specified grid service in serviceURL by the DataServiceQueryExecutor.
 *
 * @author Srini Akkala
 * @author Scott Oster
 */
class FederatedQueryProcessor {
	protected static Log LOG = LogFactory.getLog(FederatedQueryProcessor.class.getName());
        private Map objectsFromFA = new HashMap();

        public Map getObjectsFromFA(){
            return objectsFromFA;
        }
        
	public FederatedQueryProcessor() {
	}



	/**
	 * Process root element DCQLQuery Element
	 * 
	 * @param dcqlQryPlan
	 * @return
	 * @throws FederatedQueryProcessingException
	 */
	public CQLQuery processDCQLQuery(Object targetObject) throws FederatedQueryProcessingException {
		CQLQuery cqlQuery = new CQLQuery();
	    
		// initialize CQLObject .all the nested Queries would get resolved and
		// attached to this CQL object .
		gov.nih.nci.cagrid.cqlquery.Object cqlObject = new gov.nih.nci.cagrid.cqlquery.Object();
		cqlObject.setName(targetObject.getName());
                
                cqlObject.setReturnAttributes(targetObject.getReturnAttributes());

		// process the DCQL object, building up the CQL object
		populateObjectFromDCQLObject(targetObject, cqlObject);
		// this CQL Object is our target
		cqlQuery.setTarget(cqlObject);
                
                //QueryModifier qm = new QueryModifier();
                //qm.setAssociations(true);
                //cqlQuery.setQueryModifier(qm);
	    
        try {
        
         Writer w = new StringWriter();
	    
	    javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
	    
	    ObjectSerializer.serialize(w,cqlQuery,q);
	//    System.out.println(w);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }              
		return cqlQuery;
	}


	/**
	 * Recursively processes the given DCQLObject, building up the given
	 * cqlObject
	 * 
	 * @param dcqlObject
	 * @param cqlObject
	 * @throws FederatedQueryProcessingException
	 */

	private void populateObjectFromDCQLObject(Object dcqlObject, gov.nih.nci.cagrid.cqlquery.Object cqlObject)
		throws FederatedQueryProcessingException {
		// check for any attribute (PASS THRU)
		if (dcqlObject.getAttribute() != null) {
			cqlObject.setAttribute(dcqlObject.getAttribute());
		}
                if (dcqlObject.getReturnAttributes() != null) {
                    cqlObject.setReturnAttributes(dcqlObject.getReturnAttributes());
                }
                
		// check for group
		if (dcqlObject.getGroup() != null) {
			// convert group and attach group to CQL object
			gov.nih.nci.cagrid.cqlquery.Group cqlGroup = processGroup(dcqlObject.getGroup());
			cqlObject.setGroup(cqlGroup);
		}

		// check for Association
		if (dcqlObject.getAssociation() != null) {
			// Convert into CQL Associoation
			gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = processAssociation(dcqlObject.getAssociation());
			cqlObject.setAssociation(cqlAssociation);
		}

		// check for ForeignAssociation
		if (dcqlObject.getForeignAssociation() != null) {
			gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation(dcqlObject
				.getForeignAssociation());
			cqlObject.setGroup(resultedGroup);
		}
	}
 

	/**
	 * Process Group, which builds CQL Group. DCQL Groups are processed exactly
	 * as DCQL Object, except Groups can contain multiple children predicates,
	 * so an array of each must be processed.
	 * 
	 * @param dcqlGroup
	 * @return
	 * @throws FederatedQueryProcessingException
	 */
	private gov.nih.nci.cagrid.cqlquery.Group processGroup(Group dcqlGroup) throws FederatedQueryProcessingException {
		// convert basic group information and attach group to CQL object
		gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();
		// attach logical relationship
		cqlGroup.setLogicRelation(gov.nih.nci.cagrid.cqlquery.LogicalOperator.fromValue(dcqlGroup.getLogicRelation()
			.toString()));

		// attributes (PASS THRU)
		if (dcqlGroup.getAttribute() != null) {
			cqlGroup.setAttribute(dcqlGroup.getAttribute());
		}

		// associations
		if (dcqlGroup.getAssociation() != null && dcqlGroup.getAssociation().length > 0) {
			Association dcqlAssociationArray[] = dcqlGroup.getAssociation();
			gov.nih.nci.cagrid.cqlquery.Association[] cqlAssociationArray = new gov.nih.nci.cagrid.cqlquery.Association[dcqlAssociationArray.length];
			for (int i = 0; i < dcqlAssociationArray.length; i++) {
				cqlAssociationArray[i] = processAssociation(dcqlAssociationArray[i]);
			}
			cqlGroup.setAssociation(cqlAssociationArray);
		}

		// groups
		if (dcqlGroup.getGroup() != null && dcqlGroup.getGroup().length > 0) {
			Group dcqlGroupArray[] = dcqlGroup.getGroup();
			gov.nih.nci.cagrid.cqlquery.Group[] cqlGroupArray = new gov.nih.nci.cagrid.cqlquery.Group[dcqlGroupArray.length];
			for (int i = 0; i < dcqlGroupArray.length; i++) {
				gov.nih.nci.cagrid.cqlquery.Group cqlNestedGroup = processGroup(dcqlGroupArray[i]);
				cqlGroupArray[i] = cqlNestedGroup;
			}
			cqlGroup.setGroup(cqlGroupArray);
		}

		// foreign associations
		if (dcqlGroup.getForeignAssociation() != null && dcqlGroup.getForeignAssociation().length > 0) {
			ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociation();
			gov.nih.nci.cagrid.cqlquery.Group[] cqlGroupArray = new gov.nih.nci.cagrid.cqlquery.Group[foreignAssociationArray.length];
			for (int i = 0; i < foreignAssociationArray.length; i++) {
				// need to attach the results as crieteria ...
				gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation(foreignAssociationArray[i]);
				cqlGroupArray[i] = resultedGroup;
			}
			// merge in these groups with any that already exist (from group
			// processing above)
			cqlGroup.setGroup((gov.nih.nci.cagrid.cqlquery.Group[]) Utils.concatenateArrays(
				gov.nih.nci.cagrid.cqlquery.Group.class, cqlGroup.getGroup(), cqlGroupArray));
		}

		return cqlGroup;

	}


	/**
	 * Process Association convert DCQL Association into CQL Association.
	 * 
	 * @param dcqlAssociation
	 * @return
	 * @throws QueryExecutionException
	 */
	private gov.nih.nci.cagrid.cqlquery.Association processAssociation(Association dcqlAssociation)
		throws FederatedQueryProcessingException {

		// create a new CQL Association from the DCQL Association
		gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = new gov.nih.nci.cagrid.cqlquery.Association();
		cqlAssociation.setRoleName(dcqlAssociation.getRoleName());
		cqlAssociation.setName(dcqlAssociation.getName());
                //phase2
                cqlAssociation.setReturnAttributes(dcqlAssociation.getReturnAttributes());
		// process the association's Object
		populateObjectFromDCQLObject(dcqlAssociation, cqlAssociation);

		return cqlAssociation;
	}


	/**
	 * process ForeignAssociation, which is basically a Query that can be
	 * executed by a grid service mentioned in serviceURL attribute As
	 * ForeignAssocitaion itself is a DCQL Object , the Object is processed and
	 * CQL Query would be passed to DataServiceQueryExecutor obtained results
	 * from services are then aggreated.
	 * 
	 * @param foreignAssociation
	 * @return
	 * @throws FederatedQueryProcessingException
	 */
	private gov.nih.nci.cagrid.cqlquery.Group processForeignAssociation(ForeignAssociation foreignAssociation)
		throws FederatedQueryProcessingException {
		// get Foreign Object
		Object dcqlObject = foreignAssociation.getForeignObject();

		// make a new query with the CQL Object created by processing the
		// foreign association
		CQLQuery cqlQuery = new CQLQuery();
		gov.nih.nci.cagrid.cqlquery.Object cqlObject = new gov.nih.nci.cagrid.cqlquery.Object();
		cqlObject.setName(dcqlObject.getName());
		populateObjectFromDCQLObject(dcqlObject, cqlObject);
		cqlQuery.setTarget(cqlObject);

		// build up a query result modifier to only return distinct values of
		// the attribute we need
		String foreignAttribute = foreignAssociation.getJoinCondition().getForeignAttributeName();
		
                /* phase2 just returning attribute is not enough as we need 
                 * associated objects also . Without the owner column associated objects doesnot exist
                 * so getting full object insted on attribute , 
                 */
                 /*
                QueryModifier queryModifier = new QueryModifier();
		queryModifier.setDistinctAttribute(foreignAttribute);
		cqlQuery.setQueryModifier(queryModifier);
                */
                
		// Execute Foreign Query .....
		String targetServiceURL = foreignAssociation.getTargetServiceURL();
		CQLQueryResults cqlResults = DataServiceQueryExecutor.queryDataService(cqlQuery, targetServiceURL);
                
                //phase2 added code to get full object and parse it to get the CDE value 
                List remoteAttributeValues = new ArrayList();
	        
                boolean populateMap = false;
                if (CQLScanner.isAttributesRequested(cqlQuery)) {
                    populateMap = true;
                }
                
                if (cqlResults != null ) {
                    
                    CQLObjectResult[] objectResult = cqlResults.getObjectResult();
                    for (int i = 0; i < objectResult.length; i++) {
                            CQLObjectResult objResult = objectResult[i];
                            MessageElement msgsElement = objResult.get_any()[0];                            
                       //     System.out.println(msgsElement);
                            String cde = msgsElement.getAttributeValue(foreignAttribute).trim();

                            
                            ResultsParser rParser = new ResultsParser(cqlQuery);
                            if (populateMap) {
                                //objectsFromFA.put(cde,rParser.getResultMap(msgsElement));
                                 List l = rParser.convertMessageElementToListOfMaps(msgsElement);
                                 List l1 = new ArrayList();
                                 for (int j=0;j<l.size();j++){
                                     DataGroup dg = (DataGroup)l.get(j);
                                     List list = dg.getDataRows();
                                     Iterator listItr = list.iterator();
                                     while (listItr.hasNext()) {
                                         Map resultMap = (Map)listItr.next();
                                         l1.add(resultMap);
                                     }
                                 }
                                 
                                 objectsFromFA.put(cde,l1);//rParser.convertMessageElementToListOfMaps(msgsElement));
                            }

                           remoteAttributeValues.add(cde);
                            
                            //if (msgsElement.getChildNodes().getLength() > 0 ) {
                            //    objectsFromFA.put(cde,msgsElement.getChildNodes());
                            //}
                    }
                }

	    /* phase2 just returning attribute is not enough as we need 
	     * associated objects also . Without the owner column associated objects doesnot exist
	     * so getting full object insted on attribute , 
	     */
/*
		// process the resulting values
		List remoteAttributeValues = new ArrayList();
		if (cqlResults != null && cqlResults.getAttributeResult() != null) {
			CQLAttributeResult[] attributeResult = cqlResults.getAttributeResult();
			for (int i = 0; i < attributeResult.length; i++) {
				CQLAttributeResult attResult = attributeResult[i];
				TargetAttribute[] attribute = attResult.getAttribute();
				// make sure there is a valid result of only the specific
				// attribute we asked for
				if (attribute == null || attribute.length != 1 || !attribute[0].getName().equals(foreignAttribute)) {
					throw new RemoteDataServiceException("Data Service (" + targetServiceURL
						+ ") returned an invalid attribute result.");
				}
				remoteAttributeValues.add(attribute[0].getValue());
			}
			// process the array
		} else {
			// make sure there are NO RESULTS (of other types), and raise an
			// error if there are
			if (hasResults(cqlResults)) {
				throw new RemoteDataServiceException("Data Service (" + targetServiceURL
					+ ") returned invalid results when queried for Attributes.");
			}
		}
*/
		gov.nih.nci.cagrid.cqlquery.Group criteriaGroup = buildGroup(foreignAssociation.getJoinCondition(),
			remoteAttributeValues);
		return criteriaGroup;
	}
        


	/**
	 * Build group of Attributes based on the List generated by processResults
	 * method. Attribute name would be left join CDE Attribute value would be
	 * value from the list generated by processResults method predicate is
	 * "EQUAL_TO" Group with logical operator "OR". If no results are found, a
	 * group that can never be true is created (currently IS_NULL AND
	 * IS_NOT_NULL); ideally a "false" predicate would be created, but we have
	 * no such construct in CQL.
	 * 
	 * @param list
	 * @return
	 */
	public static gov.nih.nci.cagrid.cqlquery.Group buildGroup(JoinCondition joinCondition, List list) {
		gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();
		String property = joinCondition.getLocalAttributeName();

		// if the size of result set returned by sub query is zero we got no
		// results, and this group should never evaluate to true
		// add an impossible IS_NULL AND IS_NOT_NULL
		// this needs to be done because the client asked for a predicate that
		// evaluated to false (no remote results, so no join can be true), so we
		// need to propegate this evaluation (not just ommit it).
		if (list.size() == 0) {
			cqlGroup.setLogicRelation(LogicalOperator.AND);
			gov.nih.nci.cagrid.cqlquery.Attribute[] attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[2];

			gov.nih.nci.cagrid.cqlquery.Attribute attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
			attr.setName(property);
			attr.setPredicate(Predicate.IS_NULL);
			attrArray[0] = attr;

			attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
			attr.setName(property);
			attr.setPredicate(Predicate.IS_NOT_NULL);
			attrArray[1] = attr;
			// attach the created attribute array
			cqlGroup.setAttribute(attrArray);
		} else if (list.size() == 1) {
			// create the property and apply twice (because a group needs two
			// entries and we only got one value).
			cqlGroup.setLogicRelation(LogicalOperator.OR);
			gov.nih.nci.cagrid.cqlquery.Attribute[] attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[2];
			gov.nih.nci.cagrid.cqlquery.Attribute attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
			attr.setName(property);
			attr.setPredicate(Predicate.fromValue(joinCondition.getPredicate().getValue()));
			attr.setValue(list.get(0).toString());
			attrArray[0] = attr;
			attrArray[1] = attr;
			// attach the created attribute array
			cqlGroup.setAttribute(attrArray);

		} else {
			gov.nih.nci.cagrid.cqlquery.Attribute[] attrArray = new gov.nih.nci.cagrid.cqlquery.Attribute[list.size()];
			cqlGroup.setLogicRelation(LogicalOperator.OR);
			for (int i = 0; i < list.size(); i++) {
				gov.nih.nci.cagrid.cqlquery.Attribute attr = new gov.nih.nci.cagrid.cqlquery.Attribute();
				// set the local property name
				attr.setName(property);
				// set the predicate to the join predicate (this requires DCQL
				// to use the same representation as CQL)
				attr.setPredicate(Predicate.fromValue(joinCondition.getPredicate().getValue()));
				// set the value to the string representation of the "foreign
				// result value"
				attr.setValue(list.get(i).toString());
				attrArray[i] = attr;
			}
			// attach the created attribute array
			cqlGroup.setAttribute(attrArray);
		}

		return cqlGroup;
	}


	/**
	 * Returns true iff the passed result is not null AND contains some type of
	 * result data.
	 * 
	 * @param cqlResults
	 * @return true iff the passed result is not null AND contains some type of
	 *         result data.
	 */
	private static boolean hasResults(CQLQueryResults cqlResults) {
		return cqlResults != null
			&& (cqlResults.getAttributeResult() != null && cqlResults.getCountResult() != null
				|| cqlResults.getIdentifierResult() != null || cqlResults.getObjectResult() != null);
	}

}