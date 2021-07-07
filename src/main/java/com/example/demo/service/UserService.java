package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.repository.UserRepository;

import domain.Roles;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User save(UserDTO user) throws Exception {
		try {
			String salt = BCrypt.gensalt(12);
			String cpfCript = BCrypt.hashpw(user.getCpf(), salt);
			String passwordCript = BCrypt.hashpw(user.getPassword(), salt);

			User newUser = new User(user.getName(), cpfCript, passwordCript,
					Roles.ROLE_NURSE);
			
			userRepository.save(newUser);
			return newUser;
		} catch (Exception e) {
			throw e;
		}

	}

	public List<User> findAll() {
		List<User> users = this.userRepository.findByRole("NURSE");

		return users;
	}

	public String delete(Long id) {
		try {
			this.userRepository.deleteById(id);
			return "Usuário deletado com sucesso";
		} catch (Exception e) {
			throw e;
		}

	}
}