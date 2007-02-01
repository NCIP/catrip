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
		
		 ClassDb targetObject = new ClassDb();
		 targetObject.setName(dcql.getTargetObject().getName());
		populateObjectFromDCQLObject(dcql.getTargetObject(), targetObject);

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
//		ClassDb classs = new ClassDb();
//		classs.setName(dcql.getTargetObject().getName());
//		decomposedCatripQuery.addClass(classs);
		

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
		if (dcqlObject.getAttribute() != null){
			processAttribute(dcqlObject.getAttribute(), aDCQLClass);
			//decomposedCatripQuery.addClass(aDCQLClass);
			add(aDCQLClass);
		}
		else{
			if (dcqlObject.getGroup() != null)
				processGroup(dcqlObject.getGroup(), aDCQLClass);
			if (aDCQLClass != null && aDCQLClass.getName() != null)
				//decomposedCatripQuery.addClass(aDCQLClass);
				add(aDCQLClass);
		}

		
		if (dcqlObject.getAssociation() != null) {
			processAssociation(dcqlObject.getAssociation(), new ClassDb());
			populateObjectFromDCQLObject(dcqlObject.getAssociation(), null);
		}
		if (dcqlObject.getGroup() != null) {
			ClassDb aGroupAssociation = new ClassDb();
			aGroupAssociation.setName(dcqlObject.getName());
			processGroup(dcqlObject.getGroup(),new ClassDb());
		}
		if (dcqlObject.getForeignAssociation() != null) {
			gov.nih.nci.cagrid.dcql.Object o = processForeignAssociation(dcqlObject.getForeignAssociation(), new ClassDb());
			populateObjectFromDCQLObject(o, new ClassDb());
		}
//
//
//		processAttribute(dcqlObject.getAttribute(), aDCQLClass);

	}
	
	private void processAttribute(Attribute dcqlObject, ClassDb aDCQLClass){
		
		if (dcqlObject != null) {
			if (DEBUG){
				System.out.println("processAttribute : " + dcqlObject.getName());
			}
			if (aDCQLClass != null){
				AttributeDb attr = new AttributeDb();
				attr.setName(dcqlObject.getName());
				aDCQLClass.getAttributeCollection().add(attr);
			}
		}
	}
	private void processAttributes(gov.nih.nci.cagrid.dcql.Object dcqlObject, ClassDb aDCQLClass){
		if (dcqlObject.getAttribute() != null){
			processAttribute(dcqlObject.getAttribute(), aDCQLClass);
		}
		else{
			if (dcqlObject.getGroup() != null && dcqlObject.getGroup().getAttribute() != null){
				Attribute[] groupAttributes = dcqlObject.getGroup().getAttribute();
				for (int i = 0; i < groupAttributes.length; i++) {
					System.out.println(" group attrs : " + groupAttributes[i].getName());
					processAttribute(groupAttributes[i], aDCQLClass);
				}
				//processGroup(dcqlObject.getGroup(), aDCQLClass);
			}
		}

//		if (dcqlObject != null) {
//			if (dcqlObject.getG)
//			if (aDCQLClass != null){
//				for (int i = 0; i < dcqlObject.length; i++) {
//					System.out.println(" group attrs : " + dcqlObject[i].getName());
//					processAttribute(dcqlObject[i], aDCQLClass);
//				}
//			}
//		}
	}
	private void processGroup(gov.nih.nci.cagrid.dcql.Group dcqlGroup, ClassDb aClass) {
		Attribute[] groupAttributes = dcqlGroup.getAttribute();
		for (int i = 0; i < groupAttributes.length; i++) {
			System.out.println(" group attrs : " + groupAttributes[i].getName());
			processAttribute(groupAttributes[i], aClass);
		}
		
		// associations
		if (dcqlGroup.getAssociation() != null && dcqlGroup.getAssociation().length > 0) {
			Association dcqlAssociationArray[] = dcqlGroup.getAssociation();
			gov.nih.nci.cagrid.cqlquery.Association[] cqlAssociationArray = new gov.nih.nci.cagrid.cqlquery.Association[dcqlAssociationArray.length];
			for (int i = 0; i < dcqlAssociationArray.length; i++) {
				ClassDb aClassDb = new ClassDb();
				processAssociation(dcqlAssociationArray[i],aClassDb);
				
			}
		}
		// groups
		if (dcqlGroup.getGroup() != null && dcqlGroup.getGroup().length > 0) {
			gov.nih.nci.cagrid.dcql.Group[] dcqlGroupArray = dcqlGroup.getGroup();
			gov.nih.nci.cagrid.cqlquery.Group[] cqlGroupArray = new gov.nih.nci.cagrid.cqlquery.Group[dcqlGroupArray.length];
			for (int i = 0; i < dcqlGroupArray.length; i++) {
				processGroup(dcqlGroupArray[i], new ClassDb());
			}
		}
		
	}
	
	private Association processAssociation(Association dcqlAssociation, ClassDb aClass){

		// create a new CQL Association from the DCQL Association
		if (DEBUG){
			System.out.println("processAssociation : " +dcqlAssociation.getName());
		}
		aClass.setName(dcqlAssociation.getName());
		processAttributes(dcqlAssociation, aClass);
		//decomposedCatripQuery.addClass(aClass);
		add(aClass);
		//populateObjectFromDCQLObject(dcqlAssociation, new ClassDb());

		return dcqlAssociation;
	}
	
	@SuppressWarnings("unchecked")
	private gov.nih.nci.cagrid.dcql.Object processForeignAssociation(ForeignAssociation foreignAssociation, ClassDb aClass) {
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
		//decomposedCatripQuery.addClass(aClass);
		add(aClass);
		if (DEBUG){
			System.out.println("foreignAttribute = " + foreignAttribute);
		}
//		ClassDb aaClass = new ClassDb();
//		aaClass.setName(dcqlObject.getAssociation().getName());
//		processAttribute(dcqlObject.getAssociation().getAttribute(), aaClass);
//		
//
//		decomposedCatripQuery.addClass(aaClass);

		//populateObjectFromDCQLObject(dcqlObject, new ClassDb());

		return dcqlObject;

	}

	private void add(ClassDb aClass) {
		// Do not add duplicates
		if (aClass == null)
			return;
		boolean duplicateFound = false;
		Collection queryCollection = decomposedCatripQuery.getClassCollection();
		if (queryCollection == null)
			decomposedCatripQuery.addClass(aClass);	
		else{
			for (Iterator iter = queryCollection.iterator(); iter.hasNext();) {
				ClassDb element = (ClassDb) iter.next();
				System.out.println("aclass = " + aClass);
				System.out.println("element = " + element);
				if (aClass != null && element != null && element.getName() != null && !(element.getName().trim().equalsIgnoreCase(aClass.getName().trim()))){
					
					duplicateFound = false;
				}
				else{
					duplicateFound = true;
					//System.out.println("Found a duplicate " + aClass.getName());
					break;
				}

			}
			if (!duplicateFound)
				decomposedCatripQuery.addClass(aClass);	
		}
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

