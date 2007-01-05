

package edu.duke.cabig.catrip.gui.components;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
 
/**
 * DotDashBorder is a <CODE>java.swing.border.Border</CODE> implementation 
 * that draws a dotted and/or dashed line border.  The dot/dash pattern is 
 * defined as an array of ints, with every two indices defining the number 
 * of pixels for each dot/dash and space.  E.g.: <BR>
 * { 1, 1 } == 1-pixel-dot, 1-pixel-space, 1-pixel-dot, 1-pixel-space, etc.<BR>
 * { 4, 2 } == 4-pixel-dash, 2-pixel-space, 4-pixel-dash, 2-pixel-space, etc.<BR>
 */
public class DotDashBorder extends AbstractBorder {
	/**
	 * Defines a dotted line pattern:  <PRE>. . . .</PRE>
	 */
	public static final int[] DOT = { 1, 1 };
 
	/**
	 * Defines a dashed line pattern:  <PRE>- - - -</PRE>
	 */
	public static final int[] DASH_SHORT = { 4, 4 };
 
	/**
	 * Defines a long dashed line pattern:  <PRE>---   ---   ---</PRE>
	 */
	public static final int[] DASH_LONG = { 10, 10 };
 
	/**
	 * Defines a long/short dashed line pattern:  <PRE>-- - -- - -- -</PRE>
	 */
	public static final int[] DASH_LONG_SHORT = { 6, 3, 3, 3 };
 
	/**
	 * Defines a long/short/short dashed line pattern:  <PRE>-- - - -- - - -- - -</PRE>
	 */
	public static final int[] DASH_LONG_SHORT_SHORT = { 6, 3, 3, 3, 3, 3 };
 
	/**
	 * The dot/dash pattern.
	 */
	private int[] pattern = DOT;
 
	/**
	 * The border thickness
	 */
	private int thickness = 1;
 
	/**
	 * The foreground color.  If not specified, the component's foreground 
	 * color is used.  
	 */
	private Color colorFG = null;
 
	/**
	 * The background color.  If not specified, the component's background 
	 * color is used.  
	 */
	private Color colorBG = null;
 
	/**
	 * Thicken border pattern relative to thickness flag.  
	 */
	private boolean thicken = false;
 
	/**
	 * Create a new 1-pixel thick DotDashBorder using the component's 
	 * background color.
	 * @param  pattern  int[]: the dot/dash-space pattern
	 * @param  fg  Color: the foreground (dot/dash) color
	 */
	public DotDashBorder(int[] pattern, Color fg) {
		this(pattern, 1,  fg, null);
	}
 
	/**
	 * Create a new DotDashBorder using the component's foreground and 
	 * background colors.
	 * @param  pattern  int[]: the dot/dash-space pattern
	 * @param  thickness  int: the border thickness
	 */
	public DotDashBorder(int[] pattern, int thickness) {
		this(pattern, thickness, null, null);
	}
 
	/**
	 * Create a new DotDashBorder using the component's 
	 * background color.
	 * @param  pattern  int[]: the dot/dash-space pattern
	 * @param  thickness  int: the border thickness
	 * @param  fg  Color: the foreground (dot/dash) color
	 */
	public DotDashBorder(int[] pattern, int thickness, Color fg) {
		this(pattern, thickness,  fg, null);
	}
 
	/**
	 * Create a new DotDashBorder.
	 * @param  pattern  int[]: the dot/dash-space pattern
	 * @param  thickness  int: the border thickness
	 * @param  fg  Color: the foreground (dot/dash) color
	 * @param  bg  Color: the background (space) color
	 */
	public DotDashBorder(int[] pattern, int thickness, Color fg, Color bg) {
		for(int i = 0; i < pattern.length; i++) {
			if(pattern[i] <= 0) {
				throw new IllegalArgumentException("Pattern cannot have values <= 0.");
			}
		}
		this.pattern = pattern;
		if(thickness <= 0) {
			throw new IllegalArgumentException("Thickness cannot be <= 0.");
		}
		this.thickness = thickness;
		this.colorFG = fg;
		this.colorBG = bg;
	}
 
	/**
	 * Get the insets for the border.
	 * @param  Component c: the component the border is for
	 * @return  Insets: the insets for the border
	 * @see  #getBorderInsets(Component, Insets)
	 */
	public Insets getBorderInsets(Component c) {
		return new Insets(thickness, thickness, thickness, thickness);
	}
 
	/**
	 * Get the insets for the border.
	 * @param  Component c: the component the border is for
	 * @param  insets  Insets: the insets
	 * @return  Insets: the insets for the border
	 * @see  #getBorderInsets(Component)
	 */
	public Insets getBorderInsets(Component c, Insets insets) {
		return new Insets(thickness, thickness, thickness, thickness);
	}
 
	/**
	 * Check if the border is opaque.
	 * @return  boolean: true if the border is opaque
	 */
	public boolean isBorderOpaque() {
		return true;
	}
 
	/**
	 * Check if pattern will be thickened by thickness.
	 * @return  boolean: true if pattern will be thickened by thickness
	 * @see  #setThickenPattern(boolean)
	 */
	public boolean isThickenPattern() {
		return this.thicken;
	}
 
	/**
	 * Set if the pattern should be thickened by thickness.  If true, a 
	 * pattern of { 1, 1 } and thickness of 5, the pattern would be expanded 
	 * to { 5, 5 }.  This allows setting a pattern based on small pixel 
	 * measurements that grows proportionally with the thickness.  
	 * @param  boolean t: true if pattern should be thickened by thickness
	 * @see  #isThickenPattern()
	 */
	public void setThickenPattern(boolean t) {
		this.thicken = t;
	}
 
	/**
	 * Paint the border.
	 * @param  Component c: the component the border is for
	 * @param  Graphics g: the graphics object to draw on
	 * @param  x  int: the border y position
	 * @param  y  int: the border x position
	 * @param  width  int: the border width
	 * @param  height  int: the border height
	 */
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Color colorFGX = c.getBackground();
		if(colorFG != null) {
			colorFGX = colorFG;
		}
		Color colorBGX = c.getBackground();
		if(colorBG != null) {
			colorBGX = colorBG;
		}
		g.setColor(colorFGX);
		g.fillRect(x, x, width, thickness);				// top
		g.fillRect(x, y+height-thickness, width, thickness);	// bottom
		g.fillRect(x, y, thickness, height);				// left
		g.fillRect(x+width-thickness, y, thickness, height);	// right
		g.setColor(colorBGX);
		// top/bottom
		int cx = 0;
		// get real pattern
		int[] realPattern = new int[pattern.length];
		for(int i = 0; i < pattern.length; i++) {
			if(thicken) {
				realPattern[i] = pattern[i]*thickness;
			} else {
				realPattern[i] = pattern[i];
			}
		}
		for(int i = 0, j = 0; i < width; i++, j+=2) {
			if(j >= realPattern.length) {
				j = 0;
			}
			cx += realPattern[j];
			g.fillRect(cx, y, realPattern[j+1], thickness);				// top
			g.fillRect(cx, y+height-thickness, realPattern[j+1], thickness);	// bottom
			cx += realPattern[j+1];
		}
		// left/right
		int cy = 0;
		for(int i = 0, j = 0; i < height; i++, j+=2) {
			if(j >= realPattern.length) {
				j = 0;
			}
			cy += realPattern[j];
			g.fillRect(x, cy, thickness, realPattern[j+1]);				// left
			g.fillRect(x+width-thickness, cy, thickness, realPattern[j+1]);	// right
			cy += realPattern[j+1];
		}
	}
}
