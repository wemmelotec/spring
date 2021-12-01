package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
	    List<User> result = repository.findAll();
	    return ResponseEntity.ok(result);
	}
	//nesse método ao invés de passar um @RequestParam ?, eu passei uma URI (identificação de recurso local) /Long
	@GetMapping(value="/{codigo}")
	public ResponseEntity<Optional<User>> getById(@PathVariable Long codigo) {
	    Optional<User> user = repository.findById(codigo);
	    
	    	return ResponseEntity.ok(user);
	    
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<User>> findAll(Pageable pageable) {
	    Page<User> result = repository.findAll(pageable);
	    return ResponseEntity.ok(result);
	}
	
	//esse método foi criado customizado no repository através da JPQL, ele também recebe os parâmetros minSalary e maxSalary
	@GetMapping(value = "/search-salary")
	public ResponseEntity<Page<User>> searchBySalary(@RequestParam(defaultValue = "0") Double minSalary, @RequestParam(defaultValue = "1000000000000") Double maxSalary, Pageable pageable) {
	    Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
	    return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/name")
	public ResponseEntity<Page<User>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
	    Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
	    return ResponseEntity.ok(result);
	}
	

}
