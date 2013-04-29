/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.querysharing;

import java.io.PrintWriter;
import java.io.StringWriter;

public class QueryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Exception e = null;
	
	public QueryException(Exception e){
		super();
		this.e = e;
	}
	public String print(Throwable t) {
		StringWriter sw = new StringWriter();
	    printStackTrace(new PrintWriter(sw));
	    return sw.toString();
	}

}
