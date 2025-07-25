package com.pm.authservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be a valid email address")
	private String email;
	
	@NotBlank(message = "Passowrd is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
}
