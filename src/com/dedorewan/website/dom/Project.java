package com.dedorewan.website.dom;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Project {
	public static enum STATUS {
		NEW("New"), PLA("Planned"), INP("In progress"), FIN("Finished");
		String m_name;

		STATUS(String name) {
			m_name = name;
		}
		public String getValue(){
			return m_name;
		}
	}

	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	@Column(nullable = false)
	private Long group_id;
	@NotEmpty
	@Column(nullable = false)
	private Integer project_number;
	@NotEmpty
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Column(nullable = false)
	private String customer;
	@NotEmpty
	@Column(nullable = false)
	private STATUS status;
	@NotEmpty
	@Column(nullable = false)
	private Date start_date;
	@NotEmpty
	@Column
	private Date end_date;
	@NotEmpty
	@Column(nullable = false)
	private Integer version;

	public Project(Long id, Long group_id, Integer project_number, String name, String customer,
			STATUS status, Date start_date, Date end_date, Integer version) {
		this.id = id;
		this.group_id = group_id;
		this.project_number = project_number;
		this.name = name;
		this.customer = customer;
		this.version = version;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public Long getId() {
		return id;
	}

	public Long getGroupId() {
		return group_id;
	}

	public Integer getProjectNumber() {
		return project_number;
	}

	public String getName() {
		return name;
	}

	public String getCustomer() {
		return customer;
	}

	public STATUS getStatus() {
		return status;
	}

	public Integer getVersion() {
		return version;
	}

	public Date getStartDate() {
		return start_date;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGroupId(Long group_id) {
		this.group_id = group_id;
	}

	public void setProjectNumber(Integer project_number) {
		this.project_number = project_number;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public void setStartDate(Date date) {
		this.start_date = date;
	}

	public void setEndDate(Date date) {
		this.end_date = date;
	}
	public Boolean isNew(){
		if(this.status == STATUS.NEW){
			return true;
		}
		return false;
	}
}
