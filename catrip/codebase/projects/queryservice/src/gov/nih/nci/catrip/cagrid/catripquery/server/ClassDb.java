package gov.nih.nci.catrip.cagrid.catripquery.server;
import java.lang.String;
import java.util.HashSet;

/**
 * @version 1.0
 * @created 14-Dec-2006 1:22:52 PM
 */
public class ClassDb {
 
	private int id;
	private String name;
	public java.util.Collection<ClassDb> attributeCollection;

	public java.util.Collection getAttributeCollection() {
		if (attributeCollection == null)
			attributeCollection = new HashSet<ClassDb>();
		return attributeCollection;
	}

	public void setAttributeCollection(java.util.Collection attributeCollection) {
		this.attributeCollection = attributeCollection;
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

	public ClassDb(){

	}

	

}

