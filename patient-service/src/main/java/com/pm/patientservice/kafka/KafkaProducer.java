package com.pm.patientservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.models.Patient;

import patient_event.PatientEvent;
@Service
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	
	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;
	
	public void sendEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try {
            kafkaTemplate.send("patient", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending PatientCreated event for patientId: {}. Error: {}", patient.getId(), e.getMessage(), e);
        }
    }
	
}
