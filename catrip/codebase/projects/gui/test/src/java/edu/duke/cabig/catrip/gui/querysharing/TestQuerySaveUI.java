package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

public class TestQuerySaveUI extends TestCase {
	private QueryServiceClient client;
	private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\built_DemoUseCase2-b.xml";
	//private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\DemoUseCase1-CGEMSTARGET.xml";
	////
	//private String dcqlQueryFile = "C:\\catrip\\catrip\\codebase\\projects\\queryservice\\cgemsQuery.xml";
	private DCQLQuery dcql = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService";
		client = new QueryServiceClient(serviceURI);
	}

	@SuppressWarnings("unused")
	private DCQLQuery getDCQLObject() throws DeserializationException, FileNotFoundException {
		dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(dcqlQueryFile)),DCQLQuery.class);
		System.out.println("target object " + dcql.getTargetObject().getName());
		return dcql;
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testUI() throws DeserializationException, FileNotFoundException {
		QueryEditUI s = null;
		try {

			File t = new File(dcqlQueryFile); 
			String dcqlAsString = DcqlUtil.getContents(t);
			// TODO - does not work with dcql as object????
			//s = new QueryEditUI(getDCQLObject());
			
			// works with DCQL as string
			s = new QueryEditUI(dcqlAsString);
			//org.exolab.castor.jdo.engine.ClobImpl c = new org.exolab.castor.jdo.engine.ClobImpl (null, 0);
			CatripQuery catripQuery = new CatripQuery();
			catripQuery.setName("inserted");
			catripQuery.setDcql(dcqlAsString);
			client.save(catripQuery);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
//		catch (DeserializationException e) {
//		e.printStackTrace();
//		} 
//		catch (FileNotFoundException e) {
//		e.printStackTrace();
//		}

	}
}


