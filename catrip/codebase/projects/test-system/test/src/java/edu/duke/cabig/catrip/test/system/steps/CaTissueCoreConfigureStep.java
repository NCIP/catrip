/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

import java.io.File;
import java.io.IOException;

import com.atomicobject.haste.framework.Step;

public class CaTissueCoreConfigureStep
	extends Step
{
	private File serviceDir;
	
	public CaTissueCoreConfigureStep(File serviceDir) 
	{
		super();
		
		this.serviceDir = serviceDir;
	}
	
	public void runStep() 
		throws IOException
	{
		FileUtils.replace(
			new File(serviceDir, "catissuecore-hibernate.cfg.xml"),
			"jdbc:mysql://localhost:3306/catissuecore",
			System.getProperty("catissuecore.connection.url", "jdbc:mysql://catrip1.duhs.duke.edu:3306/catissuecore")
		);
	}
}
