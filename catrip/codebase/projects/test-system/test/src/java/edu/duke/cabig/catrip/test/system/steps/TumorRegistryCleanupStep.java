/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import java.io.IOException;

import com.atomicobject.haste.framework.Step;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

/**
 * Cleans up configuration files for a Tumor Registry service deployment.
 * @author Patrick McConnell
 */
public class TumorRegistryCleanupStep
	extends Step
{
	private TumorRegistryConfigureStep configStep;
	
	public TumorRegistryCleanupStep(TumorRegistryConfigureStep configStep) 
	{
		super();
		
		this.configStep = configStep;
	}
	
	public void runStep() 
		throws IOException
	{
		if (configStep.getOrigFile().exists()) {
			FileUtils.copy(configStep.getOrigFile(), configStep.getConfigFile());
			configStep.getOrigFile().delete();
		}
	}
}
