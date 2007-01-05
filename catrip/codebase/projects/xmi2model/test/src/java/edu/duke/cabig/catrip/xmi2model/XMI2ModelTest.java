/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This is a unit test to test the functionality of XMI2Model, which is the main entry point of 
 * the application.
 * @author Patrick McConnell
 * @testType unit
 */
public class XMI2ModelTest
	extends AbstractBaseTest
{
	private File outDir = null;

	public XMI2ModelTest(String name)
	{
		super(name);
		
		this.outDir = new File(System.getProperty("XMI2ModelTest.out.dir",
			"test" + File.separator + "resources" + File.separator + "XMI2Model"
		));	
		outDir.mkdirs();
	}

	public void performTest(File xmiFile, File modelFile) throws Exception
	{
		File outFile = new File(outDir, getModelName(xmiFile));
		XMI2Model.main(new String[] {
			"-xmi", xmiFile.toString(),
			"-model", outFile.toString(),
			"-projectShortName", projectShortName,
			"-projectVersion", projectVersion,
			"-projectLongName", projectLongName,
			"-projectDescription", projectDescription,
		});
		
		checkModel(modelFile, outFile);
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(XMI2ModelTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
