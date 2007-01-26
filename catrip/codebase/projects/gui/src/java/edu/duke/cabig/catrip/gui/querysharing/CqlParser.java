package edu.duke.cabig.catrip.gui.querysharing;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Group;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

public class CqlParser {
	private static QueryDb catripQuery;
	
	public CqlParser(QueryDb queryDb){
		catripQuery = queryDb;
	}
	
	public static CQLQuery parse(QueryDb queryData) {
		//gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		//target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
		CQLQuery cqlQuery2 = null;
		catripQuery = queryData;
		Attribute[] classAssociations = createClassAssociations();
		Attribute[] attributeAssociations = createAttributeAssociations();
		
		// no class or attributes were choosen
		if (classAssociations.length == 0 && attributeAssociations.length == 0){
			cqlQuery2 = createClassAttributeQuery(getClassAttributes());
		}
		// classes but no attributes choosen
		if (classAssociations.length != 0 && attributeAssociations.length == 0){
			 cqlQuery2 = createClassQuery(getClassAttributes(), classAssociations);
		}
		// Both classes and attributes are choosen
		if (classAssociations.length != 0 && attributeAssociations.length != 0){
			 cqlQuery2 = createClassAttributeQuery(getClassAttributes(), classAssociations, attributeAssociations);
		}
		printCQL(cqlQuery2);
		return cqlQuery2;
	}

	private static CQLQuery createClassAttributeQuery(Attribute[] cqlAttributeArray, Attribute[] classArray, Attribute[] attributeArray) {
		gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
		CQLQuery cqlQuery = new CQLQuery();
		Association classAssoc = null;
		Group outerGroup = new Group();
		outerGroup.setLogicRelation(LogicalOperator.OR);
		
		Group superOuterGroup = new Group();
		superOuterGroup.setLogicRelation(LogicalOperator.AND);
		Group attributeGroup = new Group();
		attributeGroup.setLogicRelation(LogicalOperator.AND);
		
		// classes
		 classAssoc = new Association();
		classAssoc.setName("gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb");
		classAssoc.setRoleName("classCollection");
		classAssoc.setGroup(outerGroup);
		if (classArray.length >1){
			// create a group for the attributes
			Group classGroup = new Group();
			classGroup.setLogicRelation(LogicalOperator.OR);
			// add the attributes to the group
			classGroup.setAttribute(classArray);
			//classAssoc.setGroup(classGroup);
			Group[] groupArray = {classGroup};
			outerGroup.setGroup(groupArray);
		}
		if (classArray.length == 1){
			outerGroup.setAttribute(classArray);
		}
		// end classes
		//Association[] classAssocArray = {classAssoc};
		
		
		Association[] attribAssoc = {getAttributeAssociation(attributeArray)};
		
		outerGroup.setAssociation(attribAssoc);
		// deal with attributes on QueryDb
		if (cqlAttributeArray.length > 1){
			// create a group for the attributes
			Group attributeGroup2 = new Group();
			attributeGroup2.setLogicRelation(LogicalOperator.AND);
			// add the attributes to the group
			attributeGroup2.setAttribute(cqlAttributeArray);
			Group[] g = {attributeGroup2};
			//g[0] = attributeGroup;
			// set the group on outer group
			//target.setGroup(attributeGroup2);
			superOuterGroup.setGroup(g);
		}
		// if only one attribute; don't need the group
		if (cqlAttributeArray.length == 1)
			superOuterGroup.setAttribute(cqlAttributeArray);
		Association[] assArray = {classAssoc};
		superOuterGroup.setAssociation(assArray);
		target.setGroup(superOuterGroup);
		cqlQuery.setTarget(target);	
		System.out.println("printing from createClassQuery");
		printCQL(cqlQuery);
		
		return cqlQuery;		
	}

