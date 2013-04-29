/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class AdverseEventReport {

	private boolean filedIndicator;
	private long id;
	private Date submissionDate;
	private Set adverseEventCollection;

	public AdverseEventReport(){

	}

	public Set getAdverseEventCollection() {
		return adverseEventCollection;
	}

	public void setAdverseEventCollection(Set adverseEventCollection) {
		this.adverseEventCollection = adverseEventCollection;
	}

	public boolean isFiledIndicator() {
		return filedIndicator;
	}

	public void setFiledIndicator(boolean filedIndicator) {
		this.filedIndicator = filedIndicator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

}