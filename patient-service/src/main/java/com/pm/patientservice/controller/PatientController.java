package com.pm.patientservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.DTO.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.inter_face.IPatientService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private IPatientService patientService;
	
	
	
	@GetMapping
	public ResponseEntity<List<PatientDTO>> patients(){
		List<PatientDTO> patientDTOs = patientService.getPatients();
		return ResponseEntity.ok().body(patientDTOs);
	}
	
	@PostMapping
	public ResponseEntity<String> patients(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
		boolean isCreated = patientService.createPatient(patientRequestDTO);
		if(isCreated) {
			return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Created failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> patients(@PathVariable("id") UUID id, @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
		boolean isUpdated = patientService.updatePatient(id,patientRequestDTO);
		if(isUpdated) {
			return new ResponseEntity<>("Updated successfully", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Updated failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> patients(@PathVariable("id") UUID id){
		boolean isDeleted = patientService.deletePatient(id);
		if(isDeleted) {
			return new ResponseEntity<>("Deleted successfully", HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>("Deleted failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
