/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid.util;

import java.util.Random;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class RandomUtilsTest
	extends TestCase
{
	public RandomUtilsTest(String name)
	{
		super(name);
	}
	
	public void testGenerateRandomValue()
	{
		char[] ch = RandomUtils.generateRandomValue(new Random()).toCharArray();
		assertEquals(RandomUtils.DEFAULT_RANDOM_SIZE, ch.length);
		for (char c : ch) {
			assertTrue(Character.isLetterOrDigit(c));
		}
	}	
	public static void main(String[] args) throws Exception
	{
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(RandomUtilsTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}
