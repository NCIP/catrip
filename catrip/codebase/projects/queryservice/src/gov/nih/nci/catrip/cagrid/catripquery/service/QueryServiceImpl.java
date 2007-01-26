package gov.nih.nci.catrip.cagrid.catripquery.service;

import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.io.CharArrayReader;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

/** 
 * 
 * @created by Introduce Toolkit version 1.0
 * 
 */
public class QueryServiceImpl extends QueryServiceImplBase {

    private QueryDb decomposedCatripQuery;
    private final boolean DEBUG = true;
	
	public QueryServiceImpl() throws RemoteException {
		super();
	}
	
	public void save(gov.nih.nci.catrip.cagrid.catripquery.CatripQuery caTripQuery) throws RemoteException {
		decomposedCatripQuery = new QueryDb();
		if (caTripQuery.getId() != 0){
			decomposedCatripQuery = getDbObject(caTripQuery.getId());
		} 
		DCQLQuery dcql = new DCQLQuery();
		try {
			 dcql = convertStringToDCQL(caTripQuery.getDcql());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("server side dcql = " + caTripQuery.getDcql());
		//System.out.println("dcql.getTargetObject() = " + dcql.getTargetObject());
		
		populateObjectFromDCQLObject(dcql.getTargetObject(), new ClassDb());

		// serialize the dcql to be saved in the database
		//QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");

		//decomposedCatripQuery.setDcql(new java.sql.Clob());//caTripQuery.getDcql());
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
		ClassDb classs = new ClassDb();
		classs.setName(dcql.getTargetObject().getName());
		decomposedCatripQuery.addClass(classs);

		if (DEBUG){
			// DEBUG
			System.out.println("\n******* attributes  *******");
			System.out.println("ID is " + decomposedCatripQuery.getId());
			System.out.println("first name : " + decomposedCatripQuery.getFirstName());
			System.out.println("last name : " + decomposedCatripQuery.getLastName());
			System.out.println("date created : " + decomposedCatripQuery.getCreationDate());
			System.out.println("description : " + decomposedCatripQuery.getDescription());
			System.out.println("query name : " + decomposedCatripQuery.getName());
			System.out.println("source : " + decomposedCatripQuery.getSource());
			System.out.println("updated : " + decomposedCatripQuery.getDateUpdated());
			System.out.println("user name : " + decomposedCatripQuery.getUserName());
			System.out.println("instance : " + decomposedCatripQuery.getInstance());
			System.out.println("version : " + decomposedCatripQuery.getVersion());
			System.out.println("dcql : " + decomposedCatripQuery.getDcql());
						System.out.println("update 1 ");

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
		if (dcqlObject.getAttribute() != null) {
			if (DEBUG){
				System.out.println("attribute = " + dcqlObject.getAttribute().getName());
			}
			if (aDCQLClass != null){
				AttributeDb attr = new AttributeDb();
				attr.setName(dcqlObject.getAttribute().getName());
				aDCQLClass.getAttributeCollection().add(attr);
			}
		}
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

		if (dcqlObject.getGroup() != null) {
			if (aDCQLClass != null)
				aDCQLClass.setName(dcqlObject.getName());
			if (dcqlObject.getGroup().getAssociation() != null)
				;// do something
			if (dcqlObject.getGroup().getAttribute() != null){
				Attribute[] attributes = dcqlObject.getGroup().getAttribute();
				if (aDCQLClass != null){
					for (int i = 0; i < attributes.length; i++) {
						AttributeDb attr = new AttributeDb();
						attr.setName(attributes[i].getName());
						aDCQLClass.getAttributeCollection().add(attr);
						if (DEBUG){
							System.out.println("processGroup attributes : " + attributes[i].getName());
						}

					}
					decomposedCatripQuery.addClass(aDCQLClass);

				}
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
		System.out.println("foreignAssociation method");
		populateObjectFromDCQLObject(dcqlObject.getAssociation(), new ClassDb());

		return null;

	}
	private DCQLQuery convertStringToDCQL(String cqlString){
        
        
        StringBuffer buf = new StringBuffer(cqlString);

        char[] chars = new char[buf.length()];
        buf.getChars(0, chars.length, chars, 0);
        
        CharArrayReader car = new CharArrayReader(chars);
        InputSource source = new InputSource(car);
         Object obj = null ;

        try {
            obj = ObjectDeserializer.deserialize(source,DCQLQuery.class);
        } catch (DeserializationException e) {
            e.printStackTrace();
        }
        return ((DCQLQuery)obj);
    }
}

