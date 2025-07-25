//package com.pm.patientservice.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import patient_event.PatientEvent;
//
//@Service
//public class KafkaConsumer {
//    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(topics = "patient", groupId = "patient-consumer-group")
//    public void consume(byte[] message) throws Exception {
//        PatientEvent event = PatientEvent.parseFrom(message);
//        log.info("Received PatientEvent: patientId={}, name={}, email={}, event_type={}",
//                event.getPatientId(), event.getName(), event.getEmail(), event.getEventType());
//    }
//}