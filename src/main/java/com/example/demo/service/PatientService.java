package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.model.Patient;
import com.example.demo.model.DTO.PatientDTO;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	public Patient save(PatientDTO patient) throws Exception {
		try {
			String strong_salt = BCrypt.gensalt(10);
			String cpfCript = BCrypt.hashpw(patient.getCpf(), strong_salt);
			
			Patient newPatient = new Patient(patient.getName(), cpfCript, patient.getUf(),
				patient.getBirth_date(), patient.getWeight(), patient.getHeight());
			patientRepository.save(newPatient);
			return newPatient;
		} catch (Exception e) {
			throw e;
		}

	}

	public List<Patient> findAll() {
		List<Patient> patients = this.patientRepository.findAll();

		return patients;
	}
	
	public String delete(Long id) {
		try {
			this.patientRepository.deleteById(id);
			return "Paciente deletado com sucesso";
		} catch (Exception e) {
			throw e;
		}

	}
}