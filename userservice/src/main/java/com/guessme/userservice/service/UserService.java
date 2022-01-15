package com.guessme.userservice.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guessme.userservice.models.User;
import com.guessme.userservice.repositories.UserRepository;
import com.guessme.userservice.utils.SHA512;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SHA512 sha512;
	
	public User getUserByUserName(String username) {
		Optional<User> user  = userRepository.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	public User saveUser(User user) {
		user.setPassword(sha512.encryptThisString(user.getPassword()));
		userRepository.save(user);
		user.setPassword("");
		return user;
	}
	
	public HashMap<String, Boolean> authUser(User user) {
		Optional<User> authUser = userRepository.authUser(user.getUsername(), sha512.encryptThisString(user.getPassword()));
		HashMap<String, Boolean> status = new HashMap();
		status.put("authStatus", authUser.isPresent());
		return status;
	}
}
