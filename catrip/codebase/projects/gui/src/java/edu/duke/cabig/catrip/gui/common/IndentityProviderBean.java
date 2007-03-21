package edu.duke.cabig.catrip.gui.common;

/**
 * Captures the Indentity Providers listed in caTrip-config.xml file.
 *
 * @author Sanjeev Agarwal
 * @author Srini Akkala
 */
public class IndentityProviderBean {
    private String displayName;
    private String idpUrl;
    private String dorianUrl;
    private String gridGrouperUrl;
    
    /** Creates a new instance of IndentityProviderBean */
    public IndentityProviderBean() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIdpUrl() {
        return idpUrl;
    }

    public void setIdpUrl(String idpUrl) {
        this.idpUrl = idpUrl;
    }

    public String getDorianUrl() {
        return dorianUrl;
    }

    public void setDorianUrl(String dorianUrl) {
        this.dorianUrl = dorianUrl;
    }

    public String getGridGrouperUrl() {
        return gridGrouperUrl;
    }

    public void setGridGrouperUrl(String gridGrouperUrl) {
        this.gridGrouperUrl = gridGrouperUrl;
    }
    
}
