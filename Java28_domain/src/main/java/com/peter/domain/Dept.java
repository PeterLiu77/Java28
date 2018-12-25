package com.peter.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Dept implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String deptName;
	private Dept parent;
	private Integer state;
	private Set<User> users = new HashSet<>();
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String depName) {
		this.deptName = depName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
