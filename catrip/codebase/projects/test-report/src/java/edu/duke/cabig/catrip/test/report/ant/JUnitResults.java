/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report.ant;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.FileSet;

public class JUnitResults
{
	protected List<FileSet> fileSetList = new ArrayList<FileSet>();

	public void addConfiguredFileSet(FileSet s) 
	{
		fileSetList.add(s);
	}
}
