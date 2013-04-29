/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.util;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;



/**
 *
 * @author Sanjeev Agarwal
 */
public class SwingUtils {
    static Color[] presetColors =
            new Color[]{Color.BLUE, Color.RED, Color.BLACK,
            new Color(0,64,0), new Color(64,0,128),
            Color.PINK,Color.YELLOW, Color.DARK_GRAY};
    
    static int usedColor=0;
    
    
    
    public static java.awt.Image getTextAsImage(String strr){
        return getTextAsImage(strr, Color.BLUE );
    }
    
    
    
    public static java.awt.Image getTextAsRandomColorImage(String strr){
        usedColor++;
        return getTextAsImage(strr, presetColors[usedColor-1]);
    }
    
    
    
    public static BufferedImage getTextAsImage(String strr, Color textColor){
        String str = null;
        if ( (strr == null) || strr.equalsIgnoreCase("") ){
            str = " ";
        } else {
            str = strr;
        }
        
        BufferedImage bi =null;
        
        try {
            
            javax.swing.JLabel lbl = new javax.swing.JLabel();
            lbl.setFont(new java.awt.Font("Arial", 1, 14));
            lbl.setText(" "+str+" ");
            
            bi = new BufferedImage(
                    (int) lbl.getPreferredSize().getWidth(),
                    (int) lbl.getPreferredSize().getHeight()+1,BufferedImage.TYPE_INT_ARGB);
            
            Graphics g = bi.createGraphics();
            g.setColor(new Color(200,200,255));
            g.fillRect(0,0,bi.getWidth(), bi.getHeight());
            
            g.setColor(textColor);
            g.setFont(new java.awt.Font("Arial", 1, 14));
            g.drawString(lbl.getText(), 0,bi.getHeight()-5);
            
        } catch (Exception e) {
        }
        
        return bi;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
