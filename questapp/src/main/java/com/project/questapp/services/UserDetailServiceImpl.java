package com.project.questapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;
import com.project.questapp.security.JwtUserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		return JwtUserDetails.create(user); //user to JwtUserDetails
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);	
	}
}
