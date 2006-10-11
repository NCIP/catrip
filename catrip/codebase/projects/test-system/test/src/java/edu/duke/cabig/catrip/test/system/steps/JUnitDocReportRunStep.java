/*
 * Created on Jul 20, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import gov.nci.nih.cagrid.tests.core.util.AntUtils;

import java.io.File;

import com.atomicobject.haste.framework.Step;

/**
 * This test case runs a JUnitDocReport ant file to generate results.
 * @author MCCON012
 */
public class JUnitDocReportRunStep
	extends Step
{
	private File antFile;
	
	public JUnitDocReportRunStep(File antFile)
	{
		super();
		
		this.antFile = antFile;
	}
	
	@Override
	public void runStep() throws Throwable
	{
		AntUtils.runAnt(antFile.getParentFile(), antFile.getName(), "runreport", null, null);
	}
}
