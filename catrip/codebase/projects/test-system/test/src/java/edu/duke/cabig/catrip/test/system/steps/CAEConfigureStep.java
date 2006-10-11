/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

import java.io.File;
import java.io.IOException;

import com.atomicobject.haste.framework.Step;

public class CAEConfigureStep
	extends Step
{
	private File serviceDir;
	
	public CAEConfigureStep(File serviceDir) 
	{
		super();
		
		this.serviceDir = serviceDir;
	}
	
	public void runStep() 
		throws IOException
	{
		FileUtils.replace(
			new File(serviceDir, "cae-hibernate.cfg.xml"),
			"jdbc:oracle:thin:@localhost:1521:agile",
			System.getProperty("cae.connection.url", "jdbc:oracle:thin:@pparker:1521:TRIP")
		);
	}
}
