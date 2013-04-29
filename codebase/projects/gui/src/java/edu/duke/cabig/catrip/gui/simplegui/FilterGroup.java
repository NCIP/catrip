/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.duke.cabig.catrip.gui.simplegui;

import edu.duke.cabig.catrip.gui.panels.FilterRowPanel;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class FilterGroup {
    
    private boolean and = true;
    private ArrayList<FilterRowPanel> filterList = new ArrayList(20);
    private ArrayList<FilterGroup> groupList = new ArrayList(20);
    
    private FilterGroup parentGroup; // parent group of this group.. might need this when you delete the group itself..
    
    
    /** Creates a new instance of FilterGroup */
    public FilterGroup() {
    }
    
    public FilterGroup(boolean and) {
        this.and = and;
    }
    
    public static FilterGroup createANDGroup(){
        return new FilterGroup(true);
    }
    
    public static FilterGroup createORGroup(){
        return new FilterGroup(false);
    }
    
    public void add(Object obj){ // throws UnsupportedOperationException {
        if (obj instanceof FilterGroup){
            FilterGroup group = (FilterGroup)obj;
            getGroupList().add(group);
            group.setParentGroup(this); // set the parent group to this group..
        }else if (obj instanceof FilterRowPanel){
            FilterRowPanel filter = (FilterRowPanel)obj;
            getFilterList().add(filter);
            filter.setParentGroup(this); // set the parent group to this filter..
        }else {
            // throw new UnsupportedOperationException ("This type of object can not be added to group :"+obj.toString());
        }
        
    }
    
    public ArrayList<FilterRowPanel> getFilterList() {
        return filterList;
    }
    
    public void setFilterList(ArrayList<FilterRowPanel> filterList) {
        this.filterList = filterList;
    }
    
    public ArrayList<FilterGroup> getGroupList() {
        return groupList;
    }
    
    public void setGroupList(ArrayList<FilterGroup> groupList) {
        this.groupList = groupList;
    }
    
    public boolean isAND(){
        return and;
    }
    
    public boolean isOR(){
        return !and;
    }
    public String getHtmlConditionString(){
        return and?"<b>AND</b>":"<b>OR</b>";
    }
    public String getConditionString(){
        return and?"    AND    ":"    OR    ";
    }
    
    public boolean getCondition(){
        return and;
    }
    
    public void setAND(boolean _cond){
        this.and = _cond;
    }
    
    
    public String toString(){
        // concatnate the filters seprated with { } with the logical grouping information..
        
        String filterVal = "{ ";
        int numFilter = getFilterList().size();
        int numGroup = getGroupList().size();
        
        
        for (int i = 0; i < numFilter; i++) {
            FilterRowPanel filter = getFilterList().get(i);
            String value = filter.toString();
            filterVal += ((i==0)?"":getConditionString()) + "\""+value+ "\"";
        }
        
        if ((numFilter > 0) && (numGroup > 0)){
            filterVal += getConditionString();
        }
        
        
        if ( numGroup > 0){
            for (int i = 0; i < getGroupList().size(); i++) {
                FilterGroup filter = getGroupList().get(i);
                String value = filter.toString();
                filterVal += ((i==0)?"":getConditionString()) +value;
            }
        }
        
        filterVal += " }";
        
        return  filterVal;
    }
    
    public FilterGroup getParentGroup() {
        return parentGroup;
    }
    
    public void setParentGroup(FilterGroup parentGroup) {
        this.parentGroup = parentGroup;
    }
    
    public String getToolTipText(){
        return "<html>"+getIndentedHtmlHText(0)+"</html>";
    }
    
    public String getIndentedHtmlHText(int indentLevel){
        
        String filterVal = getIndentChar(indentLevel)+"<font color=\""+ getTextColor(indentLevel)+"\">{**********<br>";
        int numFilter = getFilterList().size();
        int numGroup = getGroupList().size();
        
        for (int i = 0; i < numFilter; i++) {
            FilterRowPanel filter = getFilterList().get(i);
            String value = filter.toString();
            filterVal += ((i==0)?"":(getIndentChar(indentLevel)+getConditionString() + "<br>")) + getIndentChar(indentLevel) +"\""+value+ "\"<br>";
        }
        
        if ((numFilter > 0) && (numGroup > 0)){
            filterVal += getIndentChar(indentLevel)+getConditionString() + "<br>";
        }
        
        if ( numGroup > 0){
            for (int i = 0; i < getGroupList().size(); i++) {
                FilterGroup filter = getGroupList().get(i);
                String value = filter.getIndentedHtmlHText(indentLevel+1);
                filterVal += ((i==0)?"":(getIndentChar(indentLevel)+getConditionString() + "<br>")) +value;
            }
        }
        
        filterVal += getIndentChar(indentLevel)+"}**********<br></font>";
        
        return filterVal;
    }
    
    
    private String getIndentChar(int indentLevel){
        String str = "";
        for (int i = 0; i < indentLevel*5; i++) {
            str+="&nbsp;";
        }
        return str;
    }
    
    private String getTextColor(int indentLevel){
        int colors = GUIConstants.HTML_COLOR_SET.length;
        String col = null;
        if (indentLevel >= colors){
            for (int i = 0; i < indentLevel; i++) {
                indentLevel-=colors;
                if (indentLevel < colors){
                    col =  GUIConstants.HTML_COLOR_SET[indentLevel];
                    break;
                }
            }
        } else {
            col =  GUIConstants.HTML_COLOR_SET[indentLevel];
        }
        return col;
    }
    
    
    
    
    
    
    
    
    
    
    
    public String getUniqueId(){
        //return super.toString(); // this returns the instance ref for the object which is unique for that jvm instance..
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    
    public boolean equals(Object obj){
        boolean equal = false;
        if (obj instanceof FilterGroup){
        FilterGroup fGroup = (FilterGroup) obj;
        String thisRef = getUniqueId();
        String objRef = fGroup.getUniqueId();
        equal = thisRef.equals(objRef); // this compares the two references.. and will be true only if that is same object..
        }
        return equal; 
    }  
    
//    public int hashCode() {
//        return getUniqueId().hashCode(); 
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
