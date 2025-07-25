package com.pm.authservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.authservice.DTO.LoginRequestDTO;
import com.pm.authservice.DTO.LoginResponseDTO;
import com.pm.authservice.service.interfaces.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private IUserService userService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> users(@RequestBody LoginRequestDTO loginRequestDTO){
		Optional<String> token = userService.authenticate(loginRequestDTO);
		
		if(token.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		String newToken = token.get();
		return ResponseEntity.ok(new LoginResponseDTO(newToken));
	}
	
	@GetMapping("/validate")
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String jwt){
		System.out.print(jwt);
		if(jwt == null || !jwt.startsWith("Bearer ")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		return userService.validateToken(jwt.substring(7))
				? ResponseEntity.ok().build()
				: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
