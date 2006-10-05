package edu.duke.cabig.tumorregistry.domain;

import java.util.Date;
import java.lang.Integer;

/**
 * An object representing only the first course of Radiation procedure; including
 * dosages, locations, stop and start dates
 * @version 1.0
 * @created 04-Oct-2006 10:52:16 AM
 */
public class FirstCourseRadiation extends Radiation {

	private Date stopDate;
	private String numberOfTreatments;
	private String location;
	private String volume;
	private Integer regionalDose;
	private Integer boostDose;
	private String boostModality;
	private String regionalModality;

	public FirstCourseRadiation(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Integer getBoostDose() {
		return boostDose;
	}

	public void setBoostDose(Integer boostDose) {
		this.boostDose = boostDose;
	}

	public String getBoostModality() {
		return boostModality;
	}

	public void setBoostModality(String boostModality) {
		this.boostModality = boostModality;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNumberOfTreatments() {
		return numberOfTreatments;
	}

	public void setNumberOfTreatments(String numberOfTreatments) {
		this.numberOfTreatments = numberOfTreatments;
	}

	public Integer getRegionalDose() {
		return regionalDose;
	}

	public void setRegionalDose(Integer regionalDose) {
		this.regionalDose = regionalDose;
	}

	public String getRegionalModality() {
		return regionalModality;
	}

	public void setRegionalModality(String regionalModality) {
		this.regionalModality = regionalModality;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

}