package gov.nih.nci.cagrid.data.cql.tools;

public class BuildInfoBean {
    public BuildInfoBean() {
    }
    
    private String roleName = "";
    private String associationClassName = "";

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
}
