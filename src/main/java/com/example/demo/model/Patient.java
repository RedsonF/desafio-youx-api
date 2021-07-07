package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	@NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome pode ser vazio")
	private String name;

	@Column(name = "cpf", unique = true)
	@NotNull(message = "CPF não pode ser nulo")
    @NotEmpty(message = "CPF pode ser vazio")
	@JsonIgnore
	private String cpf;
	
	@Column(name = "uf")
	@NotNull(message = "UF não pode ser nulo")
    @NotEmpty(message = "UF pode ser vazio")
	private String uf;

	@Column(name = "birth_date")
	private String birth_date;
	
	@Column(name = "weight")
	private int weight;
	
	@Column(name = "height")
	private int height;

	public Patient() {
	}

	public Patient(String name, String cpf, String uf, String birth_date, int weight, int height) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.uf = uf;
		this.birth_date = birth_date;
		this.weight = weight;
		this.height = height;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getbirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}