package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 27-Jun-2006 12:44:09 PM
 */
public class HealthcareSiteParticipantRole {

	public int id;
	public String roleCode;
	//public HealthcareSiteParticipant healthcareSiteParticipant;

	public HealthcareSiteParticipantRole(){

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}