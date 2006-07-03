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
    private Info[] infos;
    
    /** Creates new form ListServicesPanel */
    public ListServicesPanel() {
        initComponents();
        buildTree2();
    }
    
    public Info[] getInfos(){
        return infos;
    }
    
    public JTree getTree(){
        return tree;
    }
    
    
    private void buildTree2(){
        // build tree for only one service now..
        
        
        
        
        infos = new Info[] {
            new Info("caCore.Gene", "Gene", "caCore.png", ClassNode.class),
            new Info("caCore.Taxon", "Taxon", "caCore.png", ClassNode.class),
            new Info("caCore.Chromosome", "Chromosome", "caCore.png", ClassNode.class),
            new Info("caCore.Clone", "Clone", "caCore.png", ClassNode.class),
            new Info("caCore.Library", "Library", "caCore.png", ClassNode.class),
            new Info("caCore.Sequence", "Sequence", "caCore.png", ClassNode.class),
            new Info("caCore.Target", "Target", "caCore.png", ClassNode.class),
            
            new Info("PIR.Taxon", "Taxon", "PIR.png", ClassNode.class),
            new Info("PIR.Protein", "Protein", "PIR.png", ClassNode.class),
            new Info("PIR.SequenceVariant", "SequenceVariant", "PIR.png", ClassNode.class),
            new Info("PIR.SpliceVariant", "SpliceVariant", "PIR.png", ClassNode.class),
            new Info("PIR.ProteinSequence", "ProteinSequence", "PIR.png", ClassNode.class),
            new Info("PIR.SequenceConflict", "SequenceConflict", "PIR.png", ClassNode.class),
            new Info("PIR.Gene", "Gene", "PIR.png", ClassNode.class),
            new Info("PIR.MutagenesisSite", "MutagenesisSite", "PIR.png", ClassNode.class),
        };
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("caTRIP Services");
        
        ArrayList sNames = ServiceMetaDataRegistry.getServiceNames();
        
        for (int i = 0; i < sNames.size(); i++) {
            String serviceName = (String)sNames.get(i);
            ArrayList clList = DomainModelMetaDataRegistry.lookupClassListByDomainModelName(serviceName);
            DefaultMutableTreeNode serviceOne = new DefaultMutableTreeNode(serviceName);
            for (int j = 0; j < clList.size(); j++) {
                ClassBean classBean = (ClassBean)clList.get(j);
                DefaultMutableTreeNode classn = new DefaultMutableTreeNode(classBean);
                
                DefaultMutableTreeNode attributesNode = new DefaultMutableTreeNode("Attrinbutes" );
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
                    DefaultMutableTreeNode assNode = new DefaultMutableTreeNode(classBeanTmp.getClassName());
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
    
    private void buildTree(){
        // not required after you generate tree dynamically.
        // names are complete package names.. so are defnintely different but the
        // displayName can be same for two similar objects...
        
        infos = new Info[] {
            new Info("caCore.Gene", "Gene", "caCore.png", ClassNode.class),
            new Info("caCore.Taxon", "Taxon", "caCore.png", ClassNode.class),
            new Info("caCore.Chromosome", "Chromosome", "caCore.png", ClassNode.class),
            new Info("caCore.Clone", "Clone", "caCore.png", ClassNode.class),
            new Info("caCore.Library", "Library", "caCore.png", ClassNode.class),
            new Info("caCore.Sequence", "Sequence", "caCore.png", ClassNode.class),
            new Info("caCore.Target", "Target", "caCore.png", ClassNode.class),
            
            new Info("PIR.Taxon", "Taxon", "PIR.png", ClassNode.class),
            new Info("PIR.Protein", "Protein", "PIR.png", ClassNode.class),
            new Info("PIR.SequenceVariant", "SequenceVariant", "PIR.png", ClassNode.class),
            new Info("PIR.SpliceVariant", "SpliceVariant", "PIR.png", ClassNode.class),
            new Info("PIR.ProteinSequence", "ProteinSequence", "PIR.png", ClassNode.class),
            new Info("PIR.SequenceConflict", "SequenceConflict", "PIR.png", ClassNode.class),
            new Info("PIR.Gene", "Gene", "PIR.png", ClassNode.class),
            new Info("PIR.MutagenesisSite", "MutagenesisSite", "PIR.png", ClassNode.class),
            
            
        };
        
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("caTRIP Services");
        
        DefaultMutableTreeNode serviceOne = new DefaultMutableTreeNode("caCORE");
        
        for (int i = 0; i < 7; i++){
            DefaultMutableTreeNode classn = new DefaultMutableTreeNode(infos[i] );
            DefaultMutableTreeNode attributes = new DefaultMutableTreeNode("Attrinbutes" );
            DefaultMutableTreeNode associations = new DefaultMutableTreeNode("Associations" );
            
            setAttributes(attributes, infos[i]);
            associations.add(new DefaultMutableTreeNode( infos[i]));
            
            classn.add(attributes); classn.add(associations);
            
            serviceOne.add(classn);
            //serviceOne.add(new DefaultMutableTreeNode(infos[i] ));//serviceOne.add(new DefaultMutableTreeNode(infos[i].getName () ));
        }
        
        
        DefaultMutableTreeNode serviceTwo = new DefaultMutableTreeNode("PIR");
        for (int i = 7; i < infos.length; i++){
            DefaultMutableTreeNode classn = new DefaultMutableTreeNode(infos[i] );
            DefaultMutableTreeNode attributes = new DefaultMutableTreeNode("Attrinbutes" );
            DefaultMutableTreeNode associations = new DefaultMutableTreeNode("Associations" );
            
            setAttributes(attributes, infos[i]);
            associations.add(new DefaultMutableTreeNode( infos[i]));
            
            classn.add(attributes); classn.add(associations);
            serviceTwo.add(classn);
            //serviceTwo.add(new DefaultMutableTreeNode(infos[i] ));
        }
        
        root.add(serviceOne);
        root.add(serviceTwo);
        
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        
        
        MyRenderer rr = new MyRenderer(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/tree/icon_cde.png")));
        tree.setCellRenderer(rr);
        ToolTipManager.sharedInstance().registerComponent(tree);
        
        
        
        add(new JScrollPane(tree));
        
        
        
//        DragGestureListener dgListener = new DragGestureListener () {
//            public void dragGestureRecognized (DragGestureEvent dge) {
//                //String info = (String) tree.getSelectionPath().getLastPathComponent().toString();
//                DefaultMutableTreeNode infoLeaf = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
//                Info infof = (Info) infoLeaf.getUserObject();
//                String info = infof.getDisplayName();
//                Transferable transferable = new StringSelection (info);
//                dge.startDrag (null, transferable, null);
//            }
//        };
//        DragSource.getDefaultDragSource ().createDefaultDragGestureRecognizer (tree, DnDConstants.ACTION_COPY_OR_MOVE, dgListener);
//
//        view = GraphFactory.createView (document, new DocumentRenderer (), new ViewController (this), new EventHandler (this));
        
        
        
        
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
    
    
    private void setAttributes(DefaultMutableTreeNode classn, Info inf){
        String nodeName = inf.getName();
        
        
        if (nodeName.startsWith("caCore.Gene")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("clusterId"));
            classn.add(new DefaultMutableTreeNode("symbol"));
            
        }else if (nodeName.startsWith("caCore.Taxon")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("scientificName"));
            classn.add(new DefaultMutableTreeNode("ethnicityStrain"));
            classn.add(new DefaultMutableTreeNode("abbreviation"));
            classn.add(new DefaultMutableTreeNode("commonName"));
            
        }else if (nodeName.startsWith("caCore.Chromosome")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("name"));
            
        }else if (nodeName.startsWith("caCore.Clone")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("name"));
            classn.add(new DefaultMutableTreeNode("accessionNumber"));
            classn.add(new DefaultMutableTreeNode("insertSize"));
            classn.add(new DefaultMutableTreeNode("version"));
            classn.add(new DefaultMutableTreeNode("strain"));
            
        }else if (nodeName.startsWith("caCore.Library")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("name"));
            classn.add(new DefaultMutableTreeNode("type"));
            classn.add(new DefaultMutableTreeNode("unigeneId"));
            classn.add(new DefaultMutableTreeNode("description"));
            classn.add(new DefaultMutableTreeNode("labHost"));
            classn.add(new DefaultMutableTreeNode("keyword"));
            classn.add(new DefaultMutableTreeNode("rsite1"));
            
        }else if (nodeName.startsWith("caCore.Sequence")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("accessionNumber"));
            classn.add(new DefaultMutableTreeNode("description"));
            classn.add(new DefaultMutableTreeNode("length"));
            classn.add(new DefaultMutableTreeNode("accessionNumberVersion"));
            classn.add(new DefaultMutableTreeNode("type"));
            classn.add(new DefaultMutableTreeNode("asciiString"));
            
        }else if (nodeName.startsWith("caCore.Target")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("name"));
            classn.add(new DefaultMutableTreeNode("type"));
            
            
            // other domain model objects..
        }else if (nodeName.startsWith("PIR.Taxon")){
            classn.add(new DefaultMutableTreeNode("id"));
            
        }else if (nodeName.startsWith("PIR.Protein")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("uniprotkbPrimaryAccession"));
            classn.add(new DefaultMutableTreeNode("uniprotkbEntryName"));
            classn.add(new DefaultMutableTreeNode("proteinType"));
            
        }else if (nodeName.startsWith("PIR.SequenceVariant")){
            classn.add(new DefaultMutableTreeNode("originalSequence"));
            classn.add(new DefaultMutableTreeNode("variantSequence"));
            
        }else if (nodeName.startsWith("PIR.SpliceVariant")){
            classn.add(new DefaultMutableTreeNode("originalSequence"));
            classn.add(new DefaultMutableTreeNode("variantSequence"));
            
        }else if (nodeName.startsWith("PIR.ProteinSequence")){
            classn.add(new DefaultMutableTreeNode("id"));
            classn.add(new DefaultMutableTreeNode("checksum"));
            classn.add(new DefaultMutableTreeNode("length"));
            classn.add(new DefaultMutableTreeNode("value"));
            classn.add(new DefaultMutableTreeNode("molecularWeightInDaltons"));
            classn.add(new DefaultMutableTreeNode("sequenceInFastaFormat"));
            
        }else if (nodeName.startsWith("PIR.SequenceConflict")){
            classn.add(new DefaultMutableTreeNode("originalSequence"));
            classn.add(new DefaultMutableTreeNode("variantSequence"));
            
        }else if (nodeName.startsWith("PIR.Gene")){
            classn.add(new DefaultMutableTreeNode("id"));
            
        }else if (nodeName.startsWith("PIR.MutagenesisSite")){
            classn.add(new DefaultMutableTreeNode("originalSequence"));
            classn.add(new DefaultMutableTreeNode("variantSequence"));
        }
        
        
        
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
            } else if( value.toString().equalsIgnoreCase("Attrinbutes") ){
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
