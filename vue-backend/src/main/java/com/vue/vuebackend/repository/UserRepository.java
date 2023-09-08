package com.vue.vuebackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vue.vuebackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}