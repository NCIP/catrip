/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;


import java.sql.Date;

/**
 * @version 1.0
 * @created 15-Jun-2006 8:28:41 AM
 */
public class ProtocolStatus {

	/**
	 * Values include: C-Closed, O-Open, S-Suspended, T-Terminated.
	 */
	private int id;
	/**
	 * Values include: C-Closed, O-Open, S-Suspended, T-Terminated.
	 */
	private String statusCode;
	private Date statusDate;

	public ProtocolStatus(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

}