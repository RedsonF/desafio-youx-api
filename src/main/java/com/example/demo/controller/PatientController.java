package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Patient;
import com.example.demo.model.DTO.PatientDTO;
import com.example.demo.security.SecurityHandler;
import com.example.demo.service.PatientService;


@RestController
@RequestMapping({ "/patient" })
@CrossOrigin(value = "*")
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@Autowired
	SecurityHandler securityHandler;

	@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody PatientDTO patientDTO, HttpServletRequest http) {
		try {
			if (securityHandler.authorizeAll(http.getHeader("authorization")) != true) {
				return new ResponseEntity<String>("Usuário não autorizado", HttpStatus.FORBIDDEN);
			}
			
			Patient patient = patientService.save(patientDTO);
			return new ResponseEntity<Patient>(patient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> findAll(HttpServletRequest http) {
		try {
			if (securityHandler.authorizeDoctor(http.getHeader("authorization")) != true) {
				return new ResponseEntity<String>("Usuário não autorizado", HttpStatus.FORBIDDEN);
			}
			List<Patient> pacientes = patientService.findAll();
			return new ResponseEntity<List<Patient>>(pacientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest http) {
		try {
			if (securityHandler.authorizeDoctor(http.getHeader("authorization")) != true) {
				return new ResponseEntity<String>("Usuário não autorizado", HttpStatus.FORBIDDEN);
			}
			String retorno = patientService.delete(id);
			return new ResponseEntity<String>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}