package edu.duke.cabig.tumorregistry.domain;
import java.util.Date;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:21 PM
 */
public class SubstanceAdministration extends Activity {

	private Date stopDate;

	public SubstanceAdministration(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

}