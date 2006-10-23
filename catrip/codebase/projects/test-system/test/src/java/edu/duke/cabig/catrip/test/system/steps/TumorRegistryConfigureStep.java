/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.atomicobject.haste.framework.Step;

import edu.duke.cabig.catrip.test.system.util.HibernatePropertiesUtil;
import gov.nci.nih.cagrid.tests.core.util.FileUtils;

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
		origFile = File.createTempFile("TumorRegistryConfigureStep", ".properties");
		configFile = new File(serviceDir, "hibernate.properties");
		FileUtils.copy(configFile, origFile);

		Properties props = new Properties();
		props.setProperty(
			"hibernate.connection.url", 
			System.getProperty("tumorregistry.connectionurl", "jdbc:oracle:thin:@pparker:1521:TRIP")
		);
		props.setProperty(
			"hibernate.connection.username", 
			System.getProperty("tumorregistry.user", "tr")
		);
		props.setProperty(
			"hibernate.connection.password", 
			System.getProperty("tumorregistry.password", "tr")
		);
		HibernatePropertiesUtil.configure(configFile, props);
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
