/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.cagrid.catripquery.server;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

/**
 * @version 1.0
 * @created 14-Dec-2006 1:22:50 PM
 */
public class QueryDb {

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
	private String dcql; 
	private Collection<ClassDb> classCollection;
	private Set<DcqlDb> dcqlCollection;
	private Random generator = new Random();

	public QueryDb(){ 

	}
	
	public void addClass(ClassDb aClass){
		if (aClass != null && aClass.getName() != null && aClass.getName().trim() != ""){
			if (classCollection == null)
				classCollection = new HashSet<ClassDb>();
			classCollection.add(aClass);
		}
	}
	public void addClass(String anAttributeName){
		if (anAttributeName != null && anAttributeName != ""){
			if (classCollection == null){
				classCollection = new HashSet<ClassDb>();
				ClassDb aClass = new ClassDb();
				aClass.setName(anAttributeName);
				aClass.setId(generator.nextInt());
				classCollection.add(aClass);
			}
		}
	}
	public Collection<ClassDb> getClassCollection() {
		return classCollection;
	}

	public void setClassCollection(Collection<ClassDb> classCollection) {
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
	public Vector<DcqlDb> bubbleSort(Vector<DcqlDb> sorted)
	  {
	    for (int i = sorted.size()-2; i >= 0; i--) 
	    {
	      for (int j = 0; j <= i; j++) 
	      {
	        int s1 = sorted.elementAt(j).getSequence();
	        int s2 = sorted.elementAt(j+1).getSequence();
	        if (s1 > s2) 
	        {
	          DcqlDb tmp = sorted.elementAt(j);
	          sorted.setElementAt(sorted.elementAt(j+1), j);
	          sorted.setElementAt(tmp, j+1);
	       	}
	      }
	    }
	    return sorted;
	  }

	public String getDcql() {
		// concatinate the dcql strings
		Vector<DcqlDb> v = new Vector<DcqlDb>(dcqlCollection.size());
		dcql = "";
		for (Iterator iter = dcqlCollection.iterator(); iter.hasNext();) {
			DcqlDb dcqlObject = (DcqlDb) iter.next();
			v.add(dcqlObject);
			}
		v = bubbleSort(v);
		for (int i = 0; i < v.size(); i++) {
			DcqlDb dcqlObject = (DcqlDb) v.elementAt(i);
			if (dcqlObject != null && dcqlObject.getDcql() != null){
				dcql += dcqlObject.getDcql();
			}
			
		}
		return dcql;
	}

	public void setDcql(String dcqlQuery) {
		this.dcql = dcqlQuery;
	}
	
	public void copy(QueryDb q){
		this.creationDate = q.getCreationDate();
		this.dateUpdated = q.getDateUpdated();
		this.dcql = q.getDcql();
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

	public Set<DcqlDb> getDcqlCollection() {
		return dcqlCollection;
	}

	public void setDcqlCollection(Set<DcqlDb> dcqlCollection) {
		this.dcqlCollection = dcqlCollection;
	}
}