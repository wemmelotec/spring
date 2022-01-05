package com.aula.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aula.crud.domain.User;
import com.aula.crud.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> listarUsuarios(){
		return ResponseEntity.ok(userService.listar());
	}
	
	@GetMapping(path = "/{id}")
	public User listarUsuarioPorId(@PathVariable Long id) {
		return userService.findByIdOrThrowBadRequestException(id);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public User salvarUsuario(@RequestBody User user){
		return userService.salvar(user);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletarUsuario(@PathVariable Long id) {
		userService.deletar(id);
	}
	
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void atualizarUsuario(@RequestBody User user) {
		 userService.atualizar(user);
	}
}
