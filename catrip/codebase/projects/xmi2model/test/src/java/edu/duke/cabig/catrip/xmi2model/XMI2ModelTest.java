/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

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
		DomainModel model = new XMIParser().parse(xmiFile);

		File outFile = new File(outDir, getModelName(xmiFile));
		XMI2Model.main(new String[] {
			"-xmi", xmiFile.toString(),
			"-model", outFile.toString(),
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
