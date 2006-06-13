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
package edu.duke.cabig.catrip.gui.control;

import edu.duke.cabig.catrip.gui.util.XMLSerializerTest;
import org.netbeans.graph.api.control.builtin.DefaultViewController;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.GraphFactory;
import org.openide.util.NbBundle;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * @author David Kaspar
 */
// TODO - reimplement
public class SimpleViewController extends DefaultViewController implements ActionListener {

    protected static final String ACTION_COMMAND_SELECT_ALL = "SELECT_ALL"; // NOI18N
//    protected static final String ACTION_COMMAND_PASTE = "PASTE"; // NOI18N
    protected static final String ACTION_COMMAND_LOAD = "LOAD"; // NOI18N
    protected static final String ACTION_COMMAND_SAVE = "SAVE"; // NOI18N
    protected static final String ACTION_COMMAND_REALIGN = "REALIGN"; // NOI18N
    protected static final String ACTION_COMMAND_PRINT = "PRINT"; // NOI18N

    public JPopupMenu getPopupMenu() {
        JPopupMenu popup = new JPopupMenu();

        JMenuItem miSelectAll = new JMenuItem ();
        miSelectAll.setText (NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_SelectAll")); // NOI18N
        miSelectAll.setActionCommand (ACTION_COMMAND_SELECT_ALL);
        miSelectAll.addActionListener (this);
        popup.add(miSelectAll);
        JMenuItem miLoad = new JMenuItem ();
        miLoad.setText (NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_Load")); // NOI18N
        miLoad.setActionCommand (ACTION_COMMAND_LOAD);
        miLoad.addActionListener (this);
        popup.add (miLoad);
        JMenuItem miSave = new JMenuItem ();
        miSave.setText (NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_Save")); // NOI18N
        miSave.setActionCommand (ACTION_COMMAND_SAVE);
        miSave.addActionListener (this);
        popup.add (miSave);
        JMenuItem miRealign = new JMenuItem ();
        miRealign.setText (NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_Realign")); // NOI18N
        miRealign.setActionCommand (ACTION_COMMAND_REALIGN);
        miRealign.addActionListener (this);
        popup.add (miRealign);
        JMenuItem miPrint = new JMenuItem ();
        miPrint.setText (NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_Print")); // NOI18N
        miPrint.setActionCommand (ACTION_COMMAND_PRINT);
        miPrint.addActionListener (this);
        popup.add (miPrint);

//        JMenuItem miPaste = new JMenuItem ();
//        miPaste.setText (ExternalHelper.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_Paste")); // NOI18N
//        miPaste.setActionCommand (ACTION_COMMAND_PASTE);
//        miPaste.addActionListener (this);
//        miPaste.setEnabled (document.isSomethingToPaste ());
//        popup.add(miPaste);

        return popup;
    }

    // TODO see comment
    /**
     * Called when key is pressed on view.
     * There is issue with handling of key shortcuts here - it may not work on some platforms. The impl for standalone
     * module should get this class as an example, moving functionality to be NetBeans specific (handled by DeleteAction and so on)
     * @param modifiersEx
     * @param keyCode
     * @return true if key is consumed
     */
    public boolean keyPressed (int modifiersEx, int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                if ((modifiersEx & KeyEvent.CTRL_DOWN_MASK) != 0) {
                    selectAllActionInvoked ();
                    return true;
                }
                break;
//            case KeyEvent.VK_V:
//                if ((modifiersEx & KeyEvent.CTRL_DOWN_MASK) != 0) {
//                    pasteActionInvoked ();
//                    return true;
//                }
//                break;
            case KeyEvent.VK_TAB: {
                    IGraphNode[] nodes = getHelper ().getNodes ();
                    Object[] selected = getHelper ().getSelectedComponents ();
                    IGraphNode node = findFirstNodeAfter (selected, null);
                    IGraphNode nodeToSelect;
                    if ((modifiersEx & KeyEvent.SHIFT_DOWN_MASK) != 0) {
                        nodeToSelect = findFirstNodeBefore (nodes, node);
                        if (nodeToSelect == null)
                            nodeToSelect = findFirstNodeBefore (nodes, null);
                    } else {
                        nodeToSelect = findFirstNodeAfter (nodes, node);
                        if (nodeToSelect == null)
                            nodeToSelect = findFirstNodeAfter (nodes, null);
                    }
                    if (nodeToSelect != null)
                        getViewPresenter ().selectComponents (new IGraphNode[] { nodeToSelect });
                }
                return true;
            case KeyEvent.VK_ENTER: {
                    Object[] components = getHelper ().getSelectedComponents ();
                    if (components != null  &&  components.length > 0  &&  components[0] instanceof IGraphNode)
                        componentDoubleClicked (components[0], getHelper ().getNodeLocation ((IGraphNode) components[0]));
                }
                return true;
            case KeyEvent.VK_L:
                if ((modifiersEx & KeyEvent.CTRL_DOWN_MASK) == 0)
                    break;
            case KeyEvent.VK_F3:
                load ();
                return true;
            case KeyEvent.VK_S:
                if ((modifiersEx & KeyEvent.CTRL_DOWN_MASK) == 0)
                    break;
            case KeyEvent.VK_F2:
                save ();
                return true;
        }
        return false;
    }

    private IGraphNode findFirstNodeBefore (Object[] components, Object component) {
        if (components == null)
            return null;
        int found = components.length;
        if (component != null) {
            for (int i = components.length - 1; i >= 0; i --) {
                if (components[i] == component) {
                    found = i;
                    break;
                }
            }
        }
        for (found --; found >= 0; found --) {
            final Object node = components[found];
            if (node instanceof IGraphNode)
                return (IGraphNode) node;
        }
        return null;
    }

    private IGraphNode findFirstNodeAfter (Object[] components, Object component) {
        if (components == null)
            return null;
        int found = -1;
        if (component != null) {
            for (int i = 0; i < components.length; i++) {
                if (components[i] == component) {
                    found = i;
                    break;
                }
            }
        }
        for (found++; found < components.length; found++) {
            final Object node = components[found];
            if (node instanceof IGraphNode)
                return (IGraphNode) node;
        }
        return null;
    }

    public boolean componentPeerClicked (Object component, Point point) {
        return false;
    }

    public boolean componentDoubleClicked (Object component, Point point) {
        return false;
    }

    public void actionPerformed (ActionEvent e) {
        if (ACTION_COMMAND_SELECT_ALL.equals (e.getActionCommand ()))
            selectAllActionInvoked ();
        else if (ACTION_COMMAND_LOAD.equals (e.getActionCommand ()))
            load ();
        else if (ACTION_COMMAND_SAVE.equals (e.getActionCommand ()))
            save ();
        else if (ACTION_COMMAND_REALIGN.equals (e.getActionCommand ()))
            GraphFactory.layoutNodes (getViewPresenter ().getView ());
        else if (ACTION_COMMAND_PRINT.equals (e.getActionCommand ()))
            print ();
//        else if (ACTION_COMMAND_PASTE.equals (e.getActionCommand ()))
//            pasteActionInvoked ();
    }

    protected void selectAllActionInvoked () {
        ArrayList list = new ArrayList ();
        list.addAll (Arrays.asList (getHelper ().getNodes ()));
        list.addAll (Arrays.asList (getHelper ().getLinks ()));
        getViewPresenter ().selectComponents (list.toArray (new Object[list.size ()]));
    }

//    protected void pasteActionInvoked () {
//        final GraphDocumentPeer documentPeer = getViewPresenter ().getDocumentPeer ();
//        documentPeer.paste ();
//    }

    protected void load () {
        String value = JOptionPane.showInputDialog (null, NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_LoadFile"), "/tmp/file.xml"); // NOI18N
        if (value == null)
            return;
        try {
            Document document = XMLSerializerTest.readXMLDocument (new FileInputStream (value));
            Node rootNode = document != null ? document.getFirstChild () : null;
            if (rootNode != null)
                XMLSerializerTest.readXMLTree (getHelper (), rootNode);
        } catch (IOException e) {
            e.printStackTrace (); // TODO
        }
    }

    private void save () {
        String value = JOptionPane.showInputDialog (null, NbBundle.getMessage (SimpleViewController.class, "LBL_SimpleViewDriver_SaveAsFile"), "/tmp/file.xml"); // NOI18N
        if (value == null)
            return;
        try {
            Document document = XMLUtil.createDocument ("document", null, null, null);
            XMLSerializerTest.writeXMLTree (getHelper (), document, null);
            XMLSerializerTest.writeXMLDocument (new FileOutputStream (value), document);
        } catch (IOException e) {
            e.printStackTrace (); // TODO
        }
    }

    private void print () {
        final JComponent view = getViewPresenter ().getView ();
        final int width = view.getWidth ();
        final int height = view.getHeight ();
        final PrinterJob printerJob = PrinterJob.getPrinterJob ();
        final PageFormat pageFormat = printerJob.defaultPage ();

        pageFormat.setOrientation (width > height ? PageFormat.LANDSCAPE : PageFormat.PORTRAIT);
        printerJob.setPrintable (new Printable () {
            public int print (Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex != 0)
                    return Printable.NO_SUCH_PAGE;
                Graphics2D gr = (Graphics2D) graphics;

                AffineTransform at = gr.getTransform ();
                double scale = Math.min (pageFormat.getImageableWidth () / width, pageFormat.getImageableHeight () / height);
                gr.translate (pageFormat.getImageableX (), pageFormat.getImageableY ());
                gr.scale (scale, scale);

                view.print (graphics);

                at.setTransform (at);
                return Printable.PAGE_EXISTS;
            }
        }, pageFormat);

        if (printerJob.printDialog ()) {
            try {
                printerJob.print ();
            } catch (PrinterException e) {
                e.printStackTrace (); // TODO
            }
        }
    }

}
