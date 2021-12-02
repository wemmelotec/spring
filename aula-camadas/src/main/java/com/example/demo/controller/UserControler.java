package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserControler {

	@Autowired
	private UserService service;
	//o próprio frameqork faz a conversão do UserDTO para um objeto JSON que é objeto que o frontend vai receber
	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
}
