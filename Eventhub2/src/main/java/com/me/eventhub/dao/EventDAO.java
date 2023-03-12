package com.me.eventhub.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.me.eventhub.exception.EventsPostedException;
import com.me.eventhub.exception.EventException;
import com.me.eventhub.pojo.User;



import com.me.eventhub.pojo.EventRegistration;
import com.me.eventhub.pojo.RegistrationDetails;




@Component
public class EventDAO extends DAO  {
     
	// Method to get list of all events
		public List<RegistrationDetails> listAllEvents() throws EventException {
			try {
				begin();
				Query query = getSession().createQuery("from RegistrationDetails");
				List<RegistrationDetails> allEventList = query.list();
				commit();
				close();
				return allEventList;
			} catch (HibernateException e) {
				rollback();
				throw new EventException("Could not find an Event", e);
			}
		}
		
		// Method for uploading Ids for Event Registration
		public void saveFiles(EventRegistration uploadFile) throws EventException {
				try {
					begin();
					getSession().save(uploadFile);
					close();
				}catch(HibernateException e) {
					rollback();
					e.printStackTrace();
					throw new EventException("Error occured while uploading files at DAO level",e);
				}
			}
		
		
		//Method for fetching registered ids from User id
			public EventRegistration getEventId(User user) throws EventsPostedException{
				try {
					begin();
					Query query = getSession().createQuery("from EventRegistration where user = '" + user + "' ");
					EventRegistration application = (EventRegistration) query.uniqueResult();
					commit();
					close();
					return application;
				}catch(HibernateException e) {
					rollback();
					throw new EventsPostedException("Error occured while updating the event at DAO level", e);
				}
			}
			
			// Criteria method for checking if the Participant has already registered for this event
			public boolean userExists(User user, RegistrationDetails eventdetails)throws EventException{
				try {
					boolean result = true;
					begin();
					Criteria criteria = getSession().createCriteria(RegistrationDetails.class);
					criteria.add(Restrictions.eq("eventdetails",eventdetails));
					List <EventRegistration> results = criteria.list();
					commit();
					close();
					if(results.size() >= 2) {
							result = true;
						}
						else {
							result = false;
						}
					return result;
					
				}catch(HibernateException e) {
					rollback();
					e.printStackTrace();
					throw new EventException("Error occured while fetching users at DAO level",e);
				}
			}
		
		// Method for listing all registrations by a given participant
		public List<EventRegistration> listRegEvent(User user)throws EventException{
			try {
				begin();
				int userid = user.getUserid();
				Query query = getSession().createQuery("from EventRegistration where user = :userid");
				query.setInteger("userid", userid);
				List<EventRegistration> eventApplications = query.list();
				commit();
				close();
				return eventApplications;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new EventException("Error occured while fetching Job list at DAO level",e);
			}
		}
		
		//Method for listing all the event details based on the event Id
		public List<RegistrationDetails> allEventDetails(long eventId) throws EventException{
			try {
				begin();
				Query query = getSession().createQuery("from RegistrationDetails where id = :eventId");
				query.setLong("eventId", eventId);
				List<RegistrationDetails> eventDetails = query.list();
				commit();
				close();
				return eventDetails;
			}catch(HibernateException e) {
				rollback();
				e.printStackTrace();
				throw new EventException("Error occured while fetching job list at DAO level",e);
			}
		}
		
		//Method for deleting a Registration 
			public boolean delete(long eventid) throws EventException{
				try {
					begin();
					Query query = getSession().createQuery("delete from EventRegistration where eventdetails = :eventid");
					query.setLong("eventid", eventid);
					query.executeUpdate();
					commit();
					close();
					return true;
				}catch(HibernateException e) {
					rollback();
					throw new EventException("Error occured while withdrawal of job", e);
				}
			}
	
}
