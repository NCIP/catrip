package gov.nih.nci.ctom.domain;

/**
 * This class is a prototype implementation of a complex data type.  This is
 * similar to HL7 complex data type.  In this release of CTOM, we are only
 * implementing CD, future releases of CTOM will implement other complex data
 * types, such as, EN, ADDR, Telecom, etc.
 * @author Smita Hastak
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class ConceptDescriptor {

	private String code;
	private String codeSystem;
	private String codeSystemName;
	private int codeSystemVersion;
	private String displayText;
	private int id;

	public ConceptDescriptor(){

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeSystem() {
		return codeSystem;
	}

	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}

	public String getCodeSystemName() {
		return codeSystemName;
	}

	public void setCodeSystemName(String codeSystemName) {
		this.codeSystemName = codeSystemName;
	}

	public int getCodeSystemVersion() {
		return codeSystemVersion;
	}

	public void setCodeSystemVersion(int codeSystemVersion) {
		this.codeSystemVersion = codeSystemVersion;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}