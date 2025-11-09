package com.acciojob.hospitalApplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "doctor_name")
    String name;

    @Column(name = "doctor_expertise")
    String expertise;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Patient> patients;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "doctor_patient",
//            joinColumns = @JoinColumn(name = "doctor_id"),
//            inverseJoinColumns = @JoinColumn(name = "patient_id")
//    )
//    private List<Patient> patients;


    public static Doctor DoctorDtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setExpertise(doctorDto.getExpertise());
        return doctor;
    }

    public static DoctorDto DoctorToDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setName(doctor.getName());
        doctorDto.setExpertise(doctor.getExpertise());
        return doctorDto;
    }

}