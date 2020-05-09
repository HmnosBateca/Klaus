package com.Leather.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name="departamentos")
public class Departamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	//Un departamento muchas ciudades
	//CascadeType.ALL:Todas las operaciones delete y persistencia se realizan en cascada
	//mappedBy = "departamento":el nombre de atributo ciudad, hacemos que sea bidireccional, crea la llave foranea ciudad id
	@OneToMany(mappedBy = "departamento",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore//Para que se haga una bucle infinito
	private List<Ciudad>ciudades;
	
	///Getters y Setters
	
	public Departamento() {//creamos constructor
		ciudades=new ArrayList<Ciudad>();
	}


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

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	//Agregar ciudad por ciudad en la clase departamento
	public void addCiudad(Ciudad ciudad) {
		ciudades.add(ciudad);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}



