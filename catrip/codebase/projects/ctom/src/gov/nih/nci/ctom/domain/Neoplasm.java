package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 25-Jun-2006 2:47:58 PM
 */
public class Neoplasm {

	public String cellTypeCode;
	public int id;

	public Neoplasm(){

	}

	public String getCellTypeCode() {
		return cellTypeCode;
	}

	public void setCellTypeCode(String cellTypeCode) {
		this.cellTypeCode = cellTypeCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}