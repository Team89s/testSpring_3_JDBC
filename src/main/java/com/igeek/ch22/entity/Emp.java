package com.igeek.ch22.entity;

public class Emp {

	private Integer id;
	private String name;
	private String email;
	private Integer deptId;
	
	public Emp() {
		super();
	}
	public Emp(String name, String email, Integer deptId) {
		super();
		this.name = name;
		this.email = email;
		this.deptId = deptId;
	}
	public Emp(Integer id, String name, String email, Integer deptId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.deptId = deptId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", email=" + email + ", deptId=" + deptId + "]";
	}
}
