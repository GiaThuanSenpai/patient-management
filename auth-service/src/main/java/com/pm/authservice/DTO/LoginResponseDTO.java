package com.pm.authservice.DTO;


public class LoginResponseDTO {
	private final String token;

	public LoginResponseDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
