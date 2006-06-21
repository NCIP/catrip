package edu.pitt.cabig.cae.domain.general;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:36 PM
 */
public class Specimen extends AnnotatableEntity {

	public Specimen specimenCollection;

	public Specimen(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setSpecimenCollection(Specimen specimenCollection) {
        this.specimenCollection = specimenCollection;
    }

    public Specimen getSpecimenCollection() {
        return specimenCollection;
    }
}
