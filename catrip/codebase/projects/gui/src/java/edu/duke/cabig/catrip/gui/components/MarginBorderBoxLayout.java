/*
 * MarginBorderBoxLayout.java
 *
 * Created on December 27, 2006, 11:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.SizeRequirements;

/**
 *
 * @author Sanjeev Agarwal
 */
public class MarginBorderBoxLayout extends PreferredHeightBoxLayout { 
    
    
    public MarginBorderBoxLayout(Container target, int axis) {
        super(target, axis);
    }
    
    public void layoutContainer(Container target) {
        
        checkContainer(target);
        int nChildren = target.getComponentCount();
        int[] xOffsets = new int[nChildren];
        int[] xSpans = new int[nChildren];
        int[] yOffsets = new int[nChildren];
        int[] ySpans = new int[nChildren];
        
        Dimension alloc = target.getSize();
        Insets in = target.getInsets();
        
        alloc.width -= in.left + in.right;
        alloc.height -= in.top + in.bottom;
        
        // Resolve axis to an absolute value (either X_AXIS or Y_AXIS)
        ComponentOrientation o = target.getComponentOrientation();
        int absoluteAxis = resolveAxis( axis, o ); 
        boolean ltr = (absoluteAxis != axis) ? o.isLeftToRight() : true;
        
        
        // determine the child placements
        synchronized(this) {
            checkRequests();
            
            // this is the place where the height is set to the preferred height irrespective of the available height.
            
            long pref = 0;
            for (int i = 0; i < yChildren.length; i++) {
                pref += yChildren[i].preferred;
            }
            if (alloc.height >= pref) {
                alloc.height = (int) pref-1;
            }
            
            
            
            if (absoluteAxis == X_AXIS) {
                SizeRequirements.calculateTiledPositions(alloc.width, xTotal,
                        xChildren, xOffsets,
                        xSpans, ltr);
                SizeRequirements.calculateAlignedPositions(alloc.height, yTotal,
                        yChildren, yOffsets,
                        ySpans);
            } else {
                SizeRequirements.calculateAlignedPositions(alloc.width, xTotal,
                        xChildren, xOffsets,
                        xSpans, ltr);
                SizeRequirements.calculateTiledPositions(alloc.height, yTotal,
                        yChildren, yOffsets,
                        ySpans); // calculateTiledPositions
            }
        }
        
        
        // Here you set the margin, 5 pix each side. 
        // Extra space is achieved by increasing the prefered size of the Container by 10 pix in both direction.

        for (int i = 0; i < nChildren; i++) {
            Component c = target.getComponent(i);
            if (c instanceof JPanel){
                xOffsets[i] = xOffsets[i]+5;
                yOffsets[i] = yOffsets[i]+5;
                xSpans[i] = xSpans[i] -10;
                ySpans[i] = ySpans[i] -5;
            }
        }
        
        
        // flush changes to the container
        for (int i = 0; i < nChildren; i++) {
            Component c = target.getComponent(i);
            c.setBounds((int) Math.min((long) in.left + (long) xOffsets[i], Integer.MAX_VALUE),
                    (int) Math.min((long) in.top + (long) yOffsets[i], Integer.MAX_VALUE),
                    xSpans[i], ySpans[i]);
            
        }
        if (dbg != null) {
            for (int i = 0; i < nChildren; i++) {
                Component c = target.getComponent(i);
                dbg.println(c.toString());
                dbg.println("X: " + xChildren[i]);
                dbg.println("Y: " + yChildren[i]);
            }
        }
        
    }
    
    
    
    
    
}
