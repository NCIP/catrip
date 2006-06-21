package edu.pitt.cabig.cae.domain.general;
import java.util.Date;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:28 PM
 */
public class AccessionIdentifiers extends AnnotationSet {

	private Date accessionDate;
	private String surgicalPathologyNumber;

	public AccessionIdentifiers(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setAccessionDate(Date accessionDate) {
        this.accessionDate = accessionDate;
    }

    public Date getAccessionDate() {
        return accessionDate;
    }

    public void setSurgicalPathologyNumber(String surgicalPathologyNumber) {
        this.surgicalPathologyNumber = surgicalPathologyNumber;
    }

    public String getSurgicalPathologyNumber() {
        return surgicalPathologyNumber;
    }
}