	private static Attribute[] getClassAttributes() {
		int index = 0;
		gov.nih.nci.cagrid.cqlquery.Attribute[] mainAttributeArray = new gov.nih.nci.cagrid.cqlquery.Attribute[8];
		if (catripQuery.getName() != null && !catripQuery.getName().trim().equals(""))
			mainAttributeArray[index++] = new Attribute("name",Predicate.LIKE,"%"+catripQuery.getName()+"%");
		if (catripQuery.getDescription() != null && !catripQuery.getDescription().trim().equals(""))
			mainAttributeArray[index++] = new Attribute("description",Predicate.LIKE,"%"+catripQuery.getDescription()+"%");
		if (catripQuery.getFirstName() != null  && !catripQuery.getFirstName().trim().equals(""))
			mainAttributeArray[index++] = new Attribute("firstName",Predicate.LIKE,"%"+catripQuery.getFirstName()+"%");
		if (catripQuery.getLastName() != null  && !catripQuery.getLastName().trim().equals(""))
			mainAttributeArray[index++] = new Attribute("lastName",Predicate.LIKE,"%"+catripQuery.getLastName()+"%");

		// Clear out any empty array slots
		gov.nih.nci.cagrid.cqlquery.Attribute[] cqlAttributeArray = new gov.nih.nci.cagrid.cqlquery.Attribute[index];
		for (int i = 0; i < index; i++) {
			cqlAttributeArray[i] = mainAttributeArray[i];
			//System.out.println("looped");
		}
		return cqlAttributeArray;
	}
	
	private static Attribute[] createClassAssociations(){
		// Create any class associations
		Association classAssoc = null;
		Group assocGroup = new Group();
		assocGroup.setLogicRelation(LogicalOperator.OR);
		Group[] assocGroupArray = new Group[1];
		Collection collection = catripQuery.getClassCollection();
		gov.nih.nci.cagrid.cqlquery.Attribute[] classArray = new gov.nih.nci.cagrid.cqlquery.Attribute[collection.size()];
		int classCounter = 0;
		if (collection.size() > 0){
			// if there are class attributes then create the ClassDb Associations
			classAssoc = new Association();
			classAssoc.setName("gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb");
			classAssoc.setRoleName("classCollection");
			for (Iterator iter = collection.iterator(); iter.hasNext();) {
				ClassDb element = (ClassDb) iter.next();
				if (element.getName() != null && element.getName().trim() != ""){
					classArray[classCounter++] = new Attribute("name",Predicate.EQUAL_TO,element.getName());
					System.out.println("class array size : " + classArray.length + " element added : " + element.getName());
				}
			}

			assocGroup.setAttribute(classArray);
			assocGroupArray[0] = assocGroup;
		}
		return classArray;
	}
	
	private static Attribute[] createAttributeAssociations(){
		// Create any Attribute associations
		Association attribAssoc = null;
		Group attribGroup = new Group();
		attribGroup.setLogicRelation(LogicalOperator.OR);
		//Group[] attribGroupArray = new Group[1];
		Association[] attribAttributeArray = new Association[1];
		Collection attribCollection = getAttributes(catripQuery.getClassCollection());
		gov.nih.nci.cagrid.cqlquery.Attribute[] attribArray = new gov.nih.nci.cagrid.cqlquery.Attribute[attribCollection.size()];
		int attribCounter = 0;
		if (attribCollection.size() > 0){
			// if there are class attributes then create the AttributeDb Associations
			attribAssoc = new Association();
			attribAssoc.setRoleName("attributeCollection");
			attribAssoc.setName("gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb");
			for (Iterator iter = attribCollection.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				attribArray[attribCounter++] = new Attribute("name",Predicate.EQUAL_TO,element);
				System.out.println("attribute array size : " + attribArray.length + " element added : " + element);
			}

			//attribGroupArray[0] = attribGroup;
			if (attribArray.length == 1){
				// don't need the group
				attribAssoc.setAttribute(attribArray[0]);
				attribAttributeArray[0] = attribAssoc;
				// TODO can't get this to work -- stack overflow
				//attributesAndAssociationsGroup.setAssociation(attribAttributeArray);
				//attributesAndAssociationsGroup.setGroup(assocGroupArray);  // temp
			}
			else{
				attribGroup.setAttribute(attribArray);
				attribAssoc.setGroup(attribGroup);
				// for later use
				attribAttributeArray[0] = attribAssoc;
				//attributesAndAssociationsGroup.setAssociation(attribAttributeArray);
			}
		}
		return attribArray;
	}
	
