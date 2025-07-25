package com.pm.patientservice.DTO;


import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

	private String id;

	private String name;
	
	private String email;
	
	private String address;

	private String dateOfBirth;
	
}
