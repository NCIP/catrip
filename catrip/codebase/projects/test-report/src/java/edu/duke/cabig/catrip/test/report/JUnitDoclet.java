/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
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
				// get packages in srcDirs
				String[] pkgs = findPackages(srcDir);
				String[] args = new String[4 + pkgs.length];

				// perform doclet
				args[0] = "-doclet";
				args[1] = JUnitDoclet.class.getName();
				args[2] = "-sourcepath";
				args[3] = srcDir.toString();
				System.arraycopy(pkgs, 0, args, 4, pkgs.length);
				Main.execute(args);
			}
			
			// clear lookup tables
			suiteTable = null;
			testTable = null;
		}
	}
	
	private static String[] findPackages(File srcDir)
	{
		return findPackages(srcDir, "");
	}
	
	private static String[] findPackages(File rootDir, String path)
	{
		File dir = new File(rootDir + File.separator + path);
		File[] files = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".java")) || 
						(file.isDirectory() && ! file.getName().equals("CVS")); 
			}
		});
		
		HashSet<String> pkgs = new HashSet<String>();
		for (File file : files) {
			if (file.isDirectory()) {
				String nextPath = path + File.separator + file.getName();
				if (nextPath.startsWith(File.separator)) nextPath = nextPath.substring(File.separator.length());
				Collections.addAll(pkgs, findPackages(rootDir, nextPath));
			} else {
				pkgs.add(path.replace(File.separatorChar, '.'));
			}
		}
		return pkgs.toArray(new String[0]);
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
