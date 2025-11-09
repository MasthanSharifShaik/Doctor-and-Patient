package com.acciojob.hospitalApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    public PatientDto addPatient(Doctor doctor, PatientDto patientDto) {
        Patient patient = Patient.patientDtoToPatient(patientDto);
        patient.setDoctor(doctor);
        doctor.getPatients().add(patient);
        doctorRepository.save(doctor);
        return patientDto;
    }



    public boolean deletePatient(Doctor doctor, int patientId) {
        for(int i = 0; i < doctor.getPatients().size(); i++) {
            if(doctor.getPatients().get(i).getId() == patientId) {
                doctor.getPatients().remove(i);
                doctorRepository.save(doctor);
                return true;
            }
        }
        return false;
    }
}
