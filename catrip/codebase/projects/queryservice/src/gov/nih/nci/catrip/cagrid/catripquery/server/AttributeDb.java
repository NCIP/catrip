package gov.nih.nci.catrip.cagrid.catripquery.server;
import java.lang.Long;
import java.lang.String;

/**
 * @version 1.0
 * @created 14-Dec-2006 1:22:51 PM
 */
public class AttributeDb { 
 
	private int id;
	private String name;

	public AttributeDb(){

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}