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

import org.netbeans.graph.api.control.GraphHelper;
import org.netbeans.graph.api.model.IGraphNode;
import org.w3c.dom.Document;
import org.openide.xml.XMLUtil;
import org.xml.sax.InputSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

import java.awt.*;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author David Kaspar
 */
public class XMLSerializerTest {

    private static String getAttributeValue (org.w3c.dom.Node node, String attr) {
        try {
            if (node != null) {
                org.w3c.dom.NamedNodeMap map = node.getAttributes ();
                if (map != null) {
                    node = map.getNamedItem (attr);
                    if (node != null)
                        return node.getNodeValue ();
                }
            }
        } catch (org.w3c.dom.DOMException e) {
        }
        return null;
    }

    private static void setAttribute (org.w3c.dom.Document doc, org.w3c.dom.Node node, String name, String value) {
        if (doc == null || node == null || name == null || value == null)
            return;
        org.w3c.dom.NamedNodeMap map = node.getAttributes ();
        org.w3c.dom.Attr attribute = doc.createAttribute (name);
        attribute.setValue (value);
        map.setNamedItem (attribute);
    }

    /**
     * This methods uses deserializes an XML node with
     * @param rootNode
     */
    // WARNING  - all GraphComponentPeer instance must be loaded from IPresenterDocument already
    public static synchronized void readXMLTree (GraphHelper helper, org.w3c.dom.Node rootNode) {
        Object lock = helper.beginTransaction (false);

        if (rootNode != null) {
            String version = getAttributeValue (rootNode, "version"); // NOI18N
            if (!"1.0".equals (version)) // NOI18N
                return;
            HashMap map = new HashMap ();
            org.w3c.dom.NodeList list = rootNode.getChildNodes ();
            for (int a = 0; a < list.getLength (); a++) {
                org.w3c.dom.Node node = list.item (a);
                if ("Node".equals (node.getNodeName ())) { // NOI18N
                    try {
                        String id = getAttributeValue (node, "id"); // NOI18N
                        int x = Integer.parseInt (getAttributeValue (node, "x")); // NOI18N
                        int y = Integer.parseInt (getAttributeValue (node, "y")); // NOI18N
                        map.put (id, new Point (x, y));
                    } catch (NumberFormatException e) {
                    }
                }
            }
            final IGraphNode[] nodes = helper.getNodes ();
            for (int i = 0; i < nodes.length; i++) {
                IGraphNode node = nodes[i];
                Point point = (Point) map.get (node.getID ());
                if (point != null) {
                    helper.setNodeLocation (node, point);
                    helper.notifyNodeLocationLoaded (node);
                }
            }
        }
        helper.recalculate ();

        helper.endTransaction (lock);
    }

    public static synchronized org.w3c.dom.Node writeXMLTree (GraphHelper helper, org.w3c.dom.Document doc, String rootNodeName) {
        org.w3c.dom.Node rootNode = rootNodeName != null ? doc.createElement (rootNodeName) : doc.getFirstChild ();
        setAttribute (doc, rootNode, "version", "1.0"); // NOI18N
        final IGraphNode[] nodes = helper.getNodes ();
        for (int i = 0; i < nodes.length; i++) {
            IGraphNode node = nodes[i];
            String id = node.getID ();
            Point location = helper.getNodeLocation (node);
            if (id == null || ! helper.isNodeLocationResolved (node))
                continue;
            org.w3c.dom.Node dn = doc.createElement ("Node"); // NOI18N
            setAttribute (doc, dn, "id", id); // NOI18N
            setAttribute (doc, dn, "x", Integer.toString (location.x)); // NOI18N
            setAttribute (doc, dn, "y", Integer.toString (location.y)); // NOI18N
            rootNode.appendChild (dn);
        }
        return rootNode;
    }

    public static Document readXMLDocument (InputStream is) throws IOException {
        Document doc = null;
        try {
            doc = XMLUtil.parse (new InputSource (is), false, false, new ErrorHandler () {
                public void error (SAXParseException e) throws SAXException {
                    throw new SAXException (e);
                }

                public void fatalError (SAXParseException e) throws SAXException {
                    throw new SAXException (e);
                }

                public void warning (SAXParseException e) throws SAXException {
                    e.printStackTrace ();
                }
            }, null);
        } catch (SAXException e) {
            e.printStackTrace ();
        } finally {
            try {
                is.close ();
            } catch (IOException e) {
            }
        }
        return doc;
    }

    public static void writeXMLDocument (OutputStream os, Document doc) throws IOException {
        try {
            XMLUtil.write (doc, os, "UTF-8"); // NOI18N
        } finally {
            if (os != null)
                try {
                    os.close ();
                } catch (IOException e) {
                }
        }
    }

}
