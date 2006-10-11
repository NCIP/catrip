/*
 * Created on Oct 11, 2006
 */
package edu.duke.cabig.catrip.test.system.util;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class HibernatePropertiesUtil
{
	private HibernatePropertiesUtil() { super(); }
	
	public static void configure(File configFile, Properties props) 
		throws IOException
	{
		File tmpFile = File.createTempFile("HibernatePropertiesUtil", ".properties");
		tmpFile.deleteOnExit();
		BufferedReader br = new BufferedReader(new FileReader(configFile));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(tmpFile)));
		String line = null;
		while ((line = br.readLine()) != null) {
			int index = line.indexOf('=');
			if (index == -1) index = line.indexOf(' ');
			if (index == -1) {
				out.println(line);
				continue;
			}
			String key = line.substring(0, index);
			if (! props.containsKey(key)) {
				out.println(line);
				continue;
			}
			String delimiter = line.substring(index, index+1);
			String value = line.substring(index+1);
			out.println(key + delimiter + props.getProperty(key)); 
		}
		out.flush();
		out.close();
		br.close();
		FileUtils.copy(tmpFile, configFile);
		tmpFile.delete();
	}
}
