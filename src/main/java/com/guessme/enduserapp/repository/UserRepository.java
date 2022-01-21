package com.guessme.enduserapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guessme.enduserapp.models.User;
import com.guessme.enduserapp.queries.UserQueries;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByUserId(Integer userId);
	
	@Query(value = UserQueries.AUTH_USER)
	public Optional<User> authUser(String username, String Password);

}
