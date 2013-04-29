/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.panels;



import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.AttributeBeanComparator;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ClassBeanComparator;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.components.NamedTreeNode;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.discovery.ServiceMetaDataRegistry;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.*;
import javax.swing.tree.*;




/**
 * Panel which contains the JTree of the Services.
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
    
    /** build the tree by loading the data from Service and DomainModel registry. */
    private void buildTree(){
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("caTRIP Services");
        
        ArrayList serviceNames = ServiceMetaDataRegistry.getServiceNames();
        
        for (int i = 0; i < serviceNames.size(); i++) {
            String serviceName = (String)serviceNames.get(i);
//            System.out.println("## Looking for the service :"+serviceName);
            ArrayList classList = DomainModelMetaDataRegistry.lookupClassListByServiceName(serviceName);
            Collections.sort(classList, new ClassBeanComparator());
            DefaultMutableTreeNode serviceOne = new DefaultMutableTreeNode(serviceName);
            for (int j = 0; j < classList.size(); j++) {
                ClassBean classBean = (ClassBean)classList.get(j);
                DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(classBean);
                
                DefaultMutableTreeNode attributesNode = new DefaultMutableTreeNode("Attributes" );
                DefaultMutableTreeNode associationsNode = new DefaultMutableTreeNode("Associations" );
                DefaultMutableTreeNode generalizationNode = new DefaultMutableTreeNode("Generalization" );
                
                
                ArrayList attributes = classBean.getAttributes();
                Collections.sort(attributes, new AttributeBeanComparator());
                for (int k = 0; k < attributes.size(); k++) {
                    AttributeBean aBean = (AttributeBean)attributes.get(k);
                    DefaultMutableTreeNode attributeLeafNode = new DefaultMutableTreeNode(aBean.getCDEName());
                    attributesNode.add(attributeLeafNode);
                }
                
                ArrayList associations = classBean.getAssociatedClasses();
                
                ArrayList<String> superClassAssociatedClassList = classBean.getSuperClassAssociatedClassList();
                
                for (int k = 0; k < associations.size(); k++) {
                    String classRefId = (String)associations.get(k);
                    boolean same = classRefId.equalsIgnoreCase(classBean.getId());
                    boolean inherited = superClassAssociatedClassList.contains(classRefId);
                    
                    ClassBean classBeanTmp = DomainModelMetaDataRegistry.lookupClassByRefId(classRefId);
                    // TODO - try to print the associatioRole name with the assciation tree
                    DefaultMutableTreeNode associationLeafNode = null;
                    if (same){
                        String name = classBeanTmp.getClassName()+" (Role: "+classBean.getAssociationRoleName(classRefId)+")";
                        associationLeafNode = new NamedTreeNode(name,classBeanTmp);
                    } else if(inherited){
                        String name = classBeanTmp.getClassName()+"  (Inherited) ";//+classBean.getAssociationRoleName(classRefId)+")";
                        associationLeafNode = new NamedTreeNode(name,classBeanTmp);
                    }else{
                        associationLeafNode = new DefaultMutableTreeNode(classBeanTmp);
                    }
                    associationsNode.add(associationLeafNode);
                }
                
                // sanjeev: add the super class information..
                String superClassName = classBean.getSuperClassName();
                if (superClassName == null){
                    superClassName = "No Super Class";
                }
                generalizationNode.add(new DefaultMutableTreeNode("Super Class: "+superClassName));
                
                classNode.add(attributesNode); classNode.add(associationsNode);  classNode.add(generalizationNode);
                serviceOne.add(classNode);
            }
            root.add(serviceOne);
        }
        
        
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        
        MyRenderer myRenderer = new MyRenderer(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_cde.png")));
        tree.setCellRenderer(myRenderer);
        ToolTipManager.sharedInstance().registerComponent(tree);
        
        add(new JScrollPane(tree));
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout());

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
//
//    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                JFrame jf = new JFrame("Testing this panel");
//                jf.getContentPane().add(new ListServicesPanel());
//                jf.setBounds(10,10,200,200);
//                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                jf.setVisible(true);
//            }
//        });
//
//    }
    
    
    
    /** Custom  Tree cell/Node renderer which different Icons for different levels/node-type. */
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
            // TODO - Get the Icons from the GUIConstants class..
            if (leaf ) {
                setIcon(tutorialIcon);
                setToolTipText(value.toString());
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
            
            // TODO - Get the Icons from the GUIConstants class..
            if ( (node.getLevel() == 4) && (node.getParent().toString().equalsIgnoreCase("Associations"))  ){
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_association.png")));
//                System.out.println ("###### node is :"+node.getParent ());
            }
            
            
            return this;
        }
        
        
        
    }
    
    
    
    
    
}
