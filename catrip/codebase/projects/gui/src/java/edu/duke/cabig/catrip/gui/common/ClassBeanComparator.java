package edu.duke.cabig.catrip.gui.common;

import java.util.Comparator;

/**
 * Comparator to compare two ClassBean objects based on the CDE name.
 * Used in JTree to sort the Classes based on Class names.
 *
 * @author Sanjeev Agarwal
 */
public class ClassBeanComparator implements Comparator{
    
    /** Creates a new instance of ClassBeanComparator */
    public ClassBeanComparator() {
    }
    
    /**
     * Compares the Class name from one ClassBean to another ClassBean.
     * @see java.util.Comparator
     */
    public int compare(Object cBean1, Object cBean2) {
        return ((ClassBean)cBean1).getClassName().compareTo( ((ClassBean)cBean2).getClassName() );
    }
    
}
