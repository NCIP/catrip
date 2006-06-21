package edu.pitt.cabig.cae.domain.general;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:31 PM
 */
public class Demographics extends AnnotationSet {

	private String gender;
	private String race;
	private String ethnicity;

	public Demographics(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRace() {
        return race;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEthnicity() {
        return ethnicity;
    }
}
