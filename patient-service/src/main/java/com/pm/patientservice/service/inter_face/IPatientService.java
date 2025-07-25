package com.pm.patientservice.service.inter_face;

import java.util.List;
import java.util.UUID;

import com.pm.patientservice.DTO.PatientDTO;
import com.pm.patientservice.DTO.PatientRequestDTO;

public interface IPatientService {
	List<PatientDTO> getPatients();
	boolean createPatient(PatientRequestDTO patientRequestDTO);
	boolean updatePatient(UUID id, PatientRequestDTO patientRequestDTO);
	boolean deletePatient(UUID id);
}
