/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.dcql.Association;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import org.xml.sax.InputSource;

public class TestParser extends TestCase {

	@SuppressWarnings("unused")
	private QueryServiceClient client;
	//private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryengine-2.0\\test\\resources\\simpleQuery1.xml";
	//private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryengine\\testDCQL\\catissuecore_tissuespecimens_cae_greatest.xml";
	private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\DemoUseCase1-CGEMSTARGET.xml";
	//private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\groupdcql.xml";
	private DCQLQuery dcql = null;
	private QueryDb decomposedCatripQuery;
	private final boolean DEBUG = true;
	
	protected void setUp() throws Exception {
		super.setUp();
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		client = new QueryServiceClient(serviceURI);
		
	}
	@SuppressWarnings("unused")
	private DCQLQuery getDCQLObject() throws DeserializationException, FileNotFoundException {
        dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(dcqlQueryFile)),DCQLQuery.class);
		return dcql;
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testParser() {
		decomposedCatripQuery = new QueryDb();
		DCQLQuery dcql = new DCQLQuery();
		File t = new File(dcqlQueryFile);
		try {
			 dcql = convertStringToDCQL(getContents(t));
			 System.out.println("target object : " + dcql.getTargetObject().getName());
			 ClassDb targetObject = new ClassDb();
			 targetObject.setName(dcql.getTargetObject().getName());
			 populateObjectFromDCQLObject(dcql.getTargetObject(), targetObject);
			 printDCQL(dcql);
		}
		catch (Exception e) {
			e.printStackTrace();
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
			if (dcqlObject.getGroup() != null){
				Attribute[] groupAttributes = dcqlObject.getGroup().getAttribute();
				if (groupAttributes != null){
					for (int i = 0; i < groupAttributes.length; i++) {
						System.out.println(" group attrs : " + groupAttributes[i].getName());
						processAttribute(groupAttributes[i], aDCQLClass);
					}
				}
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
		if (groupAttributes != null){
			for (int i = 0; i < groupAttributes.length; i++) {
				System.out.println(" group attrs : " + groupAttributes[i].getName());
				processAttribute(groupAttributes[i], aClass);
			}
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
		if (dcqlGroup.getForeignAssociation() != null) {
			ForeignAssociation[] fa = dcqlGroup.getForeignAssociation();
			if (fa != null){
			for (int i = 0; i < fa.length; i++) {
				gov.nih.nci.cagrid.dcql.Object o = processForeignAssociation(fa[i], new ClassDb());
				populateObjectFromDCQLObject(o, new ClassDb());
				
			}
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
		populateObjectFromDCQLObject(dcqlAssociation, new ClassDb());

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
		add(aClass);
		//decomposedCatripQuery.addClass(aClass);
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
		if (aClass == null || aClass.getName() == null)
			return;
		// Do not add duplicates
		boolean duplicateFound = false;
		Collection queryCollection = decomposedCatripQuery.getClassCollection();
		if (queryCollection == null){
			decomposedCatripQuery.addClass(aClass);	
		}
		else{
			for (Iterator iter = queryCollection.iterator(); iter.hasNext();) {
				ClassDb element = (ClassDb) iter.next();
				if (element != null && element.getName() != null && !(element.getName().trim().equalsIgnoreCase(aClass.getName().trim()))){
					
					duplicateFound = false;
				}
				else{
					duplicateFound = true;
					System.out.println("Found a duplicate " + aClass.getName());
					break;
				}

			}
			if (!duplicateFound){
				decomposedCatripQuery.addClass(aClass);	
				System.out.println("adding : " + aClass.getName());
			}
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
	private  String getContents(File aFile) {
		//...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		//declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			//use buffering, reading one line at a time
			//FileReader always assumes default encoding is OK!
			input = new BufferedReader( new FileReader(aFile) );
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear in a row.
			 */
			while (( line = input.readLine()) != null){
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (input!= null) {
					//flush and close both "input" and its underlying FileReader
					input.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}
	private static void printDCQL(DCQLQuery cqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.cql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, cqlQuery, qname);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		System.out.println(w);
	}

}
