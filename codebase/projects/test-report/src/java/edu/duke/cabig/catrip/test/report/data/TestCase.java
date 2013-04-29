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

import java.util.Properties;

public class TestCase
{
	public String className;
	public String name;
	public double time;
	public TestFailure failure;
	
	public String docText;
	public Properties docTags = new Properties();
}
