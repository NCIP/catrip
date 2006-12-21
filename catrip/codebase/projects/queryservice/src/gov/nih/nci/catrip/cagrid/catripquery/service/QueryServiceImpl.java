package gov.nih.nci.catrip.cagrid.catripquery.service;

import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.DCQLAttribute;
import gov.nih.nci.catrip.cagrid.catripquery.DCQLClass;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import org.hibernate.ObjectNotFoundException;

/** 
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class QueryServiceImpl extends QueryServiceImplBase {

    private CatripQuery decomposedCatripQuery = new CatripQuery();
    private final boolean DEBUG = true;

	public QueryServiceImpl() throws RemoteException {
		super();
	}
	
	public void save(gov.nih.nci.catrip.cagrid.catripquery.CaTripQuery caTripQuery) throws RemoteException,  FileNotFoundException, SerializationException {
		//  decompose the DCQL
		populateObjectFromDCQLObject(caTripQuery.getTargetObject(), new DCQLClass());

		// serialize the dcql to be saved in the database
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
		String txt = ObjectSerializer.toString((gov.nih.nci.cagrid.dcql.Object)caTripQuery.getTargetObject(),qname);
		decomposedCatripQuery.setDcqlQuery(txt);
		decomposedCatripQuery.setId((int) caTripQuery.getId());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setFirstName(caTripQuery.getFirstName());
		decomposedCatripQuery.setLastName(caTripQuery.getLastName());
		//decomposedCatripQuery.setCreationDate(caTripQuery.getCreationDate());
		decomposedCatripQuery.setDescription(caTripQuery.getDescription());
		decomposedCatripQuery.setName(caTripQuery.getName());
		decomposedCatripQuery.setSource(caTripQuery.getSource());
		//decomposedCatripQuery.setDateUpdated(caTripQuery.getDateUpdated());
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
		
		// First, remove the object if it exists
		try {
			delete(decomposedCatripQuery.getId());
		} 
		catch (ObjectNotFoundException e) {
			// no object with that ID exists so continue with save.
		}
		decomposedCatripQuery.setId(0);
		// save the decomposed DCQL data
		HibernateUtil.create(decomposedCatripQuery);
	}

	public void delete(long _long) throws RemoteException {
		System.out.println("delete id = >"+ _long +"<");
		if (_long != 0){
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

