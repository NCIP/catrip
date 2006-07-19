/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Hashtable;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;

import edu.duke.cabig.catrip.test.report.data.TestCase;
import edu.duke.cabig.catrip.test.report.data.TestSuite;

public class JUnitDoclet
{
	private static Object mutex = new Object();
	private static Hashtable<String,TestSuite> suiteTable = null;
	private static Hashtable<String,TestCase> testTable = null;
	
	public static void addDocs(File[] srcDirs, TestSuite[] suites)
		throws IOException
	{
		// only one at a time is possible
		synchronized (mutex) 
		{
			// build lookup tables
			suiteTable = new Hashtable<String,TestSuite>();
			testTable = new Hashtable<String,TestCase>();
			for (TestSuite suite : suites) {
				suiteTable.put(suite.pkg + "." + suite.name, suite);
				for (TestCase test : suite.testCases) {
					testTable.put(test.className + "." + test.name, test);
				}
			}
			
			// loop through srcDirs
			for (File srcDir : srcDirs) {
				// loop through root packages in srcDirs
				String[] pkgs = findPackages(srcDir);
				for (String pkg : pkgs) {
					// perform doclet
					Main.execute(new String[] {
						"-doclet", JUnitDoclet.class.getName(), 
						"-sourcepath", srcDir.toString(),
						pkg
					});
				}
			}
			
			// clear lookup tables
			suiteTable = null;
			testTable = null;
		}
	}
	
	private static String[] findPackages(File srcDir)
	{
		return new String[0];
	}

	/**
	 * Doclet entry point
	 */
	public static boolean start(RootDoc root) 
	{
        ClassDoc[] classes = root.classes();
        for (ClassDoc cl : root.classes()) {
        	TestSuite suite = suiteTable.get(cl.qualifiedName());
        	if (suite == null) continue;
        	suite.docs = cl.commentText();

        	for (MethodDoc m : cl.methods()) {
        		TestCase test = testTable.get(cl.qualifiedName() + "." + m.name());
        		if (test == null) continue;
        		
        		test.docs = m.commentText();
        	}
        }
        return true;
    }	

}
