package com.validation.controller;


import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validation.request.AnimePostRequestBody;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/anime")
@RequiredArgsConstructor
public class AnimeController {
	
	@PostMapping
	public void testValidation(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
		 System.out.println("o método executou");
		 return;
	}

}
