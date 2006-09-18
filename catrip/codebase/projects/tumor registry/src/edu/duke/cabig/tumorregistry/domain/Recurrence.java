package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.util.Date;
import java.lang.String;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:19 PM
 */
public class Recurrence {

	private Long id;
	private Date date;
	private String type;
	private java.util.Set distantSiteCollection;

	public Recurrence(){

	}

	public void finalize() throws Throwable {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public java.util.Set getDistantSiteCollection() {
		return distantSiteCollection;
	}

	public void setDistantSiteCollection(java.util.Set distantSiteCollection) {
		this.distantSiteCollection = distantSiteCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}