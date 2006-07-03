/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package edu.duke.cabig.catrip.gui.dnd;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.panels.VisualQueryDesignerPanel;
import org.netbeans.graph.api.IGraphEventHandler;
import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.builtin.GraphLink;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;

import javax.swing.undo.UndoableEdit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import org.netbeans.graph.api.model.ability.IDirectionable;



/**
 * @author David Kaspar
 */
public class EventHandler extends IGraphEventHandler {
    
    private VisualQueryDesignerPanel test;
    
    public EventHandler(VisualQueryDesignerPanel test) {
        this.test = test;
    }
    
    public void setSourcePort(IGraphLink link, IGraphPort sourcePort) {
        ((GraphLink) link).setSourcePort((GraphPort) sourcePort);
    }
    
    public void setTargetPort(IGraphLink link, IGraphPort targetPort) {
        ((GraphLink) link).setTargetPort((GraphPort) targetPort);
    }
    
    public boolean isLinkCreateable(IGraphPort sourcePort, IGraphPort targetPort) {
        return true;
    }
    
    public void createLink(IGraphPort sourcePort, IGraphPort targetPort) {
        GraphLink link = new GraphLink();
//        link.setDisplayName("some name linked...");
        
        link.setID(test.createID("link"));
        link.setSourcePort((GraphPort) sourcePort);
        link.setTargetPort((GraphPort) targetPort);
        test.getDocument().addComponents(GraphEvent.createSingle(link));
    }
    
    public void componentsSelected(GraphEvent event) {
        test.getDocument().selectComponents(event);
        // sanjeev : show the properties of this link in the properties table..
        Object[] selectedComponents =  test.getDocument().getSelectedComponents().getNodes();
        
        if ((selectedComponents != null) && selectedComponents.length == 1){
            Object selectedComponent = selectedComponents[0];
            if (selectedComponent instanceof IGraphNode) {
                String nodeClass = ((GraphNode)selectedComponent).getID(); //getDisplayName();
                //System.out.println("@#@#@#  "+ nodeClass);
                //System.out.println("@#@#@#  "+ selectedComponent.getClass().getName());
                test.showNodePrperties(nodeClass);
            }
        }
        
        
    }
    
    public boolean isAcceptable(IGraphNode node, DataFlavor[] dataFlavors) {
        return true;
//            final DataFlavor dataFlavor = DataFlavor.selectBestTextFlavor (dataFlavors);
//            System.out.println (dataFlavor);
//            return dataFlavor != null;
    }
    
    public void accept(IGraphNode dropNode, Transferable transferable) {
        String value = null;
        try {
            value = (String) transferable.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace(); // TODO
        } catch (IOException e) {
            e.printStackTrace(); // TODO
        }
        final Info[] infos = test.getInfos();
        
        ClassBean cBean = DomainModelMetaDataRegistry.lookupClassByRefId(value);
        
        if (cBean != null){
            ClassNode node = null;//GraphNode node = null;
            try {
                node = (ClassNode) infos[1].getNodeClass().newInstance();
                node.setID(test.createID(cBean.getId()));
                node.setDisplayName(cBean.getClassName());
                node.setIcon(infos[1].getIcon()); // set the icon based on the service name.. have a pre selected icons.. and a default icon too
                addAttrubutePorts(node, cBean);
                node.setAssociatedClassObject(cBean);// attach a classBean instance with this Graph node.
                
            } catch (InstantiationException e) {
                e.printStackTrace(); // TODO
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // TODO
            }
            test.getDocument().addComponents(GraphEvent.createSingle(node));
            return;
        }
        
        // getting to know the drag source
//        for (int i = 0; i < infos.length; i++) {
//            final Info info = infos[i];
//            if (info.getName().equals(value)) {
//                GraphNode node = null;
//                try {
//                    node = (GraphNode) info.getNodeClass().newInstance();
//                    node.setID(test.createID(info.getName()));
//                    node.setDisplayName(info.getDisplayName());
//                    node.setIcon(info.getIcon());
//                    // sanjeev
//                    if (node.getID().startsWith("caCore")){
//                        node.setTooltipText("gov.nih.nci.cabio.domain."+info.getDisplayName());
//                    } else {
//                        node.setTooltipText("edu.georgetown.pir.domain."+info.getDisplayName());
//                    }
//                    // sanjeev
//                    // here you should add the ports randomly...
//                    addPorts(node);
//                    // sanjeev
//
//                } catch (InstantiationException e) {
//                    e.printStackTrace(); // TODO
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace(); // TODO
//                }
//                test.getDocument().addComponents(GraphEvent.createSingle(node));
//                return;
//            }
//        }
        // getting to know the drag source
    }
    
    public void undoableEditHappened(UndoableEdit edit) {
    }
    
    public void notifyModified() {
    }
    
    
    
    // sanjeev
//    private void addPorts(GraphNode node){
//        String nodeName = node.getID();
//        
//        if (nodeName.startsWith("caCore.Gene")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("clusterId",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("symbol",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Taxon")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("scientificName",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("ethnicityStrain",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("abbreviation",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("commonName",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Chromosome")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("name",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Clone")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("name",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("accessionNumber",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("insertSize",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("version",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("strain",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Library")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("name",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("type",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("unigeneId",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("description",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("labHost",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("keyword",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("rsite1",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Sequence")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("accessionNumber",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("description",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("length",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("accessionNumberVersion",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("type",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("asciiString",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("caCore.Target")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("name",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("type",IDirectionable.BOTTOM_LEFT));
//            
//            
//            // other domain model objects..
//        }else if (nodeName.startsWith("PIR.Taxon")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.Protein")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("uniprotkbPrimaryAccession",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("uniprotkbEntryName",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("proteinType",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.SequenceVariant")){
//            node.addPort( new AttributePort("originalSequence",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("variantSequence",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.SpliceVariant")){
//            node.addPort( new AttributePort("originalSequence",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("variantSequence",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.ProteinSequence")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("checksum",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("length",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("value",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("molecularWeightInDaltons",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("sequenceInFastaFormat",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.SequenceConflict")){
//            node.addPort( new AttributePort("originalSequence",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("variantSequence",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.Gene")){
//            node.addPort( new AttributePort("id",IDirectionable.BOTTOM_LEFT));
//            
//        }else if (nodeName.startsWith("PIR.MutagenesisSite")){
//            node.addPort( new AttributePort("originalSequence",IDirectionable.BOTTOM_LEFT));
//            node.addPort( new AttributePort("variantSequence",IDirectionable.BOTTOM_LEFT));
//        }
//        
//    }
    // sanjeev
    
    
    // sanjeev
    private void addAttrubutePorts(GraphNode node, ClassBean classBean){
        
        ArrayList attributes = classBean.getAttributes();
        for (int k = 0; k < attributes.size(); k++) {
            AttributeBean aBean = (AttributeBean)attributes.get(k);
            node.addPort( new AttributePort(aBean.getCDEName(),IDirectionable.BOTTOM_LEFT));
        }

    }
    // sanjeev
    
    
}
