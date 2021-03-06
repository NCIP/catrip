/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Oct 18, 2006
 */
package edu.duke.cabig.catrip.xmi2model;

import java.io.File;
import java.io.FileFilter;

import junit.framework.TestCase;

public abstract class AbstractBaseTest
	extends TestCase
{
	protected File xmiDir = null;
	protected File modelsDir = null;
	protected String projectShortName = null;
	protected String projectLongName = null;
	protected String projectVersion = null;
	protected String projectDescription = null;
	
	public AbstractBaseTest(String name)
	{
		super(name);
		
		this.xmiDir = new File(System.getProperty("xmi.dir",
			"test" + File.separator + "resources" + File.separator + "xmi"
		));
		this.modelsDir = new File(System.getProperty("models.dir",
			"test" + File.separator + "resources" + File.separator + "models"
		));
		this.projectShortName = System.getProperty("projectShortName", "Tumor Registry");
		this.projectLongName = System.getProperty("projectLongName", "Tumor Registry");
		this.projectVersion = System.getProperty("projectVersion", "1");
		this.projectDescription = System.getProperty("projectDescription", "Tumor Registry");
	}
	
	public abstract void performTest(File xmiFile, File modelFile) throws Exception;
	
	public void testModelGeneration()
		throws Exception
	{
		File[] xmiFiles = xmiDir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile() && ! file.getName().equals(".cvsignore");
			}
		});
		
		for (File xmiFile : xmiFiles) {
			File modelFile = new File(modelsDir, getModelName(xmiFile));
			if (! modelFile.exists()) modelFile = null;
			
			performTest(xmiFile, modelFile);
		}
	}
	
	protected void checkModel(File modelFile, File outFile)
	{
		assertTrue(outFile.exists());
		if (modelFile == null) return;
	}
	
	protected String getModelName(File xmiFile)
	{
		String fileName = xmiFile.getName();
		fileName = fileName.substring(0, fileName.lastIndexOf("."));
		
		return fileName + "_model.xml";
	}
}
