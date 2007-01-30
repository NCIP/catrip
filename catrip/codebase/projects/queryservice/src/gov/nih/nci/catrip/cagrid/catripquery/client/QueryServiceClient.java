package gov.nih.nci.catrip.cagrid.catripquery.client;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.axis.utils.ClassUtils;

import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.xml.sax.InputSource;

import org.globus.gsi.GlobusCredential;
import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;


import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.server.AttributeDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;
import gov.nih.nci.catrip.cagrid.catripquery.stubs.QueryServicePortType;
import gov.nih.nci.catrip.cagrid.catripquery.stubs.service.QueryServiceAddressingLocator;
import gov.nih.nci.catrip.cagrid.catripquery.common.QueryServiceI;
import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Group;
import gov.nih.nci.cagrid.cqlquery.LogicalOperator;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.faults.MalformedQueryExceptionType;
import gov.nih.nci.cagrid.data.faults.QueryProcessingExceptionType;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

/**
 * This class is autogenerated, DO NOT EDIT GENERATED GRID SERVICE METHODS.
 *
 * This client is generated automatically by Introduce to provide a clean unwrapped API to the
 * service.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 *
 * @created by Introduce Toolkit version 1.0
 */
public class QueryServiceClient extends ServiceSecurityClient implements QueryServiceI {
	protected QueryServicePortType portType;
	private Object portTypeMutex;

	public QueryServiceClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);
	}

	public QueryServiceClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
		super(url,proxy);
		initialize();
	}

	public QueryServiceClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
		this(epr,null);
	}

	public QueryServiceClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
		super(epr,proxy);
		initialize();
	}

	private void initialize() throws RemoteException {
		this.portTypeMutex = new Object();
		this.portType = createPortType();
	}

	private QueryServicePortType createPortType() throws RemoteException {

		QueryServiceAddressingLocator locator = new QueryServiceAddressingLocator();
		// attempt to load our context sensitive wsdd file
		InputStream resourceAsStream = ClassUtils.getResourceAsStream(getClass(), "client-config.wsdd");
		if (resourceAsStream != null) {
			// we found it, so tell axis to configure an engine to use it
			EngineConfiguration engineConfig = new FileProvider(resourceAsStream);
			// set the engine of the locator
			locator.setEngine(new AxisClient(engineConfig));
		}
		QueryServicePortType port = null;
		try {
			port = locator.getQueryServicePortTypePort(getEndpointReference());
		} catch (Exception e) {
			throw new RemoteException("Unable to locate portType:" + e.getMessage(), e);
		}

		return port;
	}

	public GetResourcePropertyResponse getResourceProperty(QName resourcePropertyQName) throws RemoteException {
		return portType.getResourceProperty(resourcePropertyQName);
	}

	public static void usage(){
		System.out.println(QueryServiceClient.class.getName() + " -url <service url>");
	}

	public static void main(String [] args){
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		System.out.println("Running the Grid Service Client");
		String qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\SimpleQuery1.xml";
		//String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
		try{
			try {
				//insert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//if(!(args.length == 2)){
			//	if(args[0].equals("-url")){
					QueryServiceClient client = new QueryServiceClient(serviceURI);
					CQLQuery cqlQuery = (CQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(qryFile)),CQLQuery.class);
					//gov.nih.nci.cagrid.dcql.Object to = (gov.nih.nci.cagrid.dcql.Object)dcql.getTargetObject();


					// query
					//CQLQuery cqlQuery = new CQLQuery();

					//gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
					//target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
					//cqlQuery.setTarget(target);
					printCQL(cqlQuery);
					CQLQueryResults results = client.query(cqlQuery);
					CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));

					//System.out.println("results is null ? " + (results == null));
					//System.out.println("results.getObjectResult() is null ? " + (results.getObjectResult() == null));

					while (iter.hasNext()) {
						gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();

						System.out.println(de.getId() +"   " + de.getDescription());

						System.out.println(de.getDcql());
					}
					if (results != null && results.getObjectResult() != null)
						System.out.println( " Returned Result Count :  " + results.getObjectResult().length);
					


