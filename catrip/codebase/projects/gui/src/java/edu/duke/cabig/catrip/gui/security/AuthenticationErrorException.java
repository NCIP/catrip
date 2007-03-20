/*
 * AuthenticationErrorException.java
 *
 * Created on March 14, 2007, 11:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.security;

/**
 *
 * @author srini
 */
public class AuthenticationErrorException extends Exception {

  /**
   * 
   */
  public AuthenticationErrorException() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public AuthenticationErrorException(String arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   */
  public AuthenticationErrorException(Throwable arg0) {
    super(arg0);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param arg0
   * @param arg1
   */
  public AuthenticationErrorException(String arg0, Throwable arg1) {
    super(arg0, arg1);
    // TODO Auto-generated constructor stub
  }

}