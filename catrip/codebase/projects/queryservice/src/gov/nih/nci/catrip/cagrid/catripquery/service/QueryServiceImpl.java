package gov.nih.nci.catrip.cagrid.catripquery.service;

import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.DCQLAttribute;
import gov.nih.nci.catrip.cagrid.catripquery.DCQLClass;
//import gov.nih.nci.catrip.cagrid.catripquery.DCQLClass;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.ObjectSerializer;
import org.hibernate.ObjectNotFoundException;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class QueryServiceImpl extends QueryServiceImplBase {

    private CatripQuery decomposedCatripQuery;
    private final boolean DEBUG = false;
	
	public QueryServiceImpl() throws RemoteException {
		super();
	}
	
	public void save(gov.nih.nci.catrip.cagrid.catripquery.CaTripQuery caTripQuery) throws RemoteException {
		decomposedCatripQuery = new CatripQuery();
		if (caTripQuery.getId() != 0){
			decomposedCatripQuery = getDbObject(caTripQuery.getId());
		}
		populateObjectFromDCQLObject(caTripQuery.getTargetObject(), new DCQLClass());

		// serialize the dcql to be saved in the database
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
		String txt = "";
		try {
			txt = ObjectSerializer.toString((gov.nih.nci.cagrid.dcql.Object)caTripQuery.getTargetObject(),qname);

		} catch (Exception e) {
			throw new RemoteException();
		}
		//decomposedCatripQuery.copy(caTripQuery);
		decomposedCatripQuery.setDcqlQuery(txt);
		decomposedCatripQuery.setId((int) caTripQuery.getId());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setFirstName(caTripQuery.getFirstName());
		decomposedCatripQuery.setLastName(caTripQuery.getLastName());
		if (caTripQuery.getCreationDate() != null)
			decomposedCatripQuery.setCreationDate(caTripQuery.getCreationDate().getTime());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setName(caTripQuery.getName());
		decomposedCatripQuery.setSource(caTripQuery.getSource());
		if (caTripQuery.getDateUpdated() != null)
			decomposedCatripQuery.setDateUpdated(caTripQuery.getDateUpdated().getTime());
		decomposedCatripQuery.setUserName(caTripQuery.getUserName());
		decomposedCatripQuery.setInstance(caTripQuery.getInstance());
		decomposedCatripQuery.setVersion(caTripQuery.getVersion());

		// add the target object to the list of class names
		DCQLClass classs = new DCQLClass();
		classs.setName(caTripQuery.getTargetObject().getName());
		decomposedCatripQuery.addClass(classs);

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
				DCQLClass element = (DCQLClass) iter.next();
				System.out.println(element.getName());
			}
			System.out.println("******************");
			//  END DEBUG
		}

		// save the decomposed DCQL data
		System.out.println("creating object with id " + decomposedCatripQuery.getId());
		HibernateUtil.create(decomposedCatripQuery);
	}

	private CatripQuery getDbObject(long id) {
		CatripQuery dbObject;
		dbObject = (CatripQuery) HibernateUtil.currentSession().load(CatripQuery.class, Long.valueOf(id).intValue());
		// remove all the old classes and attributes as they may have changed
		dbObject.getClassCollection().removeAll(dbObject.getClassCollection());

		return dbObject;
	}

	public void delete(long _long) throws RemoteException {
		if (_long != 0){
			System.out.println("delete id = >"+ _long +"<");
			CatripQuery p = (CatripQuery) HibernateUtil.currentSession().load(CatripQuery.class, Long.valueOf(_long).intValue());
			HibernateUtil.delete(p);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void populateObjectFromDCQLObject(gov.nih.nci.cagrid.dcql.Object dcqlObject, DCQLClass aDCQLClass){
		DCQLClass aClass;
		if (dcqlObject.getAssociation() != null) {
			if (aDCQLClass != null)
				decomposedCatripQuery.addClass(aDCQLClass);
			aClass = new DCQLClass();
			processAssociation(dcqlObject.getAssociation(), aClass);
		}
		if (dcqlObject.getForeignAssociation() != null) {
			if (aDCQLClass != null)
				decomposedCatripQuery.addClass(aDCQLClass);
			aClass = new DCQLClass();
			processForeignAssociation(dcqlObject.getForeignAssociation(), aClass);
		}

		if (dcqlObject.getAttribute() != null) {
			if (DEBUG){
				System.out.println(dcqlObject.getAttribute().getName());
			}
			if (aDCQLClass != null){
				DCQLAttribute attr = new DCQLAttribute();
				attr.setName(dcqlObject.getAttribute().getName());
				aDCQLClass.getAttributeCollection().add(attr);
			}
		}


	}
	
	private gov.nih.nci.cagrid.cqlquery.Association processAssociation(Association dcqlAssociation, DCQLClass aClass){

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
	private gov.nih.nci.cagrid.cqlquery.Group processForeignAssociation(ForeignAssociation foreignAssociation, DCQLClass aClass) {
		// get Foreign Object
		gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();

		if (DEBUG){
			System.out.println("processForeignAssociation : " + dcqlObject.getName());
		}
		aClass.setName(dcqlObject.getName());
		String foreignAttribute = foreignAssociation.getJoinCondition().getForeignAttributeName();
		DCQLAttribute attribute = new DCQLAttribute();
		attribute.setName(foreignAttribute);
		aClass.getAttributeCollection().add(attribute);
		decomposedCatripQuery.addClass(aClass);
		if (DEBUG){
			System.out.println("foreignAttribute = " + foreignAttribute);
		}

		processAssociation(dcqlObject.getAssociation(), new DCQLClass());

		return null;

	}

}

