package edu.pitt.cabig.cae.domain.breast;

import edu.pitt.cabig.cae.domain.general.AnnotationSet;

/**
 * @version 1.0
 * @created 15-Sep-2006 3:19:24 PM
 */
public class BreastCancerBiomarkers extends AnnotationSet {
	
	private static final long serialVersionUID = 1234567890L;
	private String estrogenReceptor;
	private String progesteroneReceptor;
	private String HER2Status;
	private String HER2TestType;

	public BreastCancerBiomarkers(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getEstrogenReceptor() {
		return estrogenReceptor;
	}

	public void setEstrogenReceptor(String estrogenReceptor) {
		this.estrogenReceptor = estrogenReceptor;
	}

	public String getHER2Status() {
		return HER2Status;
	}

	public void setHER2Status(String status) {
		HER2Status = status;
	}

	public String getHER2TestType() {
		return HER2TestType;
	}

	public void setHER2TestType(String testType) {
		HER2TestType = testType;
	}

	public String getProgesteroneReceptor() {
		return progesteroneReceptor;
	}

	public void setProgesteroneReceptor(String progesteroneReceptor) {
		this.progesteroneReceptor = progesteroneReceptor;
	}

}