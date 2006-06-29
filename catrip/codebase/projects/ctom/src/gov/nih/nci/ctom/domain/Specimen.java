package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 26-Jun-2006 9:10:44 AM
 */
public class Specimen {

	public int id;
	public int sampleIdentifier;
	/**
	 * Values include: V-Saliva, A-Aphaeresis Cells, B-Whole Blood, C-CSF, etc.
	 */
	public String sampleTypeCode;
	public int volume;
	public String volumeUnitOfMeasureCode;

	public Specimen(){

	}

	public void finalize() throws Throwable {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSampleIdentifier() {
		return sampleIdentifier;
	}

	public void setSampleIdentifier(int sampleIdentifier) {
		this.sampleIdentifier = sampleIdentifier;
	}

	public String getSampleTypeCode() {
		return sampleTypeCode;
	}

	public void setSampleTypeCode(String sampleTypeCode) {
		this.sampleTypeCode = sampleTypeCode;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getVolumeUnitOfMeasureCode() {
		return volumeUnitOfMeasureCode;
	}

	public void setVolumeUnitOfMeasureCode(String volumeUnitOfMeasureCode) {
		this.volumeUnitOfMeasureCode = volumeUnitOfMeasureCode;
	}

}