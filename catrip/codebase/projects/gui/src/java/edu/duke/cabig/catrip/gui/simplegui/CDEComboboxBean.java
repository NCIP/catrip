
package edu.duke.cabig.catrip.gui.simplegui;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;

/** 
 *
 * @author Sanjeev Agarwal
 */
public class CDEComboboxBean {
    private ClassBean cBean;
    private AttributeBean aBean;
    private GraphObject graphBean; 
    
    /** Creates a new instance of CDEComboboxBean */
    public CDEComboboxBean() {
    }

    public ClassBean getClassBean() {
        return cBean;
    }

    public void setClassBean(ClassBean cBean) {
        this.cBean = cBean;
    }

    public AttributeBean getAttributeBean() {
        return aBean;
    }

    public void setAttributeBean(AttributeBean aBean) {
        this.aBean = aBean;
    }

    public String toString() {
        return cBean.getCDEName() + "  " + aBean.getCDEName();  
//        return cBean.getServiceName()+" -- "+cBean.getCDEName() + "  " +aBean.getCDEName();
    }

    public GraphObject getGraphObject() {
        return graphBean;
    }

    public void setGraphObject(GraphObject graphBean) {
        this.graphBean = graphBean;
        setClassBean(graphBean.getClassBean()); 
    } 
    
    public void remove(){
        getAttributeBean().setAttributeValue("");
        getAttributeBean().setDefaultPredicate();
    }
    
    
    public String print(){
        return getClassBean().getFullyQualifiedName() + " : " + getAttributeBean().getAttributeName();
    }
    
}
