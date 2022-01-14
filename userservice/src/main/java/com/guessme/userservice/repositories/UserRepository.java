package com.guessme.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guessme.userservice.models.User;
import com.guessme.userservice.queries.UserQueries;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	@Query(value = UserQueries.AUTH_USER)
	public Optional<User> authUser(String username, String Password);

}
