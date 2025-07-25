package com.pm.authservice.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pm.authservice.DTO.LoginRequestDTO;
import com.pm.authservice.model.User;
import com.pm.authservice.repository.UserRepository;
import com.pm.authservice.service.interfaces.IUserService;
import com.pm.authservice.utils.JwtUtil;

import io.jsonwebtoken.JwtException;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private UserRepository userRepository;
	@Override
	public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
		Optional<String> token = userRepository.findByEmail(loginRequestDTO.getEmail())
				.filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
				.map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
		return token;
	}
	@Override
	public boolean validateToken(String token) {
		try {
			jwtUtil.validateToken(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

}
