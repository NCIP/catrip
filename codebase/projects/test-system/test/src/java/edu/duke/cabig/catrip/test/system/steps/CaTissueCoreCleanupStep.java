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

import java.io.IOException;

import com.atomicobject.haste.framework.Step;

import gov.nci.nih.cagrid.tests.core.util.FileUtils;

/**
 * Cleans up configuration files for a caTissue CORE service deployment.
 * @author Patrick McConnell
 */
public class CaTissueCoreCleanupStep
	extends Step
{
	private CaTissueCoreConfigureStep configStep;
	
	public CaTissueCoreCleanupStep(CaTissueCoreConfigureStep configStep) 
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
