package com.example.demo.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service_impl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("/createUser")
	public ModelAndView user(User user) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("CreateUser.html");
//		User user1 = new User();
//		
//		user1.setUserFirstName("user1");
//		user1.setUserLastname("hi");
//		user1.setEmail("mymail");
//		userServiceImpl.saveUserInDb(user1);

		return mv;
	}

	@PostMapping("/createUser")
	public String saveUser(@ModelAttribute("user") User user) {

		userServiceImpl.saveUserInDb(user);
		return "redirect:/createUser";
	}

	@PostMapping("/finduser/{userName}")
	public String findUser(@PathParam(value = "userName") String userName) {

		String isAvailable = "1";
		if (userServiceImpl.getUserByName(userName) == null) {
			isAvailable = "0";
		}
		return isAvailable;
	}

	
	@PostMapping("/finduserByName/{userName}")
	@ResponseBody
	public User findUserByName(@PathParam(value = "userName") String userName) {

	User user = new User();
		if (userServiceImpl.getUserByName(userName) != null) {
			user=userServiceImpl.getUserByName(userName);
		}
		return user;
	}
	
//	@GetMapping("/viewAllUser")
//	public ModelAndView user() {
//
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("userList", userServiceImpl.getAllUsersFromDb());
//
//		return mv;
//	}

}
