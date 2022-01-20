package com.guessme.enduserapp.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.guessme.enduserapp.models.MyUserDetails;
import com.guessme.enduserapp.models.User;
import com.guessme.enduserapp.repository.UserRepository;
import com.guessme.enduserapp.utils.JWTUtils;
import com.guessme.enduserapp.utils.SHA512;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SHA512 sha512;

	@Autowired
	JWTUtils jwtUtils;

	public MyUserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return new MyUserDetails(user.get());
		}
		return null;
	}

	public User saveUser(User user) {
		user.setPassword(sha512.encryptThisString(user.getPassword()));
		userRepository.save(user);
		user.setPassword("");
		return user;
	}

	public HashMap<String, Object> authUser(User user) {
		Optional<User> authUser = userRepository.authUser(user.getUsername(),
				sha512.encryptThisString(user.getPassword()));
		HashMap<String, Object> status = new HashMap();
		if (authUser.isPresent()) {
			final MyUserDetails userDetails = this.loadUserByUsername(user.getUsername());
			final String jwt = jwtUtils.generateToken(userDetails);
			status.put("user", user);
			status.put("jwt", jwt);
		}
		return status;
	}
}
