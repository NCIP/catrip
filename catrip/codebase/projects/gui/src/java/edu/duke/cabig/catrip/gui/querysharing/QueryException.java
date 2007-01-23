package edu.duke.cabig.catrip.gui.querysharing;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;

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