//				} else {
//					usage();
//					System.exit(1);
//				}
//			} else {
//				usage();
//				System.exit(1);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	/**
	 * Fetch the entire contents of a text file, and return it in a String.
	 * This style of implementation does not throw Exceptions to the caller.
	 *
	 * @param aFile is a file which already exists and can be read.
	 * @return 
	 * @throws QueryException 
	 * @throws RemoteException 
	 * @throws MalformedURIException 
	 */
	@SuppressWarnings("unused")
	private static void insert() throws QueryException, MalformedURIException, RemoteException{
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		String qryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryengine-2.0\\test\\resources\\simpleQuery1.xml";
		System.out.println("inserting");
		QueryServiceClient client = new QueryServiceClient(serviceURI);
		CatripQuery caTripQuery = new CatripQuery();
		caTripQuery.setFirstName("DEEPI");
		DCQLQuery dcql = null;
		try {
			dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(qryFile)),DCQLQuery.class);
		} catch (DeserializationException e) {
			e.printStackTrace();
			throw new QueryException(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		//gov.nih.nci.cagrid.dcql.Object to = (gov.nih.nci.cagrid.dcql.Object)dcql.getTargetObject();

		//gov.nih.nci.cagrid.dcql.Object to = new gov.nih.nci.cagrid.dcql.Object();
		//to.setName("DCQL QRY1");
		//caTripQuery.setId(0);
		caTripQuery.setDescription("catissuecore_tissuespecimens_inactive_participants.xml");
		caTripQuery.setFirstName("first Name");
		caTripQuery.setLastName("last");
		caTripQuery.setInstance("instance");
		caTripQuery.setSource("source");
		File t = new File(qryFile);
		caTripQuery.setDcql(getContents(t));
		try {
			client.save(caTripQuery);
		} 
		catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	public static String getDCQL(String column, String value){
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		System.out.println("Running the Grid Service Client");
		String dcql =  null;
		//String QUERIES_DIR = "test" + File.separator + "resources" + File.separator;
		try {

			QueryServiceClient client = new QueryServiceClient(serviceURI);



			// query
			CQLQuery cqlQuery = new CQLQuery();

			gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
			target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
			target.setAttribute(new Attribute("dcql",Predicate.LIKE,"%"+value+"%"));
			cqlQuery.setTarget(target);
			//System.out.println("target is null? " + (target == null));
			CQLQueryResults results;
			results = client.query(cqlQuery);

			//CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));
			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));

			//System.out.println("results is null ? " + (results == null));
			//System.out.println("results.getObjectResult() is null ? " + (results.getObjectResult() == null));

			while (iter.hasNext()) {
				gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();

				//System.out.println(de.getId() +"   " + de.getFirstName());

				System.out.println(de.getDcql());
				dcql = de.getDcql().toString();

			}
		} catch (QueryProcessingExceptionType e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryExceptionType e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dcql;
	}

	public static Vector search(CQLQuery cqlQuery) throws QueryException {
		return (Vector) getResults(cqlQuery);

	}
	public static Vector search(QueryDb catripQuery) throws QueryException {
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		int index = 0;
		CQLQuery cqlQuery = new CQLQuery();
		gov.nih.nci.cagrid.cqlquery.Object target = new gov.nih.nci.cagrid.cqlquery.Object();
		QueryServiceClient client;
		Vector<QueryDb> queryResultCollection = new java.util.Vector<QueryDb>();
		try {
			client = new QueryServiceClient(serviceURI);
			//  DEBUG START
			if (catripQuery != null){
				System.out.println("query name : " + catripQuery.getName());
				System.out.println("description : " + catripQuery.getDescription());
				System.out.println("first name :  " + catripQuery.getFirstName());
				System.out.println("last name : " + catripQuery.getLastName());
			}
			// DEBUG END

			target.setName("gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb");
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
			//System.out.println("index = " + index);

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
				for (Iterator iter = collection.iterator(); iter.hasNext();) {
					ClassDb element = (ClassDb) iter.next();
						classArray[classCounter++] = new Attribute("name",Predicate.EQUAL_TO,element.getName());
						System.out.println("class array size : " + classArray.length + " element added : " + element.getName());
				}
				
				classAssoc.setRoleName("classCollection");
				assocGroup.setAttribute(classArray);
				assocGroupArray[0] = assocGroup;
			}
			Group attributesAndAssociationsGroup = new Group();
			attributesAndAssociationsGroup.setLogicRelation(LogicalOperator.OR);

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
					attributesAndAssociationsGroup.setGroup(assocGroupArray);  // temp
				}
				else{
					attribGroup.setAttribute(attribArray);
					attribAssoc.setGroup(attribGroup);
					// for later use
					attribAttributeArray[0] = attribAssoc;
					attributesAndAssociationsGroup.setAssociation(attribAttributeArray);
				}
			}
			if (classAssoc == null && attribAssoc == null){
				CQLQuery cqlQuery2 = createClassAttributeQuery(cqlAttributeArray);
				return (Vector) getResults(cqlQuery2);
			}
			if (classAssoc != null && attribAssoc == null){
				CQLQuery cqlQuery2 = createClassQuery(cqlAttributeArray, classArray);
				return (Vector) getResults(cqlQuery2);
			}

			if (classAssoc != null){
				//attributesAndAssociationsGroup.setAssociation(attribAttributeArray);
				attributesAndAssociationsGroup.setGroup(assocGroupArray);
			}
			//target.setGroup(attributesAndAssociationsGroup);
			//if (classAssoc != null)
				classAssoc.setGroup(attributesAndAssociationsGroup);
			//else
				attribAssoc.setGroup(attributesAndAssociationsGroup);
			//target.setAssociation(classAssoc);
			Group outerGroup = new Group();
			outerGroup.setLogicRelation(LogicalOperator.AND);
			Association[] outerClassAssociation = {classAssoc};
			outerGroup.setAssociation(outerClassAssociation);
			
			if (cqlAttributeArray.length > 1){
				Group attributeGroup = new Group();
				attributeGroup.setLogicRelation(LogicalOperator.AND);
				attributeGroup.setAttribute(cqlAttributeArray);
				Group[] g = new Group[1];
				g[0] = attributeGroup;
				outerGroup.setGroup(g);
			}
			if (cqlAttributeArray.length == 1){
				outerGroup.setAttribute(cqlAttributeArray);
			}

			
			target.setGroup(outerGroup);
			cqlQuery.setTarget(target);			
			printCQL(cqlQuery);
			//System.out.println("target is null? " + (target == null));
			CQLQueryResults results;
			results = client.query(cqlQuery);

			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));

			//System.out.println("results is null ? " + (results == null));
			//System.out.println("results.getObjectResult() is null ? " + (results.getObjectResult() == null));
			while (iter.hasNext()) {
				gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();
				queryResultCollection.add(de);
				System.out.println(de.getId() +"   " + de.getFirstName());
				System.out.println("dcql = " + de.getDcql());
			}
			System.out.println("results = " + queryResultCollection.size());
		} catch (QueryProcessingExceptionType e) {
			e.printStackTrace();
			throw new QueryException(e);
		} catch (MalformedQueryExceptionType e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (MalformedURIException e){
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return queryResultCollection;
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
	
	private static Collection getResults(CQLQuery cqlQuery) throws QueryException{
		Vector<QueryDb> queryResultCollection = new java.util.Vector<QueryDb>();
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		CQLQueryResults results;
		QueryServiceClient client = null;
		try{
			client = new QueryServiceClient(serviceURI);
			results = client.query(cqlQuery);
			CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("C:\\catrip\\catrip\\codebase\\projects\\queryservice\\src\\gov\\nih\\nci\\catrip\\cagrid\\catripquery\\client\\client-config.wsdd")));

			//System.out.println("results is null ? " + (results == null));
			//System.out.println("results.getObjectResult() is null ? " + (results.getObjectResult() == null));
			while (iter.hasNext()) {
				gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb de = (gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb) iter.next();
				queryResultCollection.add(de);
				System.out.println(de.getId() +"   " + de.getFirstName());
				System.out.println("dcql = " + de.getDcql());
			}
			System.out.println("results = " + queryResultCollection.size());
		} catch (QueryProcessingExceptionType e) {
			e.printStackTrace();
			throw new QueryException(e);
		} catch (MalformedQueryExceptionType e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (MalformedURIException e){
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
			throw new QueryException(e);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return queryResultCollection;

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
		if (c.size() == 0){
			c.add("medicalRecordNumber");
			c.add("totalScore");
		}
		// DEBUG END
		return c;
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

	private static String getContents(File aFile) {
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

	public void save(gov.nih.nci.catrip.cagrid.catripquery.CatripQuery catripQuery) throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"save");
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequest params = new gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequest();
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequestCatripQuery catripQueryContainer = new gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveRequestCatripQuery();
			catripQueryContainer.setCatripQuery(catripQuery);
			params.setCatripQuery(catripQueryContainer);
			gov.nih.nci.catrip.cagrid.catripquery.stubs.SaveResponse boxedResult = portType.save(params);
		}
	}
	public void delete(long _long) throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"delete");
			gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteRequest params = new gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteRequest();
			params.set_long(_long);
			gov.nih.nci.catrip.cagrid.catripquery.stubs.DeleteResponse boxedResult = portType.delete(params);
		}
	}
	public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata() throws RemoteException {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"getServiceSecurityMetadata");
			gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest params = new gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataRequest();
			gov.nih.nci.cagrid.introduce.security.stubs.GetServiceSecurityMetadataResponse boxedResult = portType.getServiceSecurityMetadata(params);
			return boxedResult.getServiceSecurityMetadata();
		}
	}
	public gov.nih.nci.cagrid.cqlresultset.CQLQueryResults query(gov.nih.nci.cagrid.cqlquery.CQLQuery cqlQuery) throws RemoteException, gov.nih.nci.cagrid.data.faults.QueryProcessingExceptionType, gov.nih.nci.cagrid.data.faults.MalformedQueryExceptionType {
		synchronized(portTypeMutex){
			configureStubSecurity((Stub)portType,"query");
			gov.nih.nci.cagrid.data.QueryRequest params = new gov.nih.nci.cagrid.data.QueryRequest();
			gov.nih.nci.cagrid.data.QueryRequestCqlQuery cqlQueryContainer = new gov.nih.nci.cagrid.data.QueryRequestCqlQuery();
			cqlQueryContainer.setCQLQuery(cqlQuery);
			params.setCqlQuery(cqlQueryContainer);
			gov.nih.nci.cagrid.data.QueryResponse boxedResult = portType.query(params);
			return boxedResult.getCQLQueryResultCollection();
		}
	}

}
