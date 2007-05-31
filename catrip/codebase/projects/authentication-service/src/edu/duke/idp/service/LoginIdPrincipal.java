/*
 * LoginIdPrincipal.java
 *
 * Created on March 7, 2007, 4:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.idp.service;

import java.security.Principal;

/**
 *
 * @author srini
 */
public class LoginIdPrincipal implements Principal {
    
    private String name;
    /** Creates a new instance of LoginIdPrincipal */
    public LoginIdPrincipal() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
}
