/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Jul 19, 2006
 */
package edu.duke.cabig.catrip.test.report.report;

import java.io.PrintStream;

import edu.duke.cabig.catrip.test.report.data.TestSuite;

public interface JUnitReport
{
	public void writeReport(TestSuite[] suites, boolean useTestType, PrintStream out)
		throws Exception;
}
