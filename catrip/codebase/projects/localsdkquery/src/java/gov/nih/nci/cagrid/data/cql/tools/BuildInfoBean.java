package gov.nih.nci.cagrid.data.cql.tools;

public class BuildInfoBean {
    public BuildInfoBean() {
    }
    
    private String roleName = "";
    private String associationClassName = "";
    private Object targetObject;
    private Class targetObjectClass;
    private String id;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setAssociationClassName(String associationClassName) {
        this.associationClassName = associationClassName;
    }

    public String getAssociationClassName() {
        return associationClassName;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObjectClass(Class targetObjectClass) {
        this.targetObjectClass = targetObjectClass;
    }

    public Class getTargetObjectClass() {
        return targetObjectClass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
