package com.test.service;

import org.springframework.stereotype.Service;

import com.test.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
}
