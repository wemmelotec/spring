package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.entities.Categoria;
import com.example.demo.repositories.CategoriaRepository;



@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaDTO findByID(Long id) {
		Categoria entity = categoriaRepository.findById(id).get();
		
		CategoriaDTO dto = new CategoriaDTO(entity);
		
		return dto;
	}
	
	public List<CategoriaDTO> listAll(){
		List<Categoria> lisaCategorias =  categoriaRepository.findAll();
		List<CategoriaDTO> listaCategoriaDTOs = new ArrayList<>();
		for (Categoria categoria : lisaCategorias) {
			CategoriaDTO dto = new CategoriaDTO(categoria);
			listaCategoriaDTOs.add(dto);
		}
		return listaCategoriaDTOs;
	}
}	
