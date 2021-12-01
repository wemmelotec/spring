package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
//Jpa é uma interface generics, por isso eu tenho que informar o tipo e o ID dos objetos que utilizarei nela
public interface UserRepository extends JpaRepository<User, Long> {
	//1 obj apelido, alias
	//User é o nome da classe
	@Query("SELECT obj FROM User obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
	Page<User> searchSalary(Double minSalary, Double maxSalary, Pageable pageable);
	
	
	//esse  :name é o mesmo nome dado ao parâmetro recebido no controller
	@Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name, '%'))")
	Page<User> searchByName(String name, Pageable pageable);
	
	
	//modelos da jpa documentation https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	
	//vou atualizar o método 1 e 2 utilizando a documentação do jpa between e containeignorecase, ou seja, a própria jpa vai gerar uma JPQL pra mim
	Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);
	Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
