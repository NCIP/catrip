/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

public class XMIParserTest
	extends AbstractBaseTest
{
	private File outDir = null;
	
	public XMIParserTest(String name)
	{
		super(name);
		
		this.outDir = new File(System.getProperty("XMIParserTest.out.dir",
			"test" + File.separator + "resources" + File.separator + "XMIParser"
		));	
		outDir.mkdirs();
	}

	public void performTest(File xmiFile, File modelFile) throws Exception
	{
		XMIParser parser = new XMIParser(projectShortName, projectVersion);
		parser.setProjectLongName(projectLongName);
		parser.setProjectDescription(projectDescription);
		DomainModel model = parser.parse(xmiFile);

		File outFile = new File(outDir, getModelName(xmiFile));
		XMIParser.writeDomainModel(model, outFile);

		checkModel(modelFile, outFile);
	}
	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(XMIParserTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
