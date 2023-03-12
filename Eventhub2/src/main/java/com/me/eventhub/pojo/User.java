package com.me.eventhub.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "USERTABLE")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", unique = true, nullable = false)
	private int userid;
	@Column(name = "FIRSTNAME")
	private String fname;
	
	@Column(name = "LASTNAME")
	private String lname;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "roleId")
	private Role role;
	
	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(mappedBy = "user",orphanRemoval = true)
	private List<RegistrationDetails> details = new ArrayList<RegistrationDetails>();
	
	@OneToMany(mappedBy = "user",orphanRemoval = true)
	private List<EventRegistration> registration = new ArrayList<EventRegistration>();
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getUserid() {
		return userid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<EventRegistration> getRegistration() {
		return registration;
	}
	public void setRegistration(List<EventRegistration> registration) {
		this.registration = registration;
	}

	
	
	public Role getRole() {
		return role;
	}
	public List<RegistrationDetails> getDetails() {
		return details;
	}
	public void setDetails(List<RegistrationDetails> details) {
		this.details = details;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
