package com.pm.patientservice.mapper;

import java.time.LocalDate;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.models.Patient;

public class PatientMapper {

	public static PatientDTO toDTO(Patient patient) {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setId(patient.getId().toString());
		patientDTO.setName(patient.getName());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
		return patientDTO;
	}
	
	public static Patient toModel(PatientRequestDTO patientRequestDTO) {
		Patient patient = new Patient();
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
		return patient;
	}
}
