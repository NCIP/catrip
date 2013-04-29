/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

public interface DeIdService
{
	public String deid(String phi) throws Exception;
}
