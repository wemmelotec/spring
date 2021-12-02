package com.example.demo.dto;

import com.example.demo.entities.User;

public class UserDTO {
	private Long id;
	private String name;
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name) {
		//this indica o id da classe e o id só indica o id do parâmetro
		this.id = id;
		this.name = name;
	}
	//esse construtor possibilitado receber como argumento a própria entidade
	//para facilitar a conversão de entidades para dto
	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
