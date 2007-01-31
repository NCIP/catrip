package edu.duke.cabig.catrip.gui.querysharing;

import gov.nih.nci.cagrid.dcql.DCQLQuery;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.namespace.QName;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import org.xml.sax.InputSource;

public class DcqlUtil extends TestQuerySaveUI {
	public static String getContents(File aFile) {
		//...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		//declared here only to make visible to finally clause
		BufferedReader input = null;
		try {
			//use buffering, reading one line at a time
			//FileReader always assumes default encoding is OK!
			input = new BufferedReader( new FileReader(aFile) );
			String line = null; //not declared within while loop
			/*
			 * readLine is a bit quirky :
			 * it returns the content of a line MINUS the newline.
			 * it returns null only for the END of the stream.
			 * it returns an empty String if two newlines appear in a row.
			 */
			while (( line = input.readLine()) != null){
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (input!= null) {
					//flush and close both "input" and its underlying FileReader
					input.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return contents.toString();
	}

	@SuppressWarnings("unused")
	public static  String getDCQL(DCQLQuery dcqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, dcqlQuery, qname);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		//System.out.println(w);
		return w.toString(); 
	}
	
	@SuppressWarnings("unused")
	public static void printDCQL(DCQLQuery cqlQuery) {
		QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.cql");
		Writer w = new StringWriter();
		try {
			ObjectSerializer.serialize(w, cqlQuery, qname);
		} catch (SerializationException e) {
			e.printStackTrace();
		}
		//System.out.println(w);
	}
	
	@SuppressWarnings("unused")
	public static DCQLQuery convertStringToDCQL(String cqlString){


		StringBuffer buf = new StringBuffer(cqlString);

		char[] chars = new char[buf.length()];
		buf.getChars(0, chars.length, chars, 0);

		CharArrayReader car = new CharArrayReader(chars);
		InputSource source = new InputSource(car);
		Object obj = null ;

		try {
			obj = ObjectDeserializer.deserialize(source,DCQLQuery.class);
		} catch (DeserializationException e) {
			e.printStackTrace();
		}
		return ((DCQLQuery)obj);
	}

}
