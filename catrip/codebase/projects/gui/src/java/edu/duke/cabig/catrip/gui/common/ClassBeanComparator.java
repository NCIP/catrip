/*
 * ClassBeanComparator.java
 *
 * Created on August 1, 2006, 4:47 PM
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
public class ClassBeanComparator implements Comparator{ 
    
    /** Creates a new instance of ClassBeanComparator */
    public ClassBeanComparator() {
    }

    public int compare(Object cBean1, Object cBean2) {
         return ((ClassBean)cBean1).getClassName().compareTo( ((ClassBean)cBean2).getClassName() ); 
    }
    
}
