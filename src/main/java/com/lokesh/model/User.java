package com.lokesh.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lokesh
 * User POJO 
 * @JsonProperty is used to map the database column name to the POJO name which is used by objectMapper
 *
 */
public class User {
	
	@JsonProperty(value = "id")
	private int id;
	
	@JsonProperty(value = "firstname")
	private String firstName;
	
	@JsonProperty(value = "lastname")
	private String lastName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
