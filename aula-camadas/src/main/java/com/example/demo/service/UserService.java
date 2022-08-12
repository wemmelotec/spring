package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
/*
 * Essa notação diz ao spring que essa classe será uma Bean e ele será o responsável pelo gerenciamento de suas instâncias
 * Como o Spring fica responsável pelo gerenciamento, eu não preciso de um new para criar um objeto dela, basta injetar 
 * com a notação @Autowired em algum local que eu necessite
 * 
 * Se a classe for externa ao meu projeto, por exemplo, vinda de uma depedência que eu precisei utilizar no projeto eu não posso anotar ela
 * com as notações usuais de minhas classes internas e torná-las numa bean simplesmente. Eu preciso criar uma classe com @Configuration e 
 * anotar o método que vai retornar o tipo da classe externa com o @Bean e a partir daí o Spring vai gerencialá.
 */
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
