package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;

/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:24 PM
 */
public class Address {

	private Long id;
	private String address1;
	private String address2;
	private String zipcode;
	private String city;
	private String state;
	private String county;
	private String country;

	public Address(){

	}

	public void finalize() throws Throwable {

	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}