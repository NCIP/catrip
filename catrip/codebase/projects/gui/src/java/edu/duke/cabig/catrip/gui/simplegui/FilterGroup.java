/*
 * FilterGroup.java
 *
 * Created on December 19, 2006, 7:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.simplegui;

import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class FilterGroup {
    
    private boolean and = true;
    ArrayList<CDEComboboxBean> filterList = new ArrayList(20);  
    ArrayList<FilterGroup> groupList = new ArrayList(20);  
    
    
    
    
    /** Creates a new instance of FilterGroup */
    public FilterGroup() {
    }
    
}
