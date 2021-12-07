package com.example.demo.dto;

import com.example.demo.entities.Categoria;

import lombok.Data;

@Data
public class CategoriaDTO {
	
	private String name;
	
	public CategoriaDTO(Categoria categoria) {
		name = categoria.getName();
	}
	
}
