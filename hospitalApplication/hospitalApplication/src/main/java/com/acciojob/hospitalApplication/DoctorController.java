package com.acciojob.hospitalApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctor(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for(Doctor doctor : doctors){
            DoctorDto doctorDto = Doctor.DoctorToDoctorDto(doctor);
            doctorDtos.add(doctorDto);
        }
        return new ResponseEntity<>(doctorDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Doctor.DoctorToDoctorDto(doctor), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = Doctor.DoctorDtoToDoctor(doctorDto);
        Doctor savedDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(Doctor.DoctorToDoctorDto(savedDoctor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{id}/addPatient")
    public ResponseEntity<PatientDto> addPatient(@PathVariable int id, @RequestBody PatientDto patientDto) {
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PatientDto savedPatientDto = doctorService.addPatient(doctor, patientDto);
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/deletePatient/{patientId}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable int id, @PathVariable int patientId) {
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        boolean isPatientDeleted = doctorService.deletePatient(doctor, patientId);
        return new ResponseEntity<>(isPatientDeleted, HttpStatus.OK);
    }

}
