package com.dedorewan.website.dom;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Column(nullable = false)
	private String visa;
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	@NotEmpty
	@Column(nullable = false)
	private String lastName;
	@NotNull
	@Column(nullable = false)
	Date birthDate;
	@NotNull
	@Column(nullable = false)
	Long version;
	private String fullName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date date) {
		this.birthDate = date;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Employee() {

	}

	public Employee(Long id, String visa, String firstName, String lastName,
			Date birthDate, Long version) {
		this.id = id;
		this.visa = visa;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.version = version;
		this.fullName = firstName + " " + lastName;

	}

	public String getFullName() {
		return this.fullName;
	}
}
