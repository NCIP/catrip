/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;


import java.util.Set;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class CancerStage {

	private long id;
	private String stageCode;
	private String stageCodeSystem;
	private String tnmStage;
	private String tnmStageCodeSystem;
	private Set metastasisSiteCollection;

	public CancerStage(){

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set getMetastasisSiteCollection() {
		return metastasisSiteCollection;
	}

	public void setMetastasisSiteCollection(Set metastasisSiteCollection) {
		this.metastasisSiteCollection = metastasisSiteCollection;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public String getStageCodeSystem() {
		return stageCodeSystem;
	}

	public void setStageCodeSystem(String stageCodeSystem) {
		this.stageCodeSystem = stageCodeSystem;
	}

	public String getTnmStage() {
		return tnmStage;
	}

	public void setTnmStage(String tnmStage) {
		this.tnmStage = tnmStage;
	}

	public String getTnmStageCodeSystem() {
		return tnmStageCodeSystem;
	}

	public void setTnmStageCodeSystem(String tnmStageCodeSystem) {
		this.tnmStageCodeSystem = tnmStageCodeSystem;
	}

}