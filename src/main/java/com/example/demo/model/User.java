package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Nome não pode ser nulo")
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "cpf", unique = true)
	@NotNull(message = "CPF não pode ser nulo")
	@JsonIgnore
	private String cpf;

	@Column(name = "password")
	@NotNull(message = "Senha não pode ser nulo")
	private String password;

	@Column(name = "role")
	@NotNull(message = "Role não pode ser nulo")
	private String role;

	public User() {
	}

	public User(String name, String cpf, String password, String role) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}