/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * ClassNodeRenderer.java
 *
 * Created on June 5, 2006, 1:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.dnd;


import org.netbeans.graph.api.control.GraphHelper;
import org.netbeans.graph.api.control.IGraphNodeRenderer;
import org.netbeans.graph.api.control.editor.IGraphEditor;
import org.netbeans.graph.api.control.editor.TextFieldEditor;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.ability.IDisplayable;
import org.netbeans.graph.api.model.ability.INameEditable;
import org.netbeans.graph.api.model.ability.IDirectionable;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import edu.duke.cabig.catrip.gui.control.*;


/**
 *
 * @author Sanjeev Agarwal
 */
public class ClassNodeRenderer implements IGraphNodeRenderer {
    
    private static Font font = Font.decode("dialog-bold").deriveFont(12.0f); // NOI18N
    
    private static Color colorFont = new Color(119, 68, 0); // 174, 100
    private static Color colorFontShadow = new Color(0xDEDEDE);
    private static Color colorFontShadowSelected = new Color(0xDEDEDE);
    
    protected static int PORT_SPACE = 20;//16;  // sanjeev
    protected static int PORT_SPACE_2 = 8;
    
    private static final int LAYER_BACKGROUND = 100;
    private static final int LAYER_NODE = 400;
    private int[] layers = layers = new int[] { LAYER_BACKGROUND, LAYER_NODE };
    
    // sanjeev
    private Rectangle ClassrRect;
    
    // sanjeev
    
    
    
    public static Color getFontColor() {
        return colorFont;
    }
    
    public static Color getFontShadowColor() {
        return colorFontShadow;
    }
    
    public static Color getFontShadowSelectedColor() {
        return colorFontShadowSelected;
    }
    
    protected GraphHelper helper;
//    private IGraphNode node;
    
    private Rectangle imageRect;
    protected Rectangle textRect;
    
    public ClassNodeRenderer(GraphHelper helper, IGraphNode node) {
        this.helper = helper;
//        this.node = node;
    }
    
    protected void layoutPortsLocationsCore(IGraphNode node, Graphics2D gr) {
        
        final IDisplayable displayable = (IDisplayable) node.getLookup().lookup(IDisplayable.class);
        Image image = displayable != null ? displayable.getIcon() : null;
        final int imageWidth = image != null ? image.getWidth(null) +20: 0; // extra space between image and points..
        final int imageHeight = image != null ? image.getHeight(null) +10: 0; // extra space between image and points..
        
        Point rightSidePoint = new Point(imageWidth / 2, - imageHeight / 2 + PORT_SPACE_2 );
        Point leftSidePoint = new Point(-rightSidePoint.x, rightSidePoint.y);
        Point bottomLeftSidePoint = new Point(leftSidePoint.x+8, (textRect != null ? (textRect.y + textRect.height) : 0));//sanjeev
        IGraphPort[] ports = helper.getNodePorts(node);
        // sanjeev
//        leftSidePoint.y  =leftSidePoint.y + (imageHeight / 2 +textRect.height + 20);//sanjeev
        // sanjeev
        for (int i = 0; i < ports.length; i++) {
            IGraphPort port = ports[i];
            IDirectionable dir = (IDirectionable) port.getLookup().lookup(IDirectionable.class);
            switch (dir.getDirection()) {
                case IDirectionable.LEFT:
                    leftSidePoint.y -= PORT_SPACE_2;//PORT_SPACE_2;  // sanjeev
                    break;
                case IDirectionable.RIGHT:
                    rightSidePoint.y -= PORT_SPACE_2;
                    break;
                case IDirectionable.BOTTOM_LEFT:
                    bottomLeftSidePoint.y += PORT_SPACE;
                    break;
            }
        }
        
        
//        System.out.println("ssss");
        for (int i = 0; i < ports.length; i++) {
            IGraphPort port = ports[i];
            IDirectionable dir = (IDirectionable) port.getLookup().lookup(IDirectionable.class);
            switch (dir.getDirection()) {
                case IDirectionable.LEFT:
                    helper.setPortRelativeLocation(port, new Point(leftSidePoint));
                    leftSidePoint.y += PORT_SPACE;
                    break;
                case IDirectionable.RIGHT:
                    helper.setPortRelativeLocation(port, new Point(rightSidePoint));
                    rightSidePoint.y += PORT_SPACE;
                    break;
                case IDirectionable.TOP:
                    helper.setPortRelativeLocation(port, new Point(0, - imageHeight));
                    break;
                case IDirectionable.BOTTOM:
                    helper.setPortRelativeLocation(port, new Point(0, (textRect != null ? (textRect.y + textRect.height) : 0)));
                    break;
                case IDirectionable.BOTTOM_LEFT:
                    helper.setPortRelativeLocation(port, new Point(bottomLeftSidePoint )); // sanjeev
                    bottomLeftSidePoint.y -= PORT_SPACE;
//                    helper.setPortRelativeLocation(port, new Point(leftSidePoint.x, (textRect != null ? (textRect.y + textRect.height) : 0))); // sanjeev
                    break;
                default:
                    helper.setPortRelativeLocation(port, new Point(0, 0));
            }
        }
//        System.out.println("sssss");
    }
    
