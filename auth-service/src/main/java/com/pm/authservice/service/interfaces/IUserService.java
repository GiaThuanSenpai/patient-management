package com.pm.authservice.service.interfaces;

import java.util.Optional;

import com.pm.authservice.DTO.LoginRequestDTO;
import com.pm.authservice.model.User;

public interface IUserService {
	public Optional<String> authenticate(LoginRequestDTO loginRequestDTO);
	public boolean validateToken(String token);
}
