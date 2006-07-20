/*
 * Created on Jul 18, 2006
 */
package edu.duke.cabig.catrip.test.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.duke.cabig.catrip.test.report.data.TestCase;
import edu.duke.cabig.catrip.test.report.data.TestFailure;
import edu.duke.cabig.catrip.test.report.data.TestSuite;

public class JUnitXmlParser
{
	private ArrayList<TestSuite> testSuites = new ArrayList<TestSuite>();
	
	public JUnitXmlParser()
	{
		super();
	}
	
	public void parse(File file) 
		throws SAXException, IOException, ParserConfigurationException
	{
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		JUnitXmlHandler handler = new JUnitXmlHandler();
		parser.parse(file, handler);
	}
	
	public TestSuite[] getTestSuites()
	{
		return testSuites.toArray(new TestSuite[0]);
	}
	
	private class JUnitXmlHandler
		extends DefaultHandler
	{
		private StringBuffer chars = new StringBuffer();

		public JUnitXmlHandler()
		{
			super();
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException
		{
			chars.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException
		{
			if (qName.equals("failure") || qName.equals("error")) {
				TestSuite suite = testSuites.get(testSuites.size()-1);
				TestCase test = suite.testCases.get(suite.testCases.size()-1);
				test.failure.stackTrace = chars.toString();
			}
			
			chars.delete(0, chars.length());
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
		{
			chars.delete(0, chars.length());
			
			if (qName.equals("testsuite")) {
				TestSuite testSuite = new TestSuite();
				testSuite.errors = Integer.parseInt(atts.getValue("errors"));
				testSuite.failures = Integer.parseInt(atts.getValue("failures"));
				testSuite.name = atts.getValue("name");
				if (atts.getValue("package") != null) {
					testSuite.name = atts.getValue("package") + "." + testSuite.name;
				}
				testSuite.tests = Integer.parseInt(atts.getValue("tests"));
				testSuite.time = Double.parseDouble(atts.getValue("time"));
				testSuites.add(testSuite);
			} else if (qName.equals("property")) {
				testSuites.get(testSuites.size()-1).props.setProperty(
					atts.getValue("name"), atts.getValue("value")
				);
			} else if (qName.equals("testcase")) {
				TestCase testCase = new TestCase();
				testCase.className = atts.getValue("classname");
				testCase.name = atts.getValue("name");
				testCase.time = Double.parseDouble(atts.getValue("time"));
				testSuites.get(testSuites.size()-1).testCases.add(testCase);
			} else if (qName.equals("failure") || qName.equals("error")) {
				TestSuite suite = testSuites.get(testSuites.size()-1);
				TestCase test = suite.testCases.get(suite.testCases.size()-1);
				test.failure = new TestFailure();
				test.failure.isError = qName.equals("error");
				test.failure.type = atts.getValue("type");
			}
		}
	}
}
