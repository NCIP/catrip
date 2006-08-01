/*
 * IndentityProviderBean.java
 *
 * Created on July 24, 2006, 5:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.common;

/**
 *
 * @author Sanjeev Agarwal
 */
public class IndentityProviderBean {
    private String displayName;
    private String url;
    private String type;
    private String keystore;
//    private boolean trusted;
    
    /** Creates a new instance of IndentityProviderBean */
    public IndentityProviderBean() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

//    public boolean isTrusted() {
//        return trusted;
//    }
//
//    public void setTrusted(boolean trusted) {
//        this.trusted = trusted;
//    }
    
}
