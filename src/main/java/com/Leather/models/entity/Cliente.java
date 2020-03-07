package com.Leather.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Cliente implements Serializable{//seralizable atribustos de la tabla

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//POSTGRESQL
    private Long id;
	
	private String documento;
	private String nombres;
	private String apellidos;
	private String numero_contacto;
	private String direccion;
	private String correo;
	private String codigo_postal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNumero_contacto() {
		return numero_contacto;
	}
	public void setNumero_contacto(String numero_contacto) {
		this.numero_contacto = numero_contacto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
