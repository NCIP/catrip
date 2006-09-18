package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:19 PM
 */
public class DistantSite {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DistantSite(){

	}

	public void finalize() throws Throwable {

	}

}