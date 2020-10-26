package com.perfectcherry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfectcherry.entity.User;

@Repository
public interface UserDetailRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String name);

}