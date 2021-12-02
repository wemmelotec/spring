package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Client;

@SpringBootApplication                         //me permiter executar algo no console quando rodar o aplicativo
public class AulaLombokApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AulaLombokApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Client c1 = new Client(1L, "Bob");
		Client c2 = new Client(2L, "Nina");
		
		c1.setName("Bob Brown");
		
		System.out.println("ID = "+c2.getId());
		
		if(c1.equals(c2)) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}

}
