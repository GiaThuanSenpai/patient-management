package com.pm.patientservice.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.kafka.KafkaProducer;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.inter_face.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private BillingServiceGrpcClient billingServiceGrpcClient;
	
	@Override
	public List<PatientDTO> getPatients() {
		List<Patient> patients = patientRepository.findAll();
		
		List<PatientDTO> patientDTOs = patients.stream()
				.map(PatientMapper::toDTO).toList();
		return patientDTOs;
	}

	@Override
	public boolean createPatient(PatientRequestDTO patientRequestDTO) {
		if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email "+ "already exists " + patientRequestDTO.getEmail());
		}
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
		
		billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),
				newPatient.getName(), newPatient.getEmail());
		
		kafkaProducer.sendEvent(newPatient);
		
		PatientDTO patientDTO = PatientMapper.toDTO(newPatient);
		return patientDTO !=  null;
	}

	@Override
	public boolean updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: "+ id));
		
		if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
			throw new EmailAlreadyExistsException("A patient with this email "+ "already exists " + patientRequestDTO.getEmail());
		}
		
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

		PatientMapper.toDTO(patientRepository.save(patient));
		
		return true;
	}

	@Override
	public boolean deletePatient(UUID id) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: "+ id));
		patientRepository.delete(patient);
		return true;
	}
	

}
