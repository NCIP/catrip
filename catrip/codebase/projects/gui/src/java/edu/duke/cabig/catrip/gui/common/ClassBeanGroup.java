
package edu.duke.cabig.catrip.gui.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ClassBeanGroup {
    
    private boolean and = true;
    private ArrayList<ClassBean> classList = new ArrayList(20);
    private HashMap classRoleMap = new HashMap(20);
    private HashMap classListMap = new HashMap();
    
    private ArrayList<AttributeBean> attributeList = new ArrayList(20);
    
    private String groupId;
    
    /** Creates a new instance of ClassBeanGroup */
    public ClassBeanGroup() {
    }
    
    public ClassBeanGroup(boolean and) {
        this.and = and;
    }
    
    public static ClassBeanGroup createANDGroup(){
        return new ClassBeanGroup(true);
    }
    
    public static ClassBeanGroup createORGroup(){
        return new ClassBeanGroup(false);
    }
    
    public void add(Object obj){
        if (obj instanceof ClassBean){ // here check if the class bean  alreay existed..  it may be a differnt instance but may be a null object..
            ClassBean cBean = (ClassBean)obj;
            
            if (classListMap.get(cBean.getFullyQualifiedName()) != null){
                // class bean instance exist.. merge them... merge groups.. merge attributes.. merge associations.. merge foreignAssociations..
//                ClassBean.mergeClassBeans( ((ClassBean)classListMap.get(cBean.getFullyQualifiedName())), cBean );
            } else { // add the class bean instance..
                classListMap.put(cBean.getFullyQualifiedName(), cBean); // attach it with the full class name..
                getClassList().add(cBean);
            }
            
        } else if(obj instanceof AttributeBean){
            if (!getAttributeList().contains(obj) && !((AttributeBean)obj).isNull() ){
                getAttributeList().add((AttributeBean)obj);
            }
        }
    }
    
    public ArrayList<ClassBean> getClassList() {
        return classList;
    }
    
    public void setClassList(ArrayList<ClassBean> classList) {
        this.classList = classList;
    }
    
    public ArrayList<AttributeBean> getAttributeList() {
        return attributeList;
    }
    
    public void setAttributeList(ArrayList<AttributeBean> attributeList) {
        this.attributeList = attributeList;
    }
    
    
    public boolean isAND(){
        return and;
    }
    
    public void setAND(boolean _cond){
        this.and = _cond;
    }
    
    public String getConditionString(){
        return and?"AND":"OR";
    }
    
    public String getGroupId() {
//        if (groupId == null){
//            groupId = toString();  // that is a unique ID per JVM instance..
//        }
        return groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public boolean isAttributeOnlyGroup(){
        return (classList.size() == 0 && attributeList.size() > 0)?true:false;
    }
    
    public boolean isClassOnlyGroup(){
        return (classList.size() > 0 && attributeList.size() == 0)?true:false;
    }
    
    public boolean isMixGroup(){
        return (classList.size() > 0 && attributeList.size() > 0)?true:false;
    }
    
    public String getClassRole(String classId) {
        return (String)classRoleMap.get(classId);
    }
    
    public void addClassRole(String classId, String role) {
        this.classRoleMap.put(classId, role);
    }
    
    
    
    
    
    
    
    public static boolean needOuterAttributeGroup(ArrayList groups){
        return needOuterGroup(groups, 1);
    } 
    
    public static boolean needOuterClassGroup(ArrayList groups){
        return needOuterGroup(groups, 2);
    }
    
    public static boolean needOuterGroup(ArrayList groups, int param){
        boolean result = false;
        int groupNums = groups.size();
        
        int attGroups = 0;
        int classGroups = 0;
        
        for (int i = 0; i < groupNums; i++) {
            ClassBeanGroup group = (ClassBeanGroup)groups.get(i);
            
            if (group.isAttributeOnlyGroup()){
                if(group.getAttributeList().size() > 1){
                    attGroups++;
                }
            } else if (group.isClassOnlyGroup()){
                int numClass = group.getClassList().size();
                if (numClass > 1){
                    classGroups++;
                }
            } else if (group.isMixGroup()){
                
                ArrayList attList = group.getAttributeList();
                ArrayList classList = group.getClassList();
                
                if (attList.size() > 1){
                    attGroups++;
                }
                if (classList.size() > 1){
                    classGroups++;
                }
            }
            
        }
        
        boolean needAttOuterGroup = attGroups>1 ?true:false;
        boolean needClassOuterGroup = classGroups>1 ?true:false;
        
        if (param == 1){ // get attribute group requirement..
            result = needAttOuterGroup;
        } else if (param == 2){ // get attribute group requirement..
            result = needClassOuterGroup;
        }
        
        
        return result;
    }
    
    
    
    
    
    
    
}
