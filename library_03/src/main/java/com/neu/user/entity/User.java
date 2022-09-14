package com.neu.user.entity;

import com.neu.depart.entity.Depart;
import com.neu.status.entity.Status;

/**
 * @author 陈锦房
 */
public class User {
	private String id;
	private String username;
	private String password;
	private String realname;
	private Status status=new Status();
	private Depart depart=new Depart();

	public User(String id, String username, String password, String realname, Status status, Depart depart) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.status = status;
		this.depart = depart;
	}

	public User(String username, String password, String realname) {
		this.username = username;
		this.password = password;
		this.realname = realname;
	}

	public User(String username, String password, Status status) {
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public User(String username, String password, String realname, Status status, Depart depart) {
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.status = status;
		this.depart = depart;
	}

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Depart depart) {
		this.depart = depart;
	}
}
