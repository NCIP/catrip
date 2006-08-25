package edu.duke.cabig.catrip.gui.common;

import java.awt.Image;
import java.util.*;


/**
 * ClassBean represents a Class as defined in UMLClass element of Domain Model Metadata.
 *
 * @author Sanjeev Agarwal
 */
public class ClassBean{
    
    private String id;
    private String version;
    private ArrayList<AttributeBean> attributes = new ArrayList(50);
    private ArrayList<String> associatedClasses= new ArrayList(20); // this is from the domain model..
    private String packageName;
    private String className;
    private String description;
    
    // set the icon based on the service name.. have a pre selected icons.. and a default icon too
    private Image icon;// = "edu/duke/cabig/catrip/gui/dnd/resources/caCore.png";
    private String serviceName;
    private String serviceUrl;
    private String domainModelId;
    private String CDEName;
    
    private HashMap associationRoleNameMap = new HashMap(20);
    
    // ----- DCQL helper attributes ---- //
    
    private boolean needImpl=false;
    
    private int numNotNullAttributes = 0;
    private ArrayList<AttributeBean> notNullAttributes;
    
    private boolean hasAssociations = false;
//    private int numAssociations = 0;
    private ArrayList<ClassBean> associations = new ArrayList(20);
    
    private ArrayList<ForeignAssociationBean> foreignAssociations = new ArrayList(10);
    private boolean hasForeignAssociations = false;
    // ----- DCQL helper attributes ---- //
    
    
    /** Creates a new instance of ClassBean */
    public ClassBean() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public ArrayList<AttributeBean> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(ArrayList<AttributeBean> attributes) {
        this.attributes = attributes;
    }
    
    /** All associated classes as defined in the domain model */
    public ArrayList<String> getAssociatedClasses() {
        return associatedClasses;
    }
    
    /** All associated classes as defined in the domain model */
    public void setAssociatedClasses(ArrayList<String> associatedClasses) {
        this.associatedClasses = associatedClasses;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public Image getIcon() {
        return icon;
    }
    
    public void setIcon(Image icon) {
        this.icon = icon;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getCDEName() {
        return CDEName;
    }
    
    public void setCDEName(String CDEName) {
        this.CDEName = CDEName;
    }
    
    public String getDomainModelId() {
        return domainModelId;
    }
    
    public void setDomainModelId(String domainModelId) {
        this.domainModelId = domainModelId;
    }
    
    /** Add to the attribute list of the class. */
    public void addAttribute(AttributeBean ab){
        getAttributes().add(ab);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /** add to the list of associated classes. Associations are defined in domain model. */
    public void addAssociatedClass(String refId){
        associatedClasses.add(refId);
    }
    
    /** this is to display the tree node name as class name only. */
    public String toString() {
        return getClassName();
    }
    
    /** used to clone the class object when it is dropped on the graph.
     * The cloned object contains all the property of the original class object except the values of the Attribute.
     */
    public ClassBean clone(){
        
        ClassBean cBean = new ClassBean();
        cBean.setAssociatedClasses(getAssociatedClasses()); // associated class will be same for all clones.
        
        // clone attributes also..
        // this is necessary as different graph nodes of the same class may have different attribute value
        ArrayList<AttributeBean> attributeClones = new ArrayList(50);
        ArrayList attributes = getAttributes();
        for (int k = 0; k < attributes.size(); k++) {
            AttributeBean aBean = ((AttributeBean)attributes.get(k)).clone();
            attributeClones.add(aBean);
        }
        cBean.setAttributes(attributeClones);
        
        cBean.setCDEName(getCDEName());
        cBean.setClassName(getClassName());
        cBean.setDescription(getDescription());
        cBean.setDomainModelId(getDomainModelId());
        cBean.setIcon(getIcon());
        cBean.setId(getId());
        cBean.setPackageName(getPackageName());
        cBean.setServiceName(getServiceName());
        cBean.setServiceUrl(getServiceUrl());
        cBean.setVersion(getVersion());
        cBean.setAssociationRoleNameMap(getAssociationRoleNameMap());
        cBean.needImpl(needImpl());
        
        return cBean;
    }
    
    /** The service URL of the grid service. Required in DCQL generation from the class instance. */
    public String getServiceUrl() {
        return serviceUrl;
    }
    
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }
    
    public String getFullyQualifiedName() {
//        return getPackageName()+"."+getClassName();
        // TODO - change this in future.. for the demo only..
        if (needImpl()){
            return getPackageName()+".impl."+getClassName()+"Impl";
        } else {
            return getPackageName()+"."+getClassName();
        }
    }
    
    /** The map of the associated classes and corresponding roleNames.
     * Built from the Domain model element "exposedUMLAssociationCollection"
     */
    public HashMap getAssociationRoleNameMap() {
        return associationRoleNameMap;
    }
    
    public void setAssociationRoleNameMap(HashMap associationRoleNameMap) {
        this.associationRoleNameMap = associationRoleNameMap;
    }
    
    /** get the role name for a particular associated class. */
    public String getAssociationRoleName(String classId) {
        String roleName = (String)getAssociationRoleNameMap().get(classId);
        return roleName;
    }
    
    /** set the role name for a particular associated class and add to the map. */
    public void addAssociationRoleName(String classId, String associationRoleName) {
        this.getAssociationRoleNameMap().put(classId, associationRoleName) ;
    }
    
    
    
    
    
    
    
    
    
    
    // --------- helper methods for generating DCQL ------------------ //
    
    /** return true of the class object contains attributes without null values  */
    public boolean hasNotNullAttributes(){
        boolean result = false;
        ArrayList attributes = getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            AttributeBean aBean = (AttributeBean)attributes.get(i);
            if (!aBean.isNull()){
                numNotNullAttributes++;
                result = true;
            }
        }
        return result;
    }
    
    /** Get the list of attributes with values. */
    public ArrayList<AttributeBean> getNonNullAttributes(){
        notNullAttributes = new ArrayList(numNotNullAttributes);
        ArrayList attributes = getAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            AttributeBean aBean = (AttributeBean)attributes.get(i);
            if (!aBean.isNull()){
                notNullAttributes.add(aBean);
            }
            
        }
        return notNullAttributes;
    }
    
    // ----------------------------------
    
    /** Check for any association created by linking two nodes on the graph via class-link. */
    public boolean hasAssociations(){
        return hasAssociations;
    }
    public void setHasAssociations(boolean has){
        this.hasAssociations = has;
    }
    /** the list that contains the list of associations created on the graph. */
    public void addAssociation(ClassBean ass){
        associations.add(ass);
//        numAssociations++;
    }
    public ArrayList getAssociations(){
        return associations;
    }
    
    // -----------------------------------
    /** Check for any foreign association created by linking two nodes on the graph via CDE link  */
    public boolean hasForeignAssociations(){
        return hasForeignAssociations;
    }
    public void setHasForeignAssociations(boolean has){
        this.hasForeignAssociations = has;
    }
    /** add to the list that contains the list of foreign associations created on the graph. */
    public void addForeignAssociation(ForeignAssociationBean fass){
        foreignAssociations.add(fass);
    }
    /** the list that contains the list of foreign associations created on the graph. */
    public ArrayList getForeignAssociations(){
        return foreignAssociations;
    }
    // --------- helper methods for generating DCQL ------------------ //
    
    
    
    /** method created for the "impl" problem in the caCORE SDK generated code. */
    public void needImpl(boolean need){
        this.needImpl = need;
    }
    /** method created for the "impl" problem in the caCORE SDK generated code. */
    public boolean needImpl(){
        return needImpl;
    }
    
    
    
    
}
