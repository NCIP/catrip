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
package org.netbeans.graph.examples;

import java.beans.PropertyChangeListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.netbeans.graph.api.GraphFactory;
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.examples.dnd.*;
import org.netbeans.graph.examples.dnd.ClassNode;

import javax.swing.*; 
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.tree.DefaultMutableTreeNode;



/**
 * @author David Kaspar
 */ 
public class DNDTreeTest {

    private final Info[] infos = new Info[] {
        
        new Info ("Gene", null, "caGrid.png", ClassNode.class),
        new Info ("Taxon", null, "caGrid.png", ClassNode.class),
        
//        new Info ("Mobile Device", null, null, ClassNode.class),
//        new Info ("Displayable", null, null, ClassNode.class),
//        new Info ("Custom Displayable", null, null, ScreenNode.class),
//        new Info ("Canvas", null, null, ScreenNode.class),
//        new Info ("Form", null, null, ScreenNode.class),
//        new Info ("List", null, null, ScreenNode.class),
//        new Info ("User Code", null, "trigger.png", TriggerNode.class),

    };

    private int lastID = 0;

    private GraphDocument document;
    private DefaultListModel model;

    private JComponent view;
    
    private JTree tree;
    

    public DNDTreeTest () {
        document = new GraphDocument ();


        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sample Service");
        for (int i = 0; i < infos.length; i++)
            root.add(new DefaultMutableTreeNode(infos[i] ));//root.add(new DefaultMutableTreeNode(infos[i].getName () ));
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
        
        DragGestureListener dgListener = new DragGestureListener () {
            public void dragGestureRecognized (DragGestureEvent dge) {
                //String info = (String) tree.getSelectionPath().getLastPathComponent().toString();
                DefaultMutableTreeNode infoLeaf = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                Info infof = (Info) infoLeaf.getUserObject();
                String info = infof.getDisplayName();
                Transferable transferable = new StringSelection (info); 
                dge.startDrag (null, transferable, null);
            }
        };
        DragSource.getDefaultDragSource ().createDefaultDragGestureRecognizer (tree, DnDConstants.ACTION_COPY_OR_MOVE, dgListener);

        //view = GraphFactory.createView (document, new DocumentRenderer (), new ViewController (this), new EventHandler (this));
    }

    public GraphDocument getDocument () {
        return document;
    }

    public Info[] getInfos () {
        return infos;
    }

    public String createID (String prefix) {
        return prefix + ++ lastID;
    }


    private void show () {
        JSplitPane pane = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, new JScrollPane (tree), new JScrollPane (view));
        pane.setDividerLocation (200);

        JDialog dialog = new JDialog ();
        dialog.getContentPane ().add (pane);
        dialog.setSize (750, 550);
        dialog.setVisible (true);
        dialog.addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit (0);
            }
        });
    }


    public static void main (String[] args) {
        final DNDTreeTest dndTest = new DNDTreeTest ();
        dndTest.show ();
    }

}
