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
 * Sets hibernate configuration properties in the CAE grid service. 
 * @author Patrick McConnell
 */
public class CAEConfigureStep
	extends Step
{
	private File serviceDir;
	private File origFile;
	private File configFile;
	
	public CAEConfigureStep(File serviceDir) 
	{
		super();
		
		this.serviceDir = serviceDir;
	}
	
	public void runStep() 
		throws IOException
	{
		origFile = File.createTempFile("CAEConfigureStep", ".cfg.xml");
		configFile = new File(serviceDir, "cae-hibernate.cfg.xml");
		FileUtils.copy(configFile, origFile);
		
		String connectionUrl = System.getProperty("cae.connectionurl", 
			"jdbc:oracle:thin:@pparker:1521:TRIP"
		); 
		String user = System.getProperty("cae.username", 
			"caedba"
		); 
		String password = System.getProperty("cae.password", 
			"cae"
		); 
		
		FileUtils.replace(
			configFile,
			"<property name=\"connection.url\">jdbc:oracle:thin:@pparker:1521:trip</property>",
			"<property name=\"connection.url\">" + connectionUrl + "</property>"			
		);
		FileUtils.replace(
			configFile,
			"<property name=\"connection.username\">catissue_core</property>",
			"<property name=\"connection.username\">" + user + "</property>"			
		);
		FileUtils.replace(
			configFile,
			"<property name=\"connection.password\">catissue_core</property>",
			"<property name=\"connection.password\">" + password + "</property>"			
		);
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
