package com.example.springbootpostgresqlrest.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

	private long id;
	private String name;
	
	public Contact() { }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public void setName(String name) {
		this.name = name;
	}

}
