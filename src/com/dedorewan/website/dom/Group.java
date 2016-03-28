package com.dedorewan.website.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Group {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false)
	private Long groupLeaderId;
	@NotNull
	@Column(nullable = false)
	Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupLeaderId() {
		return groupLeaderId;
	}

	public void setGroupLeaderId(Long id) {
		this.groupLeaderId = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Group() {

	}

	public Group(Long id, Long groupLeaderId, Long version) {
		this.id = id;
		this.groupLeaderId = groupLeaderId;
		this.version = version;
	}
}