	private static Collection getAttributes(Collection<ClassDb> classCollection) {
		Collection<String> c = new Vector<String>();
		for (Iterator iter = classCollection.iterator(); iter.hasNext();) {
			ClassDb element = (ClassDb) iter.next();
			for (Iterator iterator = element.getAttributeCollection().iterator(); iterator.hasNext();) {
				AttributeDb attribute = (AttributeDb) iterator.next();
				if (attribute.getName() != null && attribute.getName().trim() != "") 
					c.add(attribute.getName());
			}
		}
		//DEBUG START
		//if (c.size() == 0){
		//	c.add("medicalRecordNumber");
		//	c.add("totalScore");
		//}
		// DEBUG END
		return c;
	}
	private static CQLQuery createClassAttributeQuery(Attribute[] cqlAttributeArray) {
		gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
		CQLQuery cqlQuery = new CQLQuery();
		
		if (cqlAttributeArray.length > 1){
			// create a group for the attributes
			Group attributeGroup = new Group();
			attributeGroup.setLogicRelation(LogicalOperator.AND);
			// add the attributes to the group
			attributeGroup.setAttribute(cqlAttributeArray);
			Group[] g = new Group[1];
			g[0] = attributeGroup;
			// set the group on the target
			target.setGroup(attributeGroup);
		}
		// if only one attribute; don't need the group
		if (cqlAttributeArray.length == 1)
			target.setAttribute(cqlAttributeArray[0]);
		
		cqlQuery.setTarget(target);	
		System.out.println("printing from createClassAttributeQuery");
		printCQL(cqlQuery);
		
		return cqlQuery;
	}
	private static CQLQuery createClassQuery(Attribute[] cqlAttributeArray, Attribute[] classArray) {
		gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
		CQLQuery cqlQuery = new CQLQuery();
		Association classAssoc = getClassAssociation(classArray);
		Group classAndAttributeGroup = new Group();
		classAndAttributeGroup.setLogicRelation(LogicalOperator.AND);
		Association[] classAssocArray = {classAssoc};
		classAndAttributeGroup.setAssociation(classAssocArray);
		
		// deal with attributes on QueryDb
		if (cqlAttributeArray.length > 1){
			// create a group for the attributes
			Group attributeGroup = new Group();
			attributeGroup.setLogicRelation(LogicalOperator.AND);
			// add the attributes to the group
			attributeGroup.setAttribute(cqlAttributeArray);
			Group[] g = new Group[1];
			g[0] = attributeGroup;
			// set the group on outer group
			classAndAttributeGroup.setGroup(g);
		}
		// if only one attribute; don't need the group
		if (cqlAttributeArray.length == 1)
			classAndAttributeGroup.setAttribute(cqlAttributeArray);
		target.setGroup(classAndAttributeGroup);
		cqlQuery.setTarget(target);	
		System.out.println("printing from createClassQuery");
		printCQL(cqlQuery);
		
		return cqlQuery;
	}
	private static Association getClassAssociation(Attribute[] classArray) {
		Association classAssoc = new Association();
		classAssoc.setName("gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb");
		classAssoc.setRoleName("classCollection");
		if (classArray.length >1){
			// create a group for the attributes
			Group classGroup = new Group();
			classGroup.setLogicRelation(LogicalOperator.OR);
			// add the attributes to the group
			classGroup.setAttribute(classArray);
			classAssoc.setGroup(classGroup);
		}
		if (classArray.length == 1)
			classAssoc.setAttribute(classArray[0]);
		return classAssoc;
	}
	private static Association getAttributeAssociation(Attribute[] attributeArray) {
		Association attribAssoc = new Association();
		attribAssoc.setName("gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb");
		attribAssoc.setRoleName("attributeCollection");
		if (attributeArray.length >1){
			// create a group for the attributes
			Group classGroup = new Group();
			classGroup.setLogicRelation(LogicalOperator.OR);
			// add the attributes to the group
			classGroup.setAttribute(attributeArray);
			attribAssoc.setGroup(classGroup);
		}
		if (attributeArray.length == 1)
			attribAssoc.setAttribute(attributeArray[0]);
		return attribAssoc;
	}
	private static void printCQL(CQLQuery cqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.cql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, cqlQuery, qname);
		} catch (SerializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(w);
	}
}
