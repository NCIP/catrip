/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.simplegui;

import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class PathNode {
    
    private String className;
    private String roleName;
    private int childCount = 0;
    private boolean display = false;
    private ArrayList displaybleAttributes = new ArrayList(20); 
   
    
    /** Creates a new instance of PathNode */
    public PathNode() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public ArrayList getDisplaybleAttributes() {
        return displaybleAttributes;
    }

    public void setDisplaybleAttributes(ArrayList displaybleAttributes) {
        this.displaybleAttributes = displaybleAttributes;
    }
    
    public void addDisplaybleAttributes( String displaybleAttribute) {
        this.displaybleAttributes.add(displaybleAttribute);
    }
    
}
