package com.Leather.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="ciudades")
public class Ciudad implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//IDENTITY auto incrementar
	private Long id;
	
	private String nombre;
	//private int departamento_id;
	
	//fetch optener los datos de la operacion lazy perezosa, realiza la consulta cuando se le llama,
	//muchas ciudades un departamento
	@ManyToOne(fetch=FetchType.LAZY) 
	@JsonIgnore//no se haga un bucle infinito 
	private Departamento departamento;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
/*
	
	public int getDepartamento_id1() {
		return departamento_id1;
	}

	public void setDepartamento_id1(int departamento_id1) {
		this.departamento_id1 = departamento_id1;
	}*/

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
