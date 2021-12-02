package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	//@Transactional(readOnly = true)
	//observe que ele retorna para o controlador um UserDTO e não a entidade User
	public UserDTO findById(Long id) {
		//o findById retorna um Optional, por o o get após o findById para ele retornar de fato a entidade User
		User entity = repository.findById(id).get();
		//essa linha abaixo faz a conversão de User para UserDto, utilizando o construtor do UserDto
		UserDTO dto = new UserDTO(entity);
		return dto;
	}
}
