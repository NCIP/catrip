/*
 * AttributeBeanComparator.java
 *
 * Created on August 1, 2006, 4:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

import java.util.Comparator;

/**
 *
 * @author Sanjeev Agarwal
 */
public class AttributeBeanComparator implements Comparator{
    
    /** Creates a new instance of AttributeBeanComparator */
    public AttributeBeanComparator() {
    }
    public int compare(Object cBean1, Object cBean2) {
        return ((AttributeBean)cBean1).getCDEName().compareTo( ((AttributeBean)cBean2).getCDEName() );
    }
}
