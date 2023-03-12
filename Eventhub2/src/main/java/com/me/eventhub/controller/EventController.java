package com.me.eventhub.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.me.eventhub.dao.AdminDAO;
import com.me.eventhub.dao.EventDAO;
import com.me.eventhub.exception.EventsPostedException;
import com.me.eventhub.exception.EventException;
import com.me.eventhub.pojo.User;

import com.me.eventhub.pojo.EventRegistration;
import com.me.eventhub.pojo.RegistrationDetails;


@Controller
@RequestMapping(value = "/event/")
public class EventController {
     
	    //controller to view all the events
		@RequestMapping(value = "viewallevents.htm", method = RequestMethod.GET)
		public ModelAndView viewAllJobs(HttpServletRequest request, EventDAO eventDao) throws Exception {
			User user = (User) request.getSession().getAttribute("name");
			try {
				request.getSession().setAttribute("name", user);
				List<RegistrationDetails> eventDetails = eventDao.listAllEvents();
				request.getSession().setAttribute("jobID", eventDetails);
				return new ModelAndView("RegEventList", "allEvents", eventDetails);
			} catch (EventException e) {
				return new ModelAndView("student-home", "errorMessage",
					"Please logout and login again!");
			}
		}
		
	    // controller to show upload pages
		@RequestMapping(value = "showUploadPage.htm", method = RequestMethod.GET)
		public ModelAndView showApplicationPage(HttpServletRequest request, AdminDAO adminDao, EventDAO eventDao)
				throws EventsPostedException, EventException {
			HttpSession session = (HttpSession) request.getSession();
			String eventID = request.getParameter("eventID");
			long id = Long.parseLong(eventID);
			RegistrationDetails eventdetails = adminDao.updateEventPost(id);
			session.setAttribute("eventID", eventdetails);
			User user = (User) request.getSession().getAttribute("name");
			request.getSession().setAttribute("name", user);
			return new ModelAndView("EventRegistration");
		}
		
	// controller to register for an event
		@RequestMapping(value = "apply.htm", method = RequestMethod.POST)
		public ModelAndView handleFileUpload(HttpServletRequest request, EventDAO eventDao,
				@RequestParam("fileUpload") CommonsMultipartFile[] fileUpload) throws Exception {
			boolean token = true;
			List<RegistrationDetails> jd = new ArrayList<RegistrationDetails>();
			RegistrationDetails eventdetails = (RegistrationDetails) request.getSession().getAttribute("eventID");
			User user = (User) request.getSession().getAttribute("name");
			try {
				token = eventDao.userExists(user, eventdetails);
				if (token == true) {
					return new ModelAndView("student-home", "errorMessage",
							"Your registration is done . If you want to edit/update , please withdraw your registration  and apply again");
				} else {
					
					request.getSession().setAttribute("name", user);
					request.getSession().setAttribute("EventID", eventdetails);
					if (fileUpload != null && fileUpload.length > 0) {
						for (CommonsMultipartFile aFile : fileUpload) {
							EventRegistration uploadFile = new EventRegistration();
							uploadFile.setFileName(aFile.getOriginalFilename());
							uploadFile.setData(aFile.getBytes());
							uploadFile.setUser(user);
							uploadFile.setDetails(eventdetails);
							eventDao.saveFiles(uploadFile);
						}
					}
				}
				jd.add(eventdetails);
				request.getSession().setAttribute("eventList", jd);
				return new ModelAndView("student-home", "successMessage",
						"Your registration has been completed successfully");
			} catch (EventException e) {
				return new ModelAndView("student-home", "errorMessage",
						"Please logout and login again");

			}
		}
	// controller to view my events
		@RequestMapping(value = "viewMyEvents.htm", method = RequestMethod.GET)
		public ModelAndView listAllApplications(HttpServletRequest request, EventDAO eventDao) throws Exception {
			try {
				User user = (User) request.getSession().getAttribute("name");
				List<List<RegistrationDetails>> events = new ArrayList<List<RegistrationDetails>>();
				List<RegistrationDetails> eventDetails = new ArrayList<RegistrationDetails>();
				List<EventRegistration> applicationsDetails = eventDao.listRegEvent(user);
				
				if(applicationsDetails.size() > 0) {
					List<Long> id = new ArrayList<Long>();
					ModelAndView modelAndView = new ModelAndView("EventList");
					for (EventRegistration ja : applicationsDetails) {
						long eventId = ja.getDetails().getId();
						if(!id.contains(eventId)) {
							eventDetails = eventDao.allEventDetails(eventId) ;
							events.add(eventDetails);
							id.add(eventId);
						}
					}
					modelAndView.addObject("events", events);
					return modelAndView;
				}
				return new ModelAndView("student-home", "successMessage",
						"You have not been registered for any event . Please view the list to register!");

			} catch (Exception e) {
				return new ModelAndView("student-home", "errorMessage",
						"Please try to logout and login again");

			}
		}
	// controller to cancel my registration
		@RequestMapping(value = "deleteMyApplication.htm", method = RequestMethod.GET)
		public ModelAndView deleteJobPost(HttpServletRequest request, EventDAO eventDao) throws EventException {
			HttpSession session = (HttpSession) request.getSession();
			String eventId = request.getParameter("eventID");
			try {
				if (eventId.equals(null)) {
					System.out.println("id not present");
				} else {
					session.setAttribute("eventId", eventId);
					long id = Long.parseLong(eventId);
					eventDao.delete(id);
				}
				return new ModelAndView("student-home", "successMessage",
						"Your application has been withdrawn successfully");
			} catch (EventException e) {
				return new ModelAndView("student-home", "errorMessage",
						"Sorry not able to delete the Job. Please try again");
			}

		}
	
}
