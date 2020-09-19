package com.Leather.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tipo_envios")
public class TipoEnvio implements Serializable {//en json
	
	/**
	 * 
	 */
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Postgrest
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	/*@OneToMany(mappedBy = "tipoEnvio", fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"listaEnvioCiudad", "envioCiudad", "tipoEnvio","handler", "hibernateLazyInitializer"})
	public List<EnvioCiudad> listaEnvioCiudad;*/
	
	
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/*
	public List<EnvioCiudad> getEnvioCiudad() {
		return listaEnvioCiudad;
	}
	public void setEnvioCiudad(List<EnvioCiudad> listaEnvioCiudad) {
		this.listaEnvioCiudad = listaEnvioCiudad;
	}
	
	public void addEnvioCiudad(EnvioCiudad envioCiudad) {
		this.listaEnvioCiudad.add(envioCiudad);
	}*/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 1L;
}
