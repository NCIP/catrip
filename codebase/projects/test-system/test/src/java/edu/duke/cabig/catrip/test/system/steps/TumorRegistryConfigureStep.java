/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

import java.io.File;
import java.io.IOException;

import com.atomicobject.haste.framework.Step;

/**
 * Sets hibernate configuration properties in the Tumor Registry grid service. 
 * @author Patrick McConnell
 */
public class TumorRegistryConfigureStep
	extends Step
{
	private File serviceDir;
	private File origFile;
	private File configFile;
	
	public TumorRegistryConfigureStep(File serviceDir) 
	{
		super();
		
		this.serviceDir = serviceDir;
	}
	
	public void runStep() 
		throws IOException
	{
		origFile = File.createTempFile("TumorRegistryConfigureStep", ".cfg.xml");
//		configFile = new File(serviceDir, "hibernate.properties");
		configFile = new File(serviceDir, "tumor_hibernate.cfg.xml");
		FileUtils.copy(configFile, origFile);

		String connectionUrl = System.getProperty("tumorregistry.connectionurl", 
			"jdbc:oracle:thin:@pparker:1521:trip"
		); 
		String user = System.getProperty("tumorregistry.username", 
			"tr"
		); 
		String password = System.getProperty("tumorregistry.password", 
			"tr"
		); 

		FileUtils.replace(
			configFile,
			"<property name=\"connection.url\">jdbc:oracle:thin:@pparker:1521:trip</property>",
			"<property name=\"connection.url\">" + connectionUrl + "</property>"			
		);
		FileUtils.replace(
			configFile,
			"<property name=\"connection.username\">tr</property>",
			"<property name=\"connection.username\">" + user + "</property>"			
		);
		FileUtils.replace(
			configFile,
			"<property name=\"connection.password\">tr</property>",
			"<property name=\"connection.password\">" + password + "</property>"			
		);

//		Properties props = new Properties();
//		props.setProperty(
//			"hibernate.connection.url", 
//			System.getProperty("tumorregistry.connectionurl", "jdbc:oracle:thin:@pparker:1521:TRIP")
//		);
//		props.setProperty(
//			"hibernate.connection.username", 
//			System.getProperty("tumorregistry.user", "tr")
//		);
//		props.setProperty(
//			"hibernate.connection.password", 
//			System.getProperty("tumorregistry.password", "tr")
//		);
//		HibernatePropertiesUtil.configure(configFile, props);
	}

	public File getConfigFile()
	{
		return configFile;
	}

	public File getOrigFile()
	{
		return origFile;
	}

	public File getServiceDir()
	{
		return serviceDir;
	}
}
