package com.me.eventhub.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.eventhub.exception.EventsPostedException;
import com.me.eventhub.pojo.User;
import com.me.eventhub.pojo.EventRegistration;
import com.me.eventhub.pojo.RegistrationDetails;

@Component
public class AdminDAO extends DAO  {
	
         //Method to create a new event
		public RegistrationDetails postEvent(RegistrationDetails event) {
			try {
				begin();
				getSession().save(event);
				commit();
				close();
				return event;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				return null;
			}
		}
		
		//Method to list all events
		public List<RegistrationDetails> listEventPosts(User user) throws EventsPostedException{
			try {
				begin();
				int userid = user.getUserid();
				Query query = getSession().createQuery("from RegistrationDetails where user = :userid");
				query.setInteger("userid", userid);
				List<RegistrationDetails> jobPost = query.list();
				commit();
				close();
				return jobPost;
			}catch(HibernateException e) {
				rollback();
				throw new EventsPostedException("No Events found!", e);
			}
		}
		
		//Method to delete an event
		public boolean delete(long eventid) throws EventsPostedException{
			try {
				begin();
				Query query = getSession().createQuery("delete from RegistrationDetails where id = :eventid");
				query.setLong("eventid", eventid);
				query.executeUpdate();
				commit();
				close();
				return true;
			}catch(HibernateException e) {
				rollback();
				throw new EventsPostedException("Error deleting the Event", e);
			}
		}
		
//		Method to update an event
		public RegistrationDetails updateEventPost(long eventid) throws EventsPostedException{
			try {
				begin();
				Query query = getSession().createQuery("from RegistrationDetails where id = :eventid");
				query.setLong("eventid", eventid);
				RegistrationDetails eventdetails = (RegistrationDetails) query.uniqueResult();
				commit();
				close();
				return eventdetails;
			}catch(HibernateException e) {
				rollback();
				throw new EventsPostedException("Could not update the event at the DAO level", e);
			}
		}
		
//		Method to create a new event
		public RegistrationDetails onClickUpdateEventPost(RegistrationDetails eventdetails, long newEventid) throws EventsPostedException{
			try {
				begin();
				String title = eventdetails.getEventTitle();
				String host = eventdetails.getHostName();
				String eventType = eventdetails.getEventType();
				String venue = eventdetails.getVenue();
				String link = eventdetails.getLink();
				String description = eventdetails.getDescription();
				
				Query query = getSession().createQuery("update RegistrationDetails set eventTitle = :title, hostName = :host, eventType = :eventType, venue = :venue, link = :link, description = :description where id = :newEventid");
				query.setString("title", title);
				query.setString("host", host);
				query.setString("eventType", eventType);
				query.setString("venue", venue);
				query.setString("link", link);
				query.setString("description", description);
				query.setLong("newEventid", newEventid);
				query.executeUpdate();
				commit();
				close();
				return eventdetails;
			}catch(HibernateException e) {
				rollback();
				throw new EventsPostedException("Error occured while updating the event at the DAO Level", e);
			}
		}
		
		//Method to get list of participants for an event
		
		public List<EventRegistration> listOfParticipants(long eventid) throws EventsPostedException{
			try {
				begin();
				Query query = getSession().createQuery("from EventRegistration where eventdetails= :eventid");
				query.setLong("eventid", eventid);
				List<EventRegistration> event = query.list();
				commit();
				close();
				return event;
			}catch (HibernateException e) {
	            rollback();
	            throw new EventsPostedException("Could not find a participant", e);
	        }
		}
		
		//Method to get Participant's information
		public List<User> getUserInfo(int userid) throws EventsPostedException{
			try {
				begin();
				System.out.println("PART2: INSIDE getUserInfo DAO METHOD ");
				Query query = getSession().createQuery("from User where userid= :userid");
				query.setInteger("userid", userid);
				List<User> userinfo = query.list();
				commit();
				close();
				return userinfo;
			}catch (HibernateException e) {
	            rollback();
	            throw new EventsPostedException("Could not find candidate info", e);
	        }
		}
	

}
