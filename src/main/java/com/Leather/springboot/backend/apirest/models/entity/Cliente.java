package com.Leather.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Cliente implements Serializable{//seralizable atribustos de la tabla

	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//POSTGRESQL
    private Long id_cliente;
	
	private int documento;
	private String nombres;
	private String apellidos;
	private int numero_contacto;
	private String direccion;
	private String correo;
	private int codigo_postal;
	
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
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
	public int getNumero_contacto() {
		return numero_contacto;
	}
	public void setNumero_contacto(int numero_contacto) {
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
	public int getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
