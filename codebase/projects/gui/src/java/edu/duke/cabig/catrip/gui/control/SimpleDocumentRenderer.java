/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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

import org.netbeans.graph.api.control.*;
import org.netbeans.graph.api.control.builtin.BreadthFirstNodesLayouter;
import org.netbeans.graph.api.control.builtin.OrthogonalLinkRouter;
import org.netbeans.graph.api.control.editor.IGraphEditor;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.control.builtin.AnimatedNodesLayouter;
import org.openide.util.Utilities;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * @author David Kaspar
 */
public class SimpleDocumentRenderer implements IGraphDocumentRenderer {

//    private static final Image imagePaperCorner = Utilities.loadImage ("org/netbeans/graph/examples/resources/paper_corner.gif"); // NOI18N
//    private static final Image imagePaperTop = Utilities.loadImage ("org/netbeans/graph/examples/resources/paper_top.gif"); // NOI18N
//    private static final Image imagePaperLeft = Utilities.loadImage ("org/netbeans/graph/examples/resources/paper_left.gif"); // NOI18N
//    private static final int imagePaperWidth = imagePaperTop.getHeight (null);
//    private static final int imagePaperTileSize = imagePaperTop.getWidth (null);
    protected static final Font titleFont = new Font ("SansSerif", Font.BOLD, 16); // NOI18N
//    private static final Font titleSpaceDefinitionFont = new Font ("SansSerif", Font.BOLD, 24); // NOI18N
    protected static final int PAPER_HEADER_DEFAULT_HEIGHT = 32;

    protected static final Color colorPaperTop = new Color (255, 255, 255);
    protected static final Color colorPaperTopLine = new Color (250, 232, 213);
    protected static final Color colorPaperCenter = new Color (252, 250, 245);
    protected static final Color colorPaperTopLabel = new Color (213, 211, 204);
    protected static final Color colorPaperGridPoint = new Color (170, 164, 151);

    protected static final Color selectionBackground = new Color (0xEEEEEE);
    protected static final Color selectionForeground = Color.BLUE ;//new Color (0xCDCDCD); // sanjeev
    protected static final Color altBackground = new Color (0xEE4040);
    protected static final Color altForeground = new Color (0xCD0000);

    static final Image IMAGE_PAPER_GRID = Utilities.loadImage ("edu/duke/cabig/catrip/gui/control/resources/paper_grid.png"); // NOI18N
    static final int IMAGE_PAPER_GRID_HEIGHT = IMAGE_PAPER_GRID.getHeight (null);
    static final int IMAGE_PAPER_GRID_WIDTH = IMAGE_PAPER_GRID.getWidth (null);
//    protected static int GRID_SIZE = 16;

    protected GraphHelper helper;

    protected String title;
    private final IGraphNodesLayouter nodesLayouter = new AnimatedNodesLayouter (new BreadthFirstNodesLayouter () {
        protected IGraphNode getRootNode (GraphHelper helper) {
            final Object[] selectedComponents = helper.getSelectedComponents ();
            if (selectedComponents != null  &&  selectedComponents.length == 1  &&  selectedComponents[0] instanceof IGraphNode)
                return (IGraphNode) selectedComponents[0];
            final IGraphNode[] nodes = helper.getNodes ();
            return nodes != null  &&  nodes.length > 0 ? nodes[0] : null;
        }
    });
    private final OrthogonalLinkRouter orthogonalLinkRouter = new OrthogonalLinkRouter ();


    public SimpleDocumentRenderer () {
    }

    public void notifyAttached (GraphHelper helper) {
        this.helper = helper;
    }

