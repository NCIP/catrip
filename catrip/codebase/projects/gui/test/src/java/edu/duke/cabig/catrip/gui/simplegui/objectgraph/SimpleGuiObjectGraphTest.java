package edu.duke.cabig.catrip.gui.simplegui.objectgraph;


import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphAssociation;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;

import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;

import java.util.List;

import junit.framework.TestCase;

public class SimpleGuiObjectGraphTest extends TestCase {
    private ObjectGraphProcessor processor;

    public SimpleGuiObjectGraphTest(String sTestName) {
        super(sTestName);
        processor = new ObjectGraphProcessor("conf/simplegui/SimpleGuiObjectGraph.xml");
    }
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetAllServices() {
        List<Service> services = processor.getServices();
        Service service;
        System.out.println("AVIALABLE SERVICES ... ");
        for (int i=0;i<services.size();i++) {
            service = services.get(i);
            System.out.println(i+1 + ") " + service.getServiceName() + "   " + service.getServiceURL());
        }
    }

    public void testGetAllTargetObjects() {
        List<GraphObject> objs = processor.getTargetObjects();
        System.out.println("AVIALABLE TARGET OBJECTS ... ");
        printObjects(objs);
    }

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
            System.out.println(i+1 + ") " + obj.getClassName() + "   " + obj.getServiceName());
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
    public void testGetAllAssociatedObjectsForAGivenTragetObject() {
        String targetObject = "edu.wustl.catissuecore.domainobject.TissueSpecimen";
        List<GraphObject> objs = processor.getAssociatedObjects(targetObject,"caTissueCore");
        GraphObject obj;
        GraphAssociation assoc;
        System.out.println("ASSOCIATED OBJECTS for " + targetObject +" ... ");
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName());
            System.out.println("        Association path from " + targetObject);
            List<GraphAssociation> assos = obj.getAssociationPathWRTTargetObject();
            for (int k=assos.size()-1;k>=0;k--) {
                assoc = assos.get(k);
                System.out.println("                    " + assoc.getClassName() + "   ROLE : " + assoc.getRoleName());
            }
        }
    }

    public void testGetAvialbleTragetObjectsToAssociateInRemoteServices() {
        List<GraphObject> objs = processor.getAvialbleTargetObjectsToAssociateInRemoteServices("caTissueCore");
        GraphObject obj;
        System.out.println("AVIALABLE TRAGET OBJECTS to associate not in  caTissueCore ... ");
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName());
            System.out.println("                    CDE : " + obj.getForeignAssociationInboundCDE());
        }
    }

    public void testGetAllAssociatedObjectsForAGivenTragetObjectInRemoteService() {
        String targetObject = "edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade";
        List<GraphObject> objs = processor.getAssociatedObjects(targetObject,"caTissueCAE",true);
        GraphObject obj;
        GraphAssociation assoc;
        System.out.println("ASSOCIATED OBJECTS for " + targetObject +" in REMOTE service... ");
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            System.out.println(i+1 + ") " + obj.getClassName());
            System.out.println("        Association path to " + targetObject);
            List<GraphAssociation> assos = obj.getAssociationPathWRTTargetObject();

            for (int k=0; k< assos.size() ;k++) {
                assoc = assos.get(k);
                System.out.println("                    " + assoc.getClassName() + "   ROLE : " + assoc.getRoleName());
            }

        }
    }

}