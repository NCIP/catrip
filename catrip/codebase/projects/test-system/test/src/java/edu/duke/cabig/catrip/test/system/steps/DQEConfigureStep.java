/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

import java.io.File;
import java.io.IOException;

import com.atomicobject.haste.framework.Step;

public class DQEConfigureStep
	extends Step
{
	private File dqeConfigFile;
	private int port;
	
	public DQEConfigureStep(File dqeConfigFile, int port) 
	{
		super();
		
		this.dqeConfigFile = dqeConfigFile;
		this.port = port;
	}
	
	public void runStep() 
		throws IOException
	{
		File configDir = new File(System.getProperty("user.home"), ".caTRIP");
		configDir.mkdir();
		File dqeConfigFile = new File(configDir, "query_engine_services_config.xml");
		FileUtils.copy(this.dqeConfigFile, dqeConfigFile);
		FileUtils.replace(
			dqeConfigFile, "8080", String.valueOf(port)
		);
	}
}
