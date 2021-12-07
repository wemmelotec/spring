package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/*@GetMapping
	public List<CategoriaDTO> listAll(){
		return categoriaService.listAll();
	}*/
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		List<CategoriaDTO> listCategoriaDTOs = categoriaService.listAll();
		return !listCategoriaDTOs.isEmpty()? ResponseEntity.ok(listCategoriaDTOs) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/{id}")
	public CategoriaDTO findById(@PathVariable Long id) {
		return categoriaService.findByID(id);
	}
}
