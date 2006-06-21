package edu.pitt.cabig.cae.domain.general;
import java.lang.String;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:28 PM
 */
public class Accession extends AnnotatableEntity {

	private String diseaseType;
	public Specimen specimenCollection;

	public Accession(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setSpecimenCollection(Specimen specimenCollection) {
        this.specimenCollection = specimenCollection;
    }

    public Specimen getSpecimenCollection() {
        return specimenCollection;
    }
}
