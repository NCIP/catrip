
package edu.duke.cabig.catrip.gui.common;

import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import java.util.Comparator;


/**
 *
 * @author Sanjeev Agarwal
 */
public class CDEComboboxBeanComparator  implements Comparator { 
    
    /** Creates a new instance of CDEComboboxBeanComparator */
    public CDEComboboxBeanComparator() {
    }
    
    
    public int compare(Object cBean1, Object cBean2) {
        return ((CDEComboboxBean)cBean1).toString().compareTo( ((CDEComboboxBean)cBean2).toString() );
    }
    
}
