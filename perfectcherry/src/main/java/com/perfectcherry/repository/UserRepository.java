package com.perfectcherry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perfectcherry.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("FROM User where username = ?1 ") 
	public Optional<User> findUserByName(String loginName);

}
