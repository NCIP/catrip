package edu.duke.cabig.catrip.gui.simplegui.objectgraph;

public class GraphAssociation {

    private String className;
    private String roleName;
    
    public GraphAssociation() {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
