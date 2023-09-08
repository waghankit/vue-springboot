package com.vue.vuebackend;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vue.vuebackend.entity.User;
import com.vue.vuebackend.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> 
				new UsernameNotFoundException("User not found"));
		
		return new org.springframework.security.core.userdetails.User(
				existingUser.getEmail(), existingUser.getPassword(), new ArrayList<>());
	}
   
    public void createUser(UserDetails user) { 
        userRepository.save((User) user); 
    }

}