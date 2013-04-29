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
 * @created 20-Jun-2006 8:25:13 AM
 */
public class StudyTimePoint {

	private int courseNumber;
	private Date courseStartDate;
	private Date courseStopDate;
	/**
	 * Values include: Baseline, Screening, Run-in, Treatment, Follow-Up, etc.
	 * 
	 * NOTE: When pre-study or medical history information is collected -- the epoch
	 * would be "Pre-Study";  relevant attributes in Activity, Observation and
	 * Assessment will be defaulted accordingly. 
	 */
	private String epochName;
	private int id;
	private String visitName;
	//private Activity activity;

	public StudyTimePoint(){

	}


	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public Date getCourseStopDate() {
		return courseStopDate;
	}

	public void setCourseStopDate(Date courseStopDate) {
		this.courseStopDate = courseStopDate;
	}

	public String getEpochName() {
		return epochName;
	}

	public void setEpochName(String epochName) {
		this.epochName = epochName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

}