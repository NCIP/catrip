package edu.pitt.cabig.cae.domain.general;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:34 PM
 */
public class Participant extends AnnotatableEntity {

	public Accession accessionCollection;

	public Participant(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setAccessionCollection(Accession accessionCollection) {
        this.accessionCollection = accessionCollection;
    }

    public Accession getAccessionCollection() {
        return accessionCollection;
    }
}
