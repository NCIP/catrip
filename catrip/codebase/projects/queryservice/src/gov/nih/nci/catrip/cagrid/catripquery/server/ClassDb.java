package gov.nih.nci.catrip.cagrid.catripquery.server;
import java.lang.String;
import java.util.HashSet;
import java.util.Random;

/**
 * @version 1.0
 * @created 14-Dec-2006 1:22:52 PM
 */
public class ClassDb {
 
	private int id;
	private String name;
	public java.util.Collection<AttributeDb> attributeCollection;
	private Random generator = new Random();

	public java.util.Collection getAttributeCollection() {
		if (attributeCollection == null)
			attributeCollection = new HashSet<AttributeDb>();
		return attributeCollection;
	}

	public void addAttribute(AttributeDb anAttribute){
		if (anAttribute != null && anAttribute.getName() != null){
			if (attributeCollection == null)
				attributeCollection = new HashSet<AttributeDb>();
			attributeCollection.add(anAttribute);
		}
	}

	public void addAttribute(String anAttributeName){
		if (anAttributeName != null && anAttributeName != ""){
			if (attributeCollection == null){
				attributeCollection = new HashSet<AttributeDb>();
				AttributeDb attribute = new AttributeDb();
				attribute.setName(anAttributeName);
				attribute.setId(generator.nextInt());
				attributeCollection.add(attribute);
			}
		}
	}

	public void setAttributeCollection(java.util.Collection<AttributeDb> attributeCollection) {
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

