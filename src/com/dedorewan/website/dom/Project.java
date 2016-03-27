package com.dedorewan.website.dom;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Project {
	public static enum STATUS {
		NEW("New"), PLA("Planned"), INP("In progress"), FIN("Finished");
		String m_name;

		STATUS(String name) {
			m_name = name;
		}

		public String getValue() {
			return m_name;
		}
	}

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false)
	private Long groupId;
	@NotNull
	@Column(nullable = false)
	private Integer projectNumber;
	@NotEmpty
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Column(nullable = false)
	private String customer;
	@NotNull
	@Column(nullable = false)
	private STATUS status;
	@NotNull
	@Column(nullable = false)
	private Date startDate;
	@NotNull
	@Column
	private Date endDate;
	@Column(nullable = false)
	private Integer version;
	private String[] members;

	public Project() {

	}

	public Project(Long id, Long groupId, Integer project_number, String name,
			String customer, STATUS status, Date start_date, Date end_date,
			Integer version) {
		this.id = id;
		this.groupId = groupId;
		this.projectNumber = project_number;
		this.name = name;
		this.customer = customer;
		this.version = version;
		this.status = status;
		this.startDate = start_date;
		this.endDate = end_date;
	}

	public Long getId() {
		return id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Integer getProjectNumber() {
		return projectNumber;
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
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String[] getMembers() {
		return members;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setProjectNumber(Integer project_number) {
		this.projectNumber = project_number;
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
		this.startDate = date;
	}

	public void setEndDate(Date date) {
		this.endDate = date;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}

	public Boolean isNew() {
		if (this.status == STATUS.NEW) {
			return true;
		}
		return false;
	}

	public String membersToString() {
		StringBuilder builder = new StringBuilder("");
		if (this.members != null) {
			Boolean first = true;
			for (String s : this.members) {
				if (first) {
					builder.append(s);
					first = false;
				} else {
					builder.append("," + s);
				}
			}
		}
		return builder.toString();
	}

	public void editData(Project project) {
		this.groupId = project.getGroupId();
		this.customer = project.customer;
		this.members = project.getMembers();
		this.endDate = project.getEndDate();
		this.startDate = project.getStartDate();
		this.name = project.getName();
		this.projectNumber = project.getProjectNumber();
		this.version = project.getVersion();
		this.status = project.getStatus();
	}
}
