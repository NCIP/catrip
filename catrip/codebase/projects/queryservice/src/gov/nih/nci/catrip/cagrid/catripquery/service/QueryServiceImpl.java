package gov.nih.nci.catrip.cagrid.catripquery.service;

import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.service.HibernateUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.hibernate.ObjectNotFoundException;
import org.xml.sax.InputSource;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class QueryServiceImpl extends QueryServiceImplBase {

    private QueryDb decomposedCatripQuery;
    private final boolean DEBUG = false;
	
	public QueryServiceImpl() throws RemoteException {
		super();
	}
	
	public void save(gov.nih.nci.catrip.cagrid.catripquery.CatripQuery caTripQuery) throws RemoteException {
		decomposedCatripQuery = new QueryDb();
		if (caTripQuery.getId() != 0){
			decomposedCatripQuery = getDbObject(caTripQuery.getId());
		}
		DCQLQuery dcql = new DCQLQuery();
//		try {
//			dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(caTripQuery.getDcql())),DCQLQuery.class);
//		} catch (DeserializationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		populateObjectFromDCQLObject(dcql.getTargetObject(), new ClassDb());
//
//		// serialize the dcql to be saved in the database
//		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");

		decomposedCatripQuery.setDcql(caTripQuery.getDcql());
		decomposedCatripQuery.setId((int) caTripQuery.getId());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setFirstName(caTripQuery.getFirstName());
		decomposedCatripQuery.setLastName(caTripQuery.getLastName());
		//if (caTripQuery.getCreationDate() != null)
		//	decomposedCatripQuery.setCreationDate(caTripQuery.getCreationDate());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setName(caTripQuery.getName());
		decomposedCatripQuery.setSource(caTripQuery.getSource());
		//if (caTripQuery.getDateUpdated() != null)
		//	decomposedCatripQuery.setDateUpdated(caTripQuery.getDateUpdated());
		decomposedCatripQuery.setUserName(caTripQuery.getUserName());
		decomposedCatripQuery.setInstance(caTripQuery.getInstance());
		decomposedCatripQuery.setVersion(caTripQuery.getVersion());

		// add the target object to the list of class names
		//DCQLClass classs = new DCQLClass();
		//classs.setName(caTripQuery.getTargetObject().getName());
		//decomposedCatripQuery.addClass(classs);

		if (DEBUG){
			// DEBUG
			System.out.println("\n******* attributes  *******");
			System.out.println("ID is " + caTripQuery.getId());
			System.out.println("first name : " + caTripQuery.getFirstName());
			System.out.println("last name : " + caTripQuery.getLastName());
			System.out.println("date created : " + caTripQuery.getCreationDate());
			System.out.println("description : " + caTripQuery.getDescription());
			System.out.println("query name : " + caTripQuery.getName());
			System.out.println("source : " + caTripQuery.getSource());
			System.out.println("updated : " + caTripQuery.getDateUpdated());
			System.out.println("user name : " + caTripQuery.getUserName());
			System.out.println("instance : " + caTripQuery.getInstance());
			System.out.println("version : " + caTripQuery.getVersion());
			Collection c = decomposedCatripQuery.getClassCollection();
			System.out.println("****************** ");
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				ClassDb element = (ClassDb) iter.next();
				System.out.println(element.getName());
			}
			System.out.println("******************");
			//  END DEBUG
		}

		// save the decomposed DCQL data
		System.out.println("creating object with id " + decomposedCatripQuery.getId());
		HibernateUtil.create(decomposedCatripQuery);
	}

	private QueryDb getDbObject(long id) {
		QueryDb dbObject;
		dbObject = (QueryDb) HibernateUtil.currentSession().load(QueryDb.class, Long.valueOf(id).intValue());
		// remove all the old classes and attributes as they may have changed
		dbObject.getClassCollection().removeAll(dbObject.getClassCollection());

		return dbObject;
	}

	public void delete(long _long) throws RemoteException {
		if (_long != 0){
			System.out.println("delete id = >"+ _long +"<");
			QueryDb p = (QueryDb) HibernateUtil.currentSession().load(QueryDb.class, Long.valueOf(_long).intValue());
			HibernateUtil.delete(p);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void populateObjectFromDCQLObject(gov.nih.nci.cagrid.dcql.Object dcqlObject, ClassDb aDCQLClass){
		ClassDb aClass;
		if (dcqlObject.getAssociation() != null) {
			if (aDCQLClass != null)
				decomposedCatripQuery.addClass(aDCQLClass);
			aClass = new ClassDb();
			processAssociation(dcqlObject.getAssociation(), aClass);
		}
		if (dcqlObject.getForeignAssociation() != null) {
			if (aDCQLClass != null)
				decomposedCatripQuery.addClass(aDCQLClass);
			aClass = new ClassDb(); 
			processForeignAssociation(dcqlObject.getForeignAssociation(), aClass);
		}

		if (dcqlObject.getAttribute() != null) {
			if (DEBUG){
				System.out.println(dcqlObject.getAttribute().getName());
			}
			if (aDCQLClass != null){
				AttributeDb attr = new AttributeDb();
				attr.setName(dcqlObject.getAttribute().getName());
				aDCQLClass.getAttributeCollection().add(attr);
			}
		}


	}
	
	private gov.nih.nci.cagrid.cqlquery.Association processAssociation(Association dcqlAssociation, ClassDb aClass){

		// create a new CQL Association from the DCQL Association
		gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = new gov.nih.nci.cagrid.cqlquery.Association();
		if (DEBUG){
			System.out.println("processAssociation : " +dcqlAssociation.getName());
		}
		aClass.setName(dcqlAssociation.getName());
		decomposedCatripQuery.addClass(aClass);
		populateObjectFromDCQLObject(dcqlAssociation, aClass);

		return cqlAssociation;
	}
	@SuppressWarnings("unchecked")
	private gov.nih.nci.cagrid.cqlquery.Group processForeignAssociation(ForeignAssociation foreignAssociation, ClassDb aClass) {
		// get Foreign Object
		gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();

		if (DEBUG){
			System.out.println("processForeignAssociation : " + dcqlObject.getName());
		}
		aClass.setName(dcqlObject.getName());
		String foreignAttribute = foreignAssociation.getJoinCondition().getForeignAttributeName();
		AttributeDb attribute = new AttributeDb();
		attribute.setName(foreignAttribute);
		aClass.getAttributeCollection().add(attribute);
		decomposedCatripQuery.addClass(aClass);
		if (DEBUG){
			System.out.println("foreignAttribute = " + foreignAttribute);
		}

		processAssociation(dcqlObject.getAssociation(), new ClassDb());

		return null;

	}

}

