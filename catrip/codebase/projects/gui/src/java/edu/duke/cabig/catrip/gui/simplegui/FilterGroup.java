/*
 * FilterGroup.java
 *
 * Created on December 19, 2006, 7:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
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
    public String getHtmlConditionString(){
        return and?"<b>AND</b>":"<b>OR</b>";
    }
    public String getConditionString(){
        return and?"    AND    ":"    OR    ";
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
        String col = null;
        if (indentLevel >= 4){
            for (int i = 0; i < indentLevel; i++) {
                indentLevel-=4;
                if (indentLevel < 4){
                    col =  GUIConstants.HTML_COLOR_SET[indentLevel];
                    break;
                }
            }
        } else {
            col =  GUIConstants.HTML_COLOR_SET[indentLevel];
        }
        return col;
    }
    
    
    
    
}
