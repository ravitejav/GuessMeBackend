package com.guessme.userservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guessme.userservice.models.User;
import com.guessme.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/getUserByUsername/{username}", method = RequestMethod.GET)
	public User getUserByUsername(@PathVariable(name = "username") String username) {
		return userService.getUserByUserName(username);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public HashMap<String, Boolean>  authUser(@RequestBody User user) {
		return userService.authUser(user);
	}

}
