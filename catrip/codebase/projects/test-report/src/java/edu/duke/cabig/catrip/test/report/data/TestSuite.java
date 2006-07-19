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
	
	public String docs;
}
