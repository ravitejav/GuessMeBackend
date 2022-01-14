package com.guessme.enduserapp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guessme.enduserapp.models.User;
import com.guessme.enduserapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUserDetails/{username}", method = RequestMethod.GET)
	public User getUserDetails(@PathVariable String username) {
		return userService.getUserDetails(username);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public HashMap<String, Object>  authUser(@RequestBody User user) {
		return userService.authorizeUser(user);
	}
	

}
