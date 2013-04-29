/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.common;

import java.util.Comparator;

/**
 * Comparator to compare two AttributeBean objects based on the CDE name.
 * Used in JTree and Graph to sort the Attributes based on CDE names.
 *
 * @author Sanjeev Agarwal
 */
public class AttributeBeanComparator implements Comparator{
    
    /** Creates a new instance of AttributeBeanComparator */
    public AttributeBeanComparator() {
    }
    /**
     * Compares the CDE name from one AttributeBean to another AttributeBean.
     * @see java.util.Comparator
     */
    public int compare(Object cBean1, Object cBean2) {
        return ((AttributeBean)cBean1).getCDEName().compareTo( ((AttributeBean)cBean2).getCDEName() );
    }
}
