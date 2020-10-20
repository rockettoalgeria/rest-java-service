package com.example.springbootpostgresqlrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Contact {

	private long id;
	private String name;
	
	public Contact() { }
	
	public Contact(String name) { this.name = name; }
	
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String firstName) {
		this.name = firstName;
	}

}