    public void layoutNode(IGraphNode node, Graphics2D gr) {
        
        String displayName = null;
        Image image = null;
        final IDisplayable displayable = (IDisplayable) node.getLookup().lookup(IDisplayable.class);
        if (displayable != null) {
            displayName = displayable.getDisplayName();
            image = displayable.getIcon();
        }
        
        final int imageWidth = image != null ? image.getWidth(null) : 0;
        final int imageHeight = image != null ? image.getHeight(null) : 0;
        
        imageRect = new Rectangle(-imageWidth / 2, -imageHeight, imageWidth, imageHeight);
        
        textRect = null;
        if (displayName != null) {
            final FontMetrics fontMetrics = gr.getFontMetrics(font);
            final int ascent = fontMetrics.getAscent();
            
            textRect = fontMetrics.getStringBounds(displayName, gr).getBounds();
            textRect.width += 2;
            textRect.height += 2;
            textRect.translate(-textRect.width / 2, 2 + ascent);
        }
        
        // sanjeev
        // now set a new ractangle for the attributes..
//        Rectangle attributeRect = new Rectangle(-imageWidth / 2, -imageHeight, imageWidth, imageHeight+105);
        //Rectangle attributeRect = new Rectangle(imageWidth, imageHeight+100);
        // sanjeev
        
        
        layoutPortsLocationsCore(node, gr);
        
        Rectangle bounds = new Rectangle();
        if (textRect != null)
            Rectangle2D.union(imageRect, textRect, bounds);
        else
            bounds.setRect(imageRect);
        //sanjeev
        ClassrRect = bounds.getBounds();
//        Rectangle2D.union(attributeRect, bounds, bounds);
//        oldrect.grow(8,8);
        //sanjeev
        
        helper.includeNodePortsToNodeRelativeBounds(node, bounds);
        bounds.grow(4, 4);
         // TODO - add the attributes portion also into the active area...
        helper.setNodeRelativeActiveAreas(node, new Rectangle[] { imageRect, textRect } );
//        helper.setNodeRelativeActiveAreas(node, new Rectangle[] { imageRect, textRect, attributeRect } ); // sanjeev
        helper.setNodeRelativeBounds(node, bounds);
        
    }
    
    public Point locationSuggested(IGraphNode node, Point suggestedLocation) {
        return suggestedLocation;
    }
    
    public int[] getLayers(IGraphNode node) {
        return layers;
    }
    
