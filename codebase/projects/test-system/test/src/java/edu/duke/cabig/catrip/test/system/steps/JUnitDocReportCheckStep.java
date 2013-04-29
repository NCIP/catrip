/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jul 20, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import java.io.File;

import com.atomicobject.haste.framework.Step;

/**
 * This test case ensures that a JUnitDocReport result file has been generated.
 * @author MCCON012
 */
public class JUnitDocReportCheckStep
	extends Step
{
	private File destFile;
	
	public JUnitDocReportCheckStep(File destFile)
	{
		super();
		
		this.destFile = destFile;
	}
	
	@Override
	public void runStep() throws Throwable
	{
		assertTrue(destFile.exists());
	}
}
