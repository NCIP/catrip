/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jul 18, 2006
 */
package edu.duke.cabig.catrip.test.report.data;

import java.util.ArrayList;
import java.util.Properties;

public class TestSuite
{
	public int errors;
	public int failures;
	public String name;
	public int tests;
	public double time;
	
	public Properties props = new Properties();
	public ArrayList<TestCase> testCases = new ArrayList<TestCase>();
	public ArrayList<TestStep> testSteps = new ArrayList<TestStep>();
	
	public String docText;
	public Properties docTags = new Properties();
}
