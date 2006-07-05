
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
import org.netbeans.graph.api.model.ability.IDirectionable;
import org.openide.util.Utilities;



/**
 * @author Sanjeev Agarwal
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
                ClassBean node = ((ClassNode)selectedComponent).getAssociatedClassObject(); //getDisplayName(); 
                test.showNodePrperties(node); 
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
        
        ClassBean cBean = DomainModelMetaDataRegistry.lookupClassByRefId(value).clone();
        
        if (cBean != null){
            ClassNode node = null;//GraphNode node = null;
            try {
                node = (ClassNode) ClassNode.class.newInstance(); // set the information of the GraphNode type with ClassBean itself.
                node.setID(test.createID(cBean.getId()));
                node.setDisplayName(cBean.getClassName());
                node.setIcon( Utilities.loadImage (cBean.getIcon())); 
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
        
    }
    
    public void undoableEditHappened(UndoableEdit edit) {
    }
    
    public void notifyModified() {
    }
    
    
    
    
    
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