    public void renderNode(IGraphNode node, Graphics2D gr, int layer) {
        Point location = helper.getNodeLocation(node);
        
        ClassNode cNode = (ClassNode)node.getLookup().lookup(ClassNode.class);
        
        if (layer == LAYER_BACKGROUND) {
            //sanjeev drawing a box covering the node..
            Rectangle rect = helper.getBounds(node);
            
            
            // set the color for the target background border...
            if ( cNode.isTargetNode() ){
                gr.setColor(new Color(255,235,155)); //  blue : //210,210,255 , green :236,247,193, orange : 255,235,155
            } else {
                gr.setColor(new Color(0xEEEEEE)); 
            }
            gr.fillRect(rect.x, rect.y, rect.width, rect.height);
            
            // set the color for the target node border...
            if ( cNode.isTargetNode() ){
                gr.setColor(Color.RED);  
            } else {
                gr.setColor(new Color(0xCDCDCD));  // border color..
            }
            // draw the border for the node here....
            gr.draw(new Rectangle2D.Float(rect.x + 0.5f, rect.y + 0.5f, rect.width - 1, rect.height - 1));
            //sanjeev now drawing a ract for a line between attributes..
            gr.draw(new Rectangle2D.Float(rect.x + 0.5f , rect.y + 0.5f , rect.width - 1 , ClassrRect.height +4 ));
            // sanjeev
            if (helper.isComponentSelected(node))
                SimpleDocumentRenderer.renderSelectedRect(gr, helper.getBounds(node));
        } else if (layer == LAYER_NODE) {
            String displayName = null;
            Image image = null;
            final IDisplayable displayable = (IDisplayable) node.getLookup().lookup(IDisplayable.class);
            if (displayable != null) {
                displayName = displayable.getDisplayName();
                image = displayable.getIcon();
            }
            
            if (image != null) {
                final Rectangle imageRect = new Rectangle(this.imageRect);
                imageRect.translate(location.x, location.y);
                final IGraphPort defaultPort = helper.getNodeDefaultPort(node);
                if (helper.isComponentMouseOver(node)  ||  helper.isComponentMouseOver(defaultPort)) {
                    Composite composite = gr.getComposite();
                    gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
                    gr.drawImage(image, imageRect.x, imageRect.y, null);
                    gr.setComposite(composite);
                } else
                    gr.drawImage(image, imageRect.x, imageRect.y, null);
            }
            
            gr.setFont(font);
            
            if (displayName != null) {
                final FontMetrics fontMetrics = gr.getFontMetrics(font);
                final int ascent = fontMetrics.getAscent();
                final Rectangle textRect = new Rectangle(this.textRect);
                textRect.translate(location.x, location.y);
                final boolean selected = helper.isComponentSelected(node);
                
                if (selected) {
                    gr.setColor(colorFontShadowSelected);
                    gr.drawString(displayName, textRect.x + 1, textRect.y + 1 + ascent);
                } else {
                    gr.setColor(colorFontShadow);
                    gr.drawString(displayName, textRect.x + 2, textRect.y + 2 + ascent);
                }
                gr.setColor(colorFont);
                gr.drawString(displayName, textRect.x, textRect.y + ascent);
                //sanjeev
//                gr.setColor(new Color(174, 100, 0)); //
//                drawAttributes(gr, node.getID(), helper.getBounds(node).x+ 15, textRect.y + 1 + ascent+25);
                //sanjeev
            }
        }
    }
    
    public IGraphEditor getEditor(IGraphNode node, Point position) {
        if (this.textRect == null)
            return null;
        final Rectangle rectangle = new Rectangle(this.textRect);
        final Point location = helper.getNodeLocation(node);
        rectangle.translate(location.x, location.y);
        final INameEditable nameEditable = (INameEditable) node.getLookup().lookup(INameEditable.class);
        if (rectangle.contains(position)  &&  nameEditable != null  &&  nameEditable.canRename()) {
            return new TextFieldEditor() {
                public String getValue() {
                    final String name = nameEditable.getName();
                    return name != null ? name : NbBundle.getMessage(ClassNodeRenderer.class, "TXT_IconNodeDriver_EnterName"); // NOI18N
                }
                
                public void setValue(String value) {
                    nameEditable.setName(value);
                }
                
                public Rectangle getActiveArea() {
                    return rectangle;
                }
                
                public void notifyAttached(IGraphEditor.EditorPresenter presenter) {
                    super.notifyAttached(presenter);
                    
                    final int height = getComponent().getPreferredSize().height;
                    getComponent().setMinimumSize(new Dimension(64, height));
                    getComponent().setMaximumSize(new Dimension(256, height));
                    getComponent().setPreferredSize(new Dimension(128, height));
                }
            };
        }
        return null;
    }
    
    public String getToolTipText(IGraphNode node, Point position) {
        final IDisplayable displayable = (IDisplayable) node.getLookup().lookup(IDisplayable.class);
        return displayable != null ? displayable.getTooltipText() : null;
    }
    
    public void portLocationSuggested(IGraphPort port, Point suggestedLocation) {
        // TODO
    }
    
    
    // sanjeev
    // my junk methods...
    private void drawAttributes(Graphics2D gr, String nodeName, int xx, int yy){
        // keep x same and y increment with every text string
        //System.out.println("XXXX "+ yy);
        Image imagePort = Utilities.loadImage("org/netbeans/graph/examples/control/resources/port.gif");
        gr.drawImage(imagePort, xx-10, yy-8, null);
        
        if (nodeName.startsWith("caCore.Gene")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("clusterId", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("symbol", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Taxon")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("ethnicityStrain", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("commonName", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Chromosome")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("name", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Clone")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("insertSize", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("strain", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Library")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("type", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("unigeneId", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("keyword", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Sequence")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("accessionNumber", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("length", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("type", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("caCore.Target")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("name", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("type", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("PIR.Taxon")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("PIR.Gene")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }else if (nodeName.startsWith("PIR.ProteinSequence")){
            gr.drawString("id", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("checksum", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("length", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            yy=yy+20; gr.drawString("value", xx, yy);gr.drawImage(imagePort, xx-10, yy-8, null);
            
        }
        
    }
    
    
    
    // sanjeev
    
    
}