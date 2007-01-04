package gov.nih.nci.catrip.cagrid.catripquery;

import java.lang.String;
import java.util.*;

/**
 * @version 1.0
 * @created 14-Dec-2006 1:22:50 PM
 */
public class CatripQuery {

	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String description;
	private String name;
	private Date creationDate;
	private Date dateUpdated;
	private String source;
	private String version;
	private String instance;
	public String dcqlQuery;
	public Collection<DCQLClass> classCollection;

	public CatripQuery(){ 

	}
	
	public void addClass(DCQLClass aClass){
		if (aClass != null && aClass.getName() != null){
			if (classCollection == null)
				classCollection = new HashSet<DCQLClass>();
			classCollection.add(aClass);
		}
	}
	public Collection<DCQLClass> getClassCollection() {
		return classCollection;
	}

	public void setClassCollection(Collection<DCQLClass> classCollection) {
		this.classCollection = classCollection;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDcqlQuery() {
		return dcqlQuery;
	}

	public void setDcqlQuery(String dcqlQuery) {
		this.dcqlQuery = dcqlQuery;
	}
	
	public void copy(CatripQuery q){
		this.creationDate = q.getCreationDate();
		this.dateUpdated = q.getDateUpdated();
		this.dcqlQuery = q.getDcqlQuery();
		this.description = q.getDescription();
		this.firstName = q.getFirstName();
		this.lastName = q.getLastName();
		this.id = q.getId();
		this.name = q.getName();
		this.source = q.getSource();
		this.instance = q.getInstance();
		this.userName = q.getUserName();
		this.version = q.getVersion();
		
	}
}