package com.acciojob.hospitalApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientDto> patientDtos = new ArrayList<>();
        for(Patient patient : patients) {
            PatientDto patientDto = Patient.PatientToPatientDto(patient);
            patientDtos.add(patientDto);
        }
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable int id) {
        Patient patient = patientService.getPatientById(id);
        if(patient == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Patient.PatientToPatientDto(patient), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        Patient patient = Patient.PatientDtoToPatient(patientDto);
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(Patient.PatientToPatientDto(savedPatient), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatientById(@PathVariable int id) {
        try {
            patientService.deletePatientById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
