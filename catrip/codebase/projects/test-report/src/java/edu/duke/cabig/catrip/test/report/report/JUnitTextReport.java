/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import edu.duke.cabig.catrip.test.report.data.TestCase;
import edu.duke.cabig.catrip.test.report.data.TestSuite;

public class JUnitTextReport
	implements JUnitReport
{
	public void writeReport(TestSuite[] suites, PrintStream out)
		throws Exception
	{
		out.println("JUnitTextReport");
		
		for (TestSuite suite : suites) {
			out.println();
			out.println("====================================================");
			out.println();
			out.println("Test suite: " + suite.name);
			out.println("  Description: " + (suite.docs == null || suite.docs.equals("") ? "NA" : suite.docs));
			out.println("  Total tests: " + suite.tests);
			out.println("  Errors: " + suite.errors);
			out.println("  Failures: " + suite.failures);
			out.println("  Time (sec): " + suite.time);
			
			for (TestCase test : suite.testCases) {
				out.println();
				out.println("  Test: " + test.name + " " + (test.failure == null ? "SUCCESSFUL" : "FAILED - " + test.failure.type) + " (" + test.time + " secs)");
				out.println("    Description: " + (test.docs == null || test.docs.equals("") ? "NA" : test.docs));
				if (test.failure != null) { 
					out.println(addSpaces(test.failure.stackTrace, 6));
				}
			}
		}
	}

	private String addSpaces(String s, int spaceCount) 
		throws IOException
	{
		char[] ch = new char[spaceCount];
		for (int i = 0; i < spaceCount; i++) ch[i] = ' ';
		String spaces = new String(ch);
		
		StringBuffer buf = new StringBuffer();
		BufferedReader br = new BufferedReader(new StringReader(s));
		String line = null;
		while ((line = br.readLine()) != null) {
			buf.append(spaces);
			buf.append(line);
			buf.append(System.getProperty("line.separator"));
		}
		br.close();
		
		return buf.toString();
	}
}
