/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * $Id: InsufficientAttributeException.java,v 1.1 2007-07-10 03:55:00 srakkala Exp $
 *
 */
package gov.nih.nci.cagrid.authentication.common;

/**
 *
 * @version $Revision: 1.1 $
 * @author Joshua Phillips
 *
 */
public class InsufficientAttributeException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8673519454645453386L;

    /**
     * 
     */
    public InsufficientAttributeException() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public InsufficientAttributeException(String arg0) {
	super(arg0);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public InsufficientAttributeException(Throwable arg0) {
	super(arg0);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InsufficientAttributeException(String arg0, Throwable arg1) {
	super(arg0, arg1);
	// TODO Auto-generated constructor stub
    }

}
