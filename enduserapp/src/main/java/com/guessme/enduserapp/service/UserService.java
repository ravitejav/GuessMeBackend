package com.guessme.enduserapp.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.guessme.enduserapp.models.User;
import com.guessme.enduserapp.utils.JWTUtils;
import com.guessme.enduserapp.utils.MyUserDetails;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JWTUtils jwtUtils;

	public User getUserDetails(String username) {
		User user  = restTemplate.getForObject("http://user-handler/user/getUserByUsername/" + username, User.class);
		return user;
	}

	public User saveUser(User user) {
		User createdUser  = restTemplate.postForObject("http://user-handler/user/createUser", user, User.class);
		return createdUser;
	}
	
	public HashMap<String, Object> authorizeUser(User user) {
		HashMap<String, String> authStatus  = restTemplate.postForObject("http://user-handler/user/auth", user, HashMap.class);
		HashMap<String, Object> response = new HashMap<String, Object>();
		
		if(authStatus.get("authStatus") != null) {
			final UserDetails userDetails = this.loadUserByUsername(user.getUsername());
			final String jwt = jwtUtils.generateToken(userDetails);
			
			response.put("userDetails", user);
			response.put("jwt", jwt);
			response.put("status", "success");
		}

		return response;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new MyUserDetails(this.getUserDetails(username));
	}
	
	
	
}