    public void notifyDettached (GraphHelper helper) {
        if (this.helper == helper)
            this.helper = null;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public static void renderSelectedRect (Graphics2D gr, Rectangle rect) {
        if (rect == null)
            return;
//        gr.setColor (selectionBackground); // sanjeev
//        gr.fillRect (rect.x, rect.y, rect.width, rect.height);// sanjeev
        gr.setColor (selectionForeground);
        gr.draw (new Rectangle2D.Float (rect.x + 0.5f, rect.y + 0.5f, rect.width - 1, rect.height - 1));
    }

    public static void renderAltRect (Graphics2D gr, Rectangle rect) {
        if (rect == null)
            return;
        gr.setColor (altBackground);
        gr.fillRect (rect.x, rect.y, rect.width, rect.height);
        gr.setColor (altForeground);
        gr.draw (new Rectangle2D.Float (rect.x + 0.5f, rect.y + 0.5f, rect.width - 1, rect.height - 1));
    }

    public static Color getSelectionBackgroundColor () {
        return selectionBackground;
    }

    public static Color getSelectionForegroundColor () {
        return selectionForeground;
    }

    public static Color getPaperColor () {
        return colorPaperCenter;
    }

    protected int getPaperHeaderHeight (Graphics2D gr) {
        return title != null ? PAPER_HEADER_DEFAULT_HEIGHT : -1;
    }

    protected void renderPaperHeader (Graphics2D gr) {
        if (title != null) {
            gr.setColor (colorPaperTopLabel);
            gr.setFont (titleFont);
            gr.drawString (title, 40, /*imagePaperWidth + */(3 * PAPER_HEADER_DEFAULT_HEIGHT) / 4/* - fontMetrics.getDescent ()*/);
            gr.setColor (colorPaperTop);
            Rectangle clip = gr.getClipBounds ();
            gr.fillRect (clip.x, 0, clip.width, PAPER_HEADER_DEFAULT_HEIGHT + 1);
            gr.setColor (colorPaperTopLine);
            gr.fillRect (clip.x, PAPER_HEADER_DEFAULT_HEIGHT, clip.width, PAPER_HEADER_DEFAULT_HEIGHT + 1);
        }
    }

    public void renderPaper (Graphics2D gr) {
        Rectangle clip = gr.getClipBounds ();
        AffineTransform at = gr.getTransform ();

        if (helper.isSnapToGrid () && at.getScaleX () > 0.7 && at.getScaleY () > 0.7) {
            final int gridX = IMAGE_PAPER_GRID_WIDTH;
            final int gridY = IMAGE_PAPER_GRID_HEIGHT;

            int fromx = (clip.x / gridX) * gridX;
            int tox = ((clip.x + clip.width) / gridX + 1) * gridX;
            int fromy = (clip.y / gridY) * gridY;
            int toy = ((clip.y + clip.height) / gridY + 1) * gridY;

            for (int posy = fromy; posy <= toy; posy += gridY)
                for (int posx = fromx; posx <= tox; posx += gridX)
                    gr.drawImage (IMAGE_PAPER_GRID, posx, posy, null);
        } else {
            int paperTopHeight = getPaperHeaderHeight (gr);
            gr.setColor (colorPaperCenter);
            gr.fillRect (clip.x, /*imagePaperWidth + */paperTopHeight + 1, clip.width, clip.y + clip.height);
        }

        renderPaperHeader (gr);

//        for (int count = (clip.x + clip.width + imagePaperTileSize - 1) / imagePaperTileSize, pos = 0; count > 0; count--, pos += imagePaperTileSize)
//            gr.drawImage (imagePaperTop, pos, 0, null);
//        for (int count = (clip.y + clip.height + imagePaperTileSize - 1) / imagePaperTileSize, pos = 0; count > 0; count--, pos += imagePaperTileSize)
//            gr.drawImage (imagePaperLeft, 0, pos, null);
//        gr.drawImage (imagePaperCorner, 0, 0, null);
    }

    public void renderSelectionRectangle (Graphics2D gr, Rectangle selectionRectangle) {
        Composite composite = gr.getComposite ();
        gr.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_ATOP, 0.5f));
        renderSelectedRect (gr, selectionRectangle);
        gr.setComposite (composite);
    }

    public Rectangle getRelinkingRectangle (IGraphLink link, IGraphPort sourcePort, IGraphPort targetPort, Point sourcePoint, Point targetPoint) {
        if (sourcePoint == null || targetPoint == null)
            return null;
        Rectangle sourceRect = new Rectangle (sourcePoint);
        Rectangle targetRect = new Rectangle (targetPoint);
        Rectangle2D.union (sourceRect, targetRect, sourceRect);
        sourceRect.grow (8, 8);
        if (sourceRect.width < 32)
            sourceRect.grow (16, 0);
        if (sourceRect.height < 32)
            sourceRect.grow (0, 16);
        return sourceRect;
    }

    public void renderRelinking (Graphics2D gr, IGraphLink link, IGraphPort sourcePort, IGraphPort targetPort, Point sourcePoint, Point targetPoint) {
        if (sourcePoint == null || targetPoint == null)
            return;
        int[] xs = new int[]{sourcePoint.x, targetPoint.x};
        int[] ys = new int[]{sourcePoint.y, targetPoint.y};
        gr.setColor (SimpleLinkRenderer.relinkingColor);
        SimpleLinkRenderer.paintLink (gr, xs, ys, 2);
    }

    public boolean isAcceptable (DataFlavor[] dataFlavors, IGraphNode node, Point location) {
        return helper.getEventHandler ().isAcceptable ((IGraphNode) helper.getModelComponent (node), dataFlavors);
    }

    public void accept (Transferable transferable, IGraphNode node, Point location) {
        helper.setNewNodePosition (location);
        helper.getEventHandler ().accept ((IGraphNode) helper.getModelComponent (node), transferable);
        helper.setNewNodePosition (null);
    }

    public IGraphEditor getEditor (Point position) {
        return null;
    }

    public String getToolTipText (Point position) {
        return null;
    }

    public IGraphNodesLayouter getNodesLayouter () {
        return nodesLayouter;
    }

    public IGraphNodeRenderer getNodeRenderer (IGraphNode node) {
        return new IconNodeRenderer (helper, node);
    }

    public IGraphPortRenderer getPortRenderer (IGraphPort port) {
        return new IconPortRenderer (helper, port);
    }

    public IGraphLinkRenderer getLinkRenderer (IGraphLink link) {
//        return new SimpleLinkRenderer (helper, link);
        return new OrthogonalLinkRouterLinkRenderer (helper, link, orthogonalLinkRouter);
    }

    public IGraphLinkRouter getLinkRouter (IGraphLink link) {
        return orthogonalLinkRouter;
    }

}
