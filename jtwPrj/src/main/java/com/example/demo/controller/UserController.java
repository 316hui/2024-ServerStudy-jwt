package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.JwtService;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	JwtService jwtService;
	@Autowired
	RefreshTokenService refreshTokenService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userservice;
	
	
}
