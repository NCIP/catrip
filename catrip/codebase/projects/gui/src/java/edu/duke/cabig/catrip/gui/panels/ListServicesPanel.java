/*
 * ListServicesPanel.java
 *
 * Created on May 16, 2006, 2:21 PM
 */

package edu.duke.cabig.catrip.gui.panels;



import com.sun.org.apache.bcel.internal.classfile.Attribute;
import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.discovery.ServiceMetaDataRegistry;
import edu.duke.cabig.catrip.gui.dnd.*;
import java.awt.Component;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.tree.*;




/**
 *
 * @author  Sanjeev Agarwal
 */
public class ListServicesPanel extends CPanel {
    
//    private int lastID = 0;
//    private GraphDocument document;
//    private JComponent view;
    private JTree tree;
    
    /** Creates new form ListServicesPanel */
    public ListServicesPanel() {
        initComponents();
        buildTree();
    }
    
    
    public JTree getTree(){
        return tree;
    }
    
    
    private void buildTree(){
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("caTRIP Services");
        
        ArrayList sNames = ServiceMetaDataRegistry.getServiceNames();
        
        for (int i = 0; i < sNames.size(); i++) {
            String serviceName = (String)sNames.get(i);
            ArrayList clList = DomainModelMetaDataRegistry.lookupClassListByDomainModelName(serviceName);
            DefaultMutableTreeNode serviceOne = new DefaultMutableTreeNode(serviceName);
            for (int j = 0; j < clList.size(); j++) {
                ClassBean classBean = (ClassBean)clList.get(j);
                DefaultMutableTreeNode classn = new DefaultMutableTreeNode(classBean);
                
                DefaultMutableTreeNode attributesNode = new DefaultMutableTreeNode("Attributes" );
                DefaultMutableTreeNode associationsNode = new DefaultMutableTreeNode("Associations" );
                
                ArrayList attributes = classBean.getAttributes();
                for (int k = 0; k < attributes.size(); k++) {
                    AttributeBean aBean = (AttributeBean)attributes.get(k);
                    DefaultMutableTreeNode attNode = new DefaultMutableTreeNode(aBean.getCDEName());
                    attributesNode.add(attNode);
                }
                
                ArrayList associations = classBean.getAssociatedClasses();
                for (int k = 0; k < associations.size(); k++) {
                    String classRefId = (String)associations.get(k);
                    ClassBean classBeanTmp = DomainModelMetaDataRegistry.lookupClassByRefId(classRefId);
                    DefaultMutableTreeNode assNode = new DefaultMutableTreeNode(classBeanTmp);
                    associationsNode.add(assNode);
                }
                
                classn.add(attributesNode); classn.add(associationsNode);
                serviceOne.add(classn);
            }
            root.add(serviceOne);
        }
        
        
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        
        MyRenderer rr = new MyRenderer(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_cde.png")));
        tree.setCellRenderer(rr);
        ToolTipManager.sharedInstance().registerComponent(tree);
        
        
        
        add(new JScrollPane(tree));
        
        
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout());

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame("Testing this panel");
                jf.getContentPane().add(new ListServicesPanel());
                jf.setBounds(10,10,200,200);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);
            }
        });
        
    }
    
    
    
    
    private class MyRenderer extends DefaultTreeCellRenderer {
        Icon tutorialIcon;
        
        public MyRenderer(Icon icon) {
            tutorialIcon = icon;
            setClosedIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_close.png")));
            setOpenIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_open.png")));
        }
        
        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {
            
            super.getTreeCellRendererComponent(
                    tree, value, sel,
                    expanded, leaf, row,
                    hasFocus);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            
            if (leaf ) {
                setIcon(tutorialIcon);
                setToolTipText("CDE Named : "+value.toString());
            } else if( value.toString().equalsIgnoreCase("Attributes") ){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_attributes.png")));
            }else if( value.toString().equalsIgnoreCase("Associations") ){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_associations.png")));
            }else if ( node.getLevel() == 2){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_class_.png")));
            }else if ( node.getLevel() == 1){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_service.png")));
            }else {
                setToolTipText(value.toString()); //no tool tip
            }
            
            
            if ( (node.getLevel() == 4) && (node.getParent().toString().equalsIgnoreCase("Associations"))  ){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_association.png")));
//                System.out.println ("###### node is :"+node.getParent ());
            }
            
            
            return this;
        }
        
        
        
    }
    
    
    
    
    
}
