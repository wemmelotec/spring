package com.aula.crud.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.aula.crud.domain.User;
import com.aula.crud.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	public List<User> listar() {
		return userRepository.findAll();
	}

	public User findByIdOrThrowBadRequestException(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
	}

	public User salvar(User user) {
		return userRepository.save(user);
	}

	public void deletar(Long id) {
		userRepository.delete(findByIdOrThrowBadRequestException(id));
		
	}

	public void atualizar(User user) {
		User usuarioSalvoNoBancoUser = findByIdOrThrowBadRequestException(user.getId());
		User userAtual = User.builder()
				.id(usuarioSalvoNoBancoUser.getId())
				.name(user.getName())
				.password(user.getPassword())
				.build();
		salvar(userAtual);
	}
	
	
}
