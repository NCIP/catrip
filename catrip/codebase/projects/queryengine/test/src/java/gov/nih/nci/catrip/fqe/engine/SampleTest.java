package gov.nih.nci.catrip.fqe.engine;

import caBIG.caGrid.x10.govNihNciCagridDcql.DCQLQueryDocument;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SampleTest extends TestCase {
  private static Log log = LogFactory.getLog(SampleTest.class);

  public SampleTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(SampleTest.class);
   }

    public void testFQE() throws Exception {
    
        FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
        DCQLQueryDocument dcqlQueryDocument = DCQLQueryDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema-cagrid\\dcql1.xml"));
        
        fqe.execute(dcqlQueryDocument);
    }
}




