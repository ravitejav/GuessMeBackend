package com.guessme.userservice.queries;


public final class UserQueries {

	public static final String AUTH_USER = "SELECT u from User u where u.username=?1 and u.password =?2";
	
}
