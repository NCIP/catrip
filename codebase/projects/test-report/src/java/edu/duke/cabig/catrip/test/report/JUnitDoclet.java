/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.Tag;

import edu.duke.cabig.catrip.test.report.data.TestCase;
import edu.duke.cabig.catrip.test.report.data.TestStep;
import edu.duke.cabig.catrip.test.report.data.TestSuite;
import edu.duke.cabig.catrip.test.report.javadoc.Main;

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
				suiteTable.put(suite.name, suite);
				for (TestCase test : suite.testCases) {
					testTable.put(test.className + "." + test.name, test);
				}
			}
			
			// loop through srcDirs
			for (File srcDir : srcDirs) {
				// get packages in srcDirs
				String[] pkgs = findPackages(srcDir);
				if (pkgs.length == 0) continue;
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
	@SuppressWarnings("deprecation")
	public static boolean start(RootDoc root) 
	{
		Hashtable<String,ClassDoc> classTable = new Hashtable<String,ClassDoc>(root.classes().length);
		for (ClassDoc cl : root.classes()) {
			classTable.put(cl.name(), cl);
		}
		
        ClassDoc[] classes = root.classes();
        for (ClassDoc cl : root.classes()) {
        	TestSuite suite = suiteTable.get(cl.qualifiedName());
        	if (suite == null) continue;
        	
        	suite.docText = cl.commentText();
    		for (Tag tag : cl.tags()) {
    			suite.docTags.setProperty(tag.name().substring(1), tag.text());
    		}
    		
    		boolean isStory = isStory(cl); 
    		
    		if (isStory) {
    			Hashtable<String,TestStep> stepTable = new Hashtable<String,TestStep>();
    			
    			// add imports
    			for (ClassDoc iCl : cl.importedClasses()) {
    				if (!(
    					iCl.name().endsWith("Step") || 
    					(iCl.superclass() != null && iCl.superclass().name().equals("Step"))
    				)) { continue; }
    				
   					TestStep test = new TestStep();
					test.className = cl.qualifiedName();
	        		test.name = iCl.name();
	        		
					test.docText = iCl.commentText();
	        		for (Tag tag : iCl.tags()) {
	        			test.docTags.setProperty(tag.name().substring(1), tag.text());
	        		}
	        		stepTable.put(iCl.qualifiedName(), test);
    			}
    			
    			// add tagged steps
    			Tag[] stepTags = cl.tags("steps");
    			ArrayList<String> stepNames = new ArrayList<String>();
    			for (Tag stepTag : stepTags) {
    				for (String stepName : stepTag.text().split("\\s*,\\s*")) {
    					ClassDoc iCl = classTable.get(stepName.trim());
    					if (iCl == null) continue;
    					
       					TestStep test = new TestStep();
    					test.className = cl.qualifiedName();
    	        		test.name = iCl.name();

    					test.docText = iCl.commentText();
    	        		for (Tag tag : iCl.tags()) {
    	        			test.docTags.setProperty(tag.name().substring(1), tag.text());
    	        		}
    	        		
    	        		stepTable.put(iCl.qualifiedName(), test);
    	        		stepNames.add(stepName);
    				}
    			}

    			// sort stepList
    			ArrayList<TestStep> stepList = new ArrayList<TestStep>(stepTable.values());
				if (stepNames.size() == stepList.size()) {
					try {
						stepList = getSortedSteps(stepNames.toArray(new String[0]), stepList);
					} catch (Exception e) {
						System.out.println("ERROR: " + e.getMessage()); 
					}
				}
				suite.testSteps.addAll(stepList);
    		} else {
	        	for (MethodDoc m : cl.methods()) {
	        		TestCase test = testTable.get(cl.qualifiedName() + "." + m.name());
	        		if (test == null) continue;
	        		
	        		test.docText = m.commentText();
	        		for (Tag tag : m.tags()) {
	        			test.docTags.setProperty(tag.name().substring(1), tag.text());
	        		}
	        	}
    		}
        }
        return true;
    }

	private static boolean isStory(ClassDoc cl)
	{
		do  {
			if (cl.superclassType() == null) break;
			if (cl.superclassType().qualifiedTypeName().equals("Story")) return true;
		} while ((cl = cl.superclass()) != null);
		return false;
	}

	private static ArrayList<TestStep> getSortedSteps(String[] stepNames, ArrayList<TestStep> testSteps) 
		throws Exception
	{
		ArrayList<TestStep> sortedTestSteps = new ArrayList<TestStep>(testSteps.size());
		
		for (String stepName : stepNames) {
			TestStep foundStep = null;
			for (TestStep step : testSteps) {
				if (step.name.equals(stepName.trim())) {
					foundStep = step;
					break;
				}
			}
			if (foundStep == null) throw new Exception("test " + stepName + " not found");
			sortedTestSteps.add(foundStep);
		}
		
		return sortedTestSteps;
	}
}
