package com.me.eventhub.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name = "REGISTRATION_DETAILS")

public class RegistrationDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique=true, nullable = false)
	private long id;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User user;
	
	@Column(name = "EVENT_TITLE")
	private String eventTitle;
	
	@Column(name = "HOST_NAME")
	private String hostName;
	
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "VENUE")
	private String venue;
	
	@Column(name = "LINK")
	private String link;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date postedOn;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<EventRegistration> registration = new ArrayList<EventRegistration>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String jobTitle) {
		this.eventTitle = jobTitle;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String companyName) {
		this.hostName = companyName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String jobType) {
		this.eventType = jobType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public List<EventRegistration> getRegistration() {
		return registration;
	}
	public void setRegistration(List<EventRegistration> registration) {
		this.registration = registration;
	}

}
