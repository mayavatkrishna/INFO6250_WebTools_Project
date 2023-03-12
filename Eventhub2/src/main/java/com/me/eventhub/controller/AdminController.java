package com.me.eventhub.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.eventhub.dao.AdminDAO;
import com.me.eventhub.exception.EventsPostedException;
import com.me.eventhub.pojo.User;

import com.me.eventhub.pojo.EventRegistration;
import com.me.eventhub.pojo.RegistrationDetails;



@Controller
@RequestMapping(value = "/admin/")
public class AdminController {
	 
    //	Controller to post a new event
	@RequestMapping(value = "postevent.htm", method = RequestMethod.GET)
	public ModelAndView getEventPostForm(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute("name");
		request.getSession().setAttribute("name", user);
		modelAndView.setViewName("EventPosting");
		return modelAndView;
	}
     // controller to add new posted event in database
	@RequestMapping(value = "posteventsuccess.htm", method = RequestMethod.POST)
	public ModelAndView postEvent(HttpServletRequest request, AdminDAO adminDao, ModelMap map) {
		String title = request.getParameter("EVENT_TITLE");
		String host = request.getParameter("HOST_NAME");
		String eventType = request.getParameter("EVENT_TYPE");
		String venue = request.getParameter("VENUE");
		String link = request.getParameter("LINK");
		String jobDesc = request.getParameter("DESCRIPTION");
		Date postedOn = new Date();
		
		User user = (User) request.getSession().getAttribute("name");
		RegistrationDetails eventDetails = new RegistrationDetails();
		eventDetails.setEventTitle(title);
		eventDetails.setHostName(host);
		eventDetails.setEventType(eventType);
		eventDetails.setVenue(venue);
		eventDetails.setLink(link);
		eventDetails.setDescription(jobDesc);
		eventDetails.setPostedOn(postedOn);
		eventDetails.setUser(user);
		
		try {
			eventDetails = adminDao.postEvent(eventDetails);
			if (eventDetails != null) {
				map.addAttribute("successMessage", "Event has been posted successfully!");
				return new ModelAndView("HomeAdmin", "eventPost", eventDetails);
			} else {
				map.addAttribute("errorMessage", "Error occured in while posting your event!");
				return new ModelAndView("EventPosting");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
    //Controller to to view all the posted events
	@RequestMapping(value = "myeventposts.htm", method = RequestMethod.GET)
	public ModelAndView listMyPostedEvents(HttpServletRequest request, AdminDAO adminDao) {
		try {
			User user = (User) request.getSession().getAttribute("name");
			List<RegistrationDetails> eventPost = adminDao.listEventPosts(user);
			return new ModelAndView("PostedEventList", "eventPost", eventPost);
		} catch (EventsPostedException e) {
			return new ModelAndView("HomeAdmin", "errorMessage", "Error occured while displaying your posted events");
		}
	}
	
// Controller to delete the posted events
	@RequestMapping(value = "deleteMyEventposts.htm", method = RequestMethod.GET)
	public ModelAndView deleteEventPost(HttpServletRequest request, AdminDAO adminDao) {
		HttpSession session = (HttpSession) request.getSession();
		String eventId = request.getParameter("eventID");
		try {
			if (eventId.equals(null)) {
				System.out.println("No Id found to delete the event post");
			} else {
				session.setAttribute("eventId", eventId);
				long id = Long.parseLong(eventId);
				adminDao.delete(id);
			}
			return new ModelAndView("EventDeleteMsg");
		} catch (EventsPostedException e) {
			return new ModelAndView("HomeAdmin", "errorMessage", "Error occured while deleting the event post");
		}

	}
// controller to get the event details for form
	
	@RequestMapping(value = "updateEventDetails.htm", method = RequestMethod.GET)
	public ModelAndView updateJobPost(HttpServletRequest request, AdminDAO adminDao) {
		HttpSession session =  request.getSession();
		String id = request.getParameter("eventID");
	    User user = (User) request.getSession().getAttribute("name");
		session.setAttribute("id", id);
		session.setAttribute("name", user);
		long eventid = Long.parseLong(id);
		try {
			RegistrationDetails eventDetails = adminDao.updateEventPost(eventid);
			request.setAttribute("eventDetails", eventDetails);
			return new ModelAndView("UpdateEvent", "eventDetails", eventDetails);
		}catch(EventsPostedException e) {
			return new ModelAndView("HomeAdmin", "errorMessage", "Error occured while updating the event post");
		}
	}
     //	controller to update the event details
	@RequestMapping(value = "updateEventDetails.htm", method = RequestMethod.POST)
	public @ResponseBody ModelAndView onUpdateClick(HttpServletRequest request, AdminDAO adminDao, ModelMap map, @ModelAttribute("eventdetails") RegistrationDetails eventdetails) {
		
		HttpSession session =  request.getSession();
		String id = (String)session.getAttribute("id");
		User user = (User) request.getSession().getAttribute("name");
		String title = request.getParameter("EVENT_TITLE");
		String host = request.getParameter("HOST_NAME");
		String eventType = request.getParameter("EVENT_TYPE");
		String venue = request.getParameter("VENUE");
		String link = request.getParameter("LINK");
		String jobDesc = request.getParameter("DESCRIPTION");
		Date postedOn = new Date();
	
		eventdetails.setEventTitle(title);
		eventdetails.setHostName(host);
		eventdetails.setEventType(eventType);
		eventdetails.setVenue(venue);
		eventdetails.setLink(link);
		eventdetails.setDescription(jobDesc);
		eventdetails.setPostedOn(postedOn);
		try {
			long newEventId = Long.parseLong(id);
			RegistrationDetails details = adminDao.onClickUpdateEventPost(eventdetails, newEventId);
			request.getSession().setAttribute("updatedEventDetails", details);
			session.setAttribute("name", user);
			map.addAttribute("successMessage", "Event details has been updated successfully!");
			return new ModelAndView("HomeAdmin", "eventDetails", details);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
// controller to view participants
	@RequestMapping(value = "viewCandidatesApplied", method = RequestMethod.GET)
	public ModelAndView listCandidatesApplied(HttpServletRequest request,AdminDAO adminDao) {
			User user = (User) request.getSession().getAttribute("name");
			HttpSession session = request.getSession();
			String id = request.getParameter("eventID");
			session.setAttribute("id", id);
			session.setAttribute("name", user);
			long eventid = Long.parseLong(id);
			try {
				List<EventRegistration> listApplication = adminDao.listOfParticipants(eventid);
				if(listApplication.size()>0) {
					for(EventRegistration ja : listApplication) {
						int userid = ja.getUser().getUserid();
						List<User> appUserInfo = adminDao.getUserInfo(userid);
						ModelAndView modelAndView = new ModelAndView("RegisteredUser");
						modelAndView.addObject("listapp",listApplication);
						modelAndView.addObject("listuser",appUserInfo);
						return modelAndView;
					}
				} else {
					return new ModelAndView("HomeAdmin", "errorMessage", "No Candidates have applied still");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

}
