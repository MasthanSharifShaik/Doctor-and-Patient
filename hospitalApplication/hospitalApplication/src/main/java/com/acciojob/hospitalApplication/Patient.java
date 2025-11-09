package com.acciojob.hospitalApplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue
    int id;

    @Column(name = "patient_name")
    String name;

    @ManyToMany(mappedBy ="patient",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Doctor> doctors;

    public static PatientDto PatientToPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setName(patient.getName());
        return patientDto;
    }

    public static Patient PatientDtoToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        return patient;
    }

    public static Patient patientDtoToPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        return patient;
    }
}
