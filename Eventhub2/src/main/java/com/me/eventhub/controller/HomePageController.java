package com.me.eventhub.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.me.eventhub.dao.AdminDAO;
import com.me.eventhub.dao.EventDAO;
import com.me.eventhub.dao.RoleDAO;
import com.me.eventhub.dao.UserDAO;
import com.me.eventhub.exception.EventException;
import com.me.eventhub.exception.EventsPostedException;
import com.me.eventhub.pojo.RegistrationDetails;
import com.me.eventhub.pojo.Role;
import com.me.eventhub.pojo.User;


@Controller
@RequestMapping(value = "/")
@SessionAttributes("/logout.htm")
public class HomePageController {
	
     //	controller to register user
	
	@RequestMapping(value = "register.htm", method = RequestMethod.POST)
	public String register(HttpServletRequest request, UserDAO userDao, ModelMap map) {
		boolean token = true;
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String r= request.getParameter("role");
		User user = new User();
		Role role = new Role();
		token = userDao.registerCriteria(email, username);
		if(token == true) {
			map.addAttribute("errorMessage", "Username or emailId has already been registered");
			return "home";
		}else {
			role.setRole_name(r);
			user.setFname(firstname);
			user.setLname(lastname);
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			user.setRole(role);
			try {
				user = userDao.registerUser(user);
				map.addAttribute("successMessage", "Registration done Successfully!");
				return "home";
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
//	controller for logout
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	protected ModelAndView logout(HttpServletRequest request, ModelMap map, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView( "home");
	}
//	controller to register page
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public ModelAndView signup(HttpServletRequest request) throws Exception{
		return new ModelAndView( "register");
	}
	
// controller to pass the login request
	@RequestMapping(value = "/login.htm", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loginRequest(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, RoleDAO roleDao, ModelMap map,AdminDAO adminDao, EventDAO eventDao){
		HttpSession httpSession = request.getSession(true);
		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
		try {
			User user = userDao.get(username, password);
			if(user != null) {
				httpSession.setAttribute("name", user);				
					if(user.getRole().getRole_name().equalsIgnoreCase("admin")) {
						try {
							User users = (User) request.getSession().getAttribute("name");
							List<RegistrationDetails> eventPost = adminDao.listEventPosts(users);
							return new ModelAndView("PostedEventList", "eventPost", eventPost);
						} catch (EventsPostedException e) {
							return new ModelAndView("errors", "errorMessage", "Please refresh and login again");
						}
					}else if(user.getRole().getRole_name().equalsIgnoreCase("participant")) {
						User users = (User) request.getSession().getAttribute("name");
						try {
							request.getSession().setAttribute("name", user);
							List<RegistrationDetails> eventDetails = eventDao.listAllEvents();
							request.getSession().setAttribute("eventID", eventDetails);
							return new ModelAndView("student-home", "errorMessage",
									"Login Successfull!");
						} catch (EventException e) {
							return new ModelAndView("errors", "errorMessage", "Please refresh and login again");
						}
					}else {
						map.addAttribute("errorMessage", "Invalid username/password!");
						return new ModelAndView("home");
					}
			}else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("home");
			}
		}catch(Exception e) {			
			e.printStackTrace();
		}
		return null;
	}

}
