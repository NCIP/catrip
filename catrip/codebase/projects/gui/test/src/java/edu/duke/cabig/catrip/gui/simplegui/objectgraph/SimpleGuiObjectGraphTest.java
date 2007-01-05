package edu.duke.cabig.catrip.gui.simplegui.objectgraph;


import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphAssociation;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;

import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;
import java.io.File;

import java.util.List;

import junit.framework.TestCase;

/**
 * This is a unit test that validates the ability to parse configuration files and walk through
 * object graphs.
 * @author Sanjeev Agarwal
 * @testType unit
 */
public class SimpleGuiObjectGraphTest extends TestCase {
    private ObjectGraphProcessor processor;

    public SimpleGuiObjectGraphTest(String sTestName) {
        super(sTestName);
        processor = new ObjectGraphProcessor(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation()+File.separator+"simplegui"+File.separator+"SimpleGuiObjectGraph.xml");
         //processor = new ObjectGraphProcessor("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\gui\\conf\\simplegui\\SimpleGuiObjectGraph.xml");
    }
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests whether services are there
     */
    public void testGetAllServices() {
        List<Service> services = processor.getServices();
        Service service;
        System.out.println("AVIALABLE SERVICES ... ");
        for (int i=0;i<services.size();i++) {
            service = services.get(i);
            System.out.println(i+1 + ") " + service.getServiceName() + "   " + service.getServiceURL());
        }
    }

    /**
     * Tests getting the target objects
     */
    public void testGetAllTargetObjects() {
        List<GraphObject> objs = processor.getTargetObjects();
        System.out.println("AVIALABLE TARGET OBJECTS ... ");
        printObjects(objs);
    }

    /**
     * Tests getting the target objects for a service
     */
    public void testGetAllTargetObjectsForAGivenService() {
        List<GraphObject> objs = processor.getTragetObjects("caTissueCore");
        System.out.println("AVIALABLE TARGET OBJECTS for caTissueCore ... ");
        printObjects(objs);
    }
    private void printObjects(List<GraphObject> objs){
        GraphObject obj;
        GraphAssociation assoc;
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName() + "   refID:" + obj.getRefID());
            System.out.println("    OutBound Path to associate with Foreign Association" );
            List<GraphAssociation> assos = obj.getForeignAssociationOutboundPath();
            for (int k=0; k<assos.size();k++) {
                assoc = assos.get(k);
                System.out.println("                    " + assoc.getClassName() + "   ROLE : " + assoc.getRoleName() );
                // check for last class in the path
                if (k==assos.size()-1){
                 System.out.println("                    CDE : " + obj.getForeignAssociationOutboundCDE() );
                }
            }
        }
    }
    
    /**
     * Tests whether there are associations for a target object
     */
    public void testGetAllAssociatedObjectsForAGivenTragetObject() {
        String targetObject = "edu.wustl.catissuecore.domainobject.TissueSpecimen";
        List<GraphObject> objs = processor.getAssociatedObjects(targetObject,"caTissueCore");
        GraphObject obj;
        GraphAssociation assoc;
        System.out.println("ASSOCIATED OBJECTS for " + targetObject +" ... ");
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName() + "   " + obj.getRefID());
            System.out.println("        Association path from " + targetObject);
            List<GraphAssociation> assos = obj.getAssociationPathWRTTargetObject();
            for (int k=assos.size()-1;k>=0;k--) {
                assoc = assos.get(k);
                System.out.println("                    " + assoc.getClassName() + "   ROLE : " + assoc.getRoleName());
            }
        }
    }

    /**
     * Tests getting the target objects for a remote service
     */
    public void testGetAvialbleTragetObjectsToAssociateInRemoteServices() {
        List<GraphObject> objs = processor.getAvialbleTargetObjectsToAssociateInRemoteServices("caTissueCore");
        GraphObject obj;
        GraphAssociation assoc;
        System.out.println("AVIALABLE TRAGET OBJECTS to associate not in  caTissueCore ... ");
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName());
            System.out.println("                    CDE : " + obj.getForeignAssociationInboundCDE());
            System.out.println("        Foreign Association inbound path");

            List<GraphAssociation> assos = obj.getForeignAssociationInboundPath();
            for (int k=0;k<assos.size();k++) {
                assoc = assos.get(k);
                System.out.println("                    " + assoc.getClassName() + "   ROLE : " + assoc.getRoleName());
            }
        }
    }

}