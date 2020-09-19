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
@Table(name="transportadoras")
public class EmpresaTransportadora  implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	@OneToMany(mappedBy = "empresaTransportadora", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value= {"listaEnvioCiudad", "listaEstadoEnvioCiudad"})
	private List<EnvioCiudad> listaEnvioCiudad;
	
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

	public List<EnvioCiudad> getListaEnvioCiudad() {
		return listaEnvioCiudad;
	}
	public void setListaEnvioCiudad(List<EnvioCiudad> listaEnvioCiudad) {
		this.listaEnvioCiudad = listaEnvioCiudad;
	}

	public void addEnvioCiudad(EnvioCiudad envioCiudad) {
		this.listaEnvioCiudad.add(envioCiudad);
	}

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
