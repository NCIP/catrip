package gov.nih.nci.ctom.domain;

import java.sql.Date;

/**
 * @version 1.0
 * @created 26-Jun-2006 8:35:16 PM
 */
public class PersonOccupation {

	private int id;
	/**
	 * [Additional Documentation] www.osha.gov/cgi-bin/sic/sicser5 - This is paired
	 * with Occupation Primary-Industry. 4 Digit SIC Codes. [End Documentation]
	 */
	private String primaryTypeCode;
	private String primaryTypeCodeSystem;
	private Date startDate;
	private Date stopDate;

	public PersonOccupation(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimaryTypeCode() {
		return primaryTypeCode;
	}

	public void setPrimaryTypeCode(String primaryTypeCode) {
		this.primaryTypeCode = primaryTypeCode;
	}

	public String getPrimaryTypeCodeSystem() {
		return primaryTypeCodeSystem;
	}

	public void setPrimaryTypeCodeSystem(String primaryTypeCodeSystem) {
		this.primaryTypeCodeSystem = primaryTypeCodeSystem;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

}