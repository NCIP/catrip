package edu.pitt.cabig.cae.domain.general;
import java.lang.String;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:36 PM
 */
public class SpecimenIdentifiers extends AnnotationSet {

	private String identifier;
	private String surgicalLabel;

	public SpecimenIdentifiers(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setSurgicalLabel(String surgicalLabel) {
        this.surgicalLabel = surgicalLabel;
    }

    public String getSurgicalLabel() {
        return surgicalLabel;
    }
}
