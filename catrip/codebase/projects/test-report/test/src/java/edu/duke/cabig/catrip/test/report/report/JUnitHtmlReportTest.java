/*
 * Created on Jul 20, 2006
 */
package edu.duke.cabig.catrip.test.report.report;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;

import junit.textui.TestRunner;

import org.xml.sax.SAXException;

import edu.duke.cabig.catrip.test.report.JUnitDoclet;
import edu.duke.cabig.catrip.test.report.JUnitXmlParser;
import edu.duke.cabig.catrip.test.report.data.TestSuite;

/**
 * This is a unit test that tests the html report generation functionality of the JUnitHtmlReport.
 * @testType unit
 * @author MCCON012
 */
public class JUnitHtmlReportTest
	extends junit.framework.TestCase
{
	public static final boolean MANUAL_TEST = true;
	
	public JUnitHtmlReportTest(String name)
	{
		super(name);
	}

	/**
	 * Tests that an html report can be generated wihtout using the testType tag
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void testUntypedHtmlReport() 
		throws SAXException, IOException, ParserConfigurationException
	{
		// parse results
		JUnitXmlParser parser = new JUnitXmlParser();
		parser.parse(new File(System.getProperty("junit.report", 
			"test" + File.separator + "resources" + File.separator + "TESTS-TestSuites.xml"
		)));
		TestSuite[] suites = parser.getTestSuites();
		
		// add documentation
		JUnitDoclet.addDocs(
			new File[] { 
				new File("src" + File.separator + "java"), 
				new File("test" + File.separator + "src" + File.separator + "java"),
			}, 
			suites
		);
		
		// print report
		File file = null;
		if (MANUAL_TEST) {
			file = new File("test" + File.separator + "logs" + File.separator + "junitDocReport.html");
		} else {
			file = File.createTempFile("JUnitHtmlReportTest", ".html");
			file.deleteOnExit();
		}
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
		JUnitHtmlReport report = new JUnitHtmlReport();
		report.writeReport(suites, false, out);
		out.flush();
		out.close();
		
		// read report
		String text = readText(file);
		if (! MANUAL_TEST) file.delete();
		
		// check report
	}
	
	/**
	 * Tests that an html report can be generated using the testType tag
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void testTypedHtmlReport() 
		throws SAXException, IOException, ParserConfigurationException
	{
		// parse results
		JUnitXmlParser parser = new JUnitXmlParser();
		parser.parse(new File(System.getProperty("junit.report", 
			"test" + File.separator + "resources" + File.separator + "TEST-edu.duke.cabig.catrip.test.report.JUnitDocletTest.xml"
		)));
		parser.parse(new File(System.getProperty("junit.report2", 
			"test" + File.separator + "resources" + File.separator + "TEST-edu.duke.cabig.catrip.test.report.JUnitXmlParserTest.xml"
		)));		
		TestSuite[] suites = parser.getTestSuites();
		
		// add documentation
		JUnitDoclet.addDocs(
			new File[] { 
				new File("src" + File.separator + "java"), 
				new File("test" + File.separator + "src" + File.separator + "java"),
			}, 
			suites
		);
		
		// print report
		File file = null;
		if (MANUAL_TEST) {
			file = new File("test" + File.separator + "logs" + File.separator + "junitDocReport.html");
		} else {
			file = File.createTempFile("JUnitHtmlReportTest", ".html");
			file.deleteOnExit();
		}
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
		JUnitHtmlReport report = new JUnitHtmlReport();
		report.writeReport(suites, true, out);
		out.flush();
		out.close();
		
		// read report
		String text = readText(file);
		if (! MANUAL_TEST) file.delete();
		
		// check report
	}

	/**
	 * Tests that an html report can be generated with using the testType tag on a Haste test
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void testHasteHtmlReport() 
		throws SAXException, IOException, ParserConfigurationException
	{
		// parse results
		JUnitXmlParser parser = new JUnitXmlParser();
		parser.parse(new File(System.getProperty("junit.report", 
			"test" + File.separator + "resources" + File.separator + "TESTS-TestSuites.xml"
		)));
		TestSuite[] suites = parser.getTestSuites();
		
		// add documentation
		File projectDir = new File("..", "test-system");
		JUnitDoclet.addDocs(
			new File[] { 
				new File(projectDir, "src" + File.separator + "java"), 
				new File(projectDir, "test" + File.separator + "src" + File.separator + "java"),
			}, 
			suites
		);
		JUnitDoclet.addDocs(
			new File[] { 
				new File("src" + File.separator + "java"), 
				new File("test" + File.separator + "src" + File.separator + "java"),
			}, 
			suites
		);
		
		// print report
		File file = null;
		if (MANUAL_TEST) {
			file = new File("test" + File.separator + "logs" + File.separator + "junitDocReport.html");
		} else {
			file = File.createTempFile("JUnitHtmlReportTest", ".html");
			file.deleteOnExit();
		}
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
		JUnitHtmlReport report = new JUnitHtmlReport();
		report.writeReport(suites, true, out);
		out.flush();
		out.close();
		
		// read report
		String text = readText(file);
		if (! MANUAL_TEST) file.delete();
		
		// check report
	}
	
	private String readText(File file) 
		throws IOException
	{
		StringBuffer buf = new StringBuffer((int) file.length());
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		try {
			char[] ch = new char[1024];
			int count = 0;
			while ((count = br.read(ch)) != -1) buf.append(ch, 0, count);
		} finally {
			try { br.close(); } catch (IOException e) { e.printStackTrace(); }
		}
		
		return buf.toString();
	}
	
	/**
	 * Run the tests
	 * @param args ignored
	 */
	public static void main(String[] args)
	{
		TestRunner runner = new TestRunner();
		junit.framework.TestResult result = runner.doRun(new junit.framework.TestSuite(JUnitHtmlReportTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}

}
