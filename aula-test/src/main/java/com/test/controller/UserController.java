package com.test.controller;

import org.springframework.web.bind.annotation.RestController;

import com.test.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserController {

	private static UserService userService;
}
