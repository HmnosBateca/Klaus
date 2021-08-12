package com.Leather.models.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/*
 * Esta clase se utiliza para la creación de nuevos usuarios*/
public class NuevoUsuario {
	@NotBlank//No puede ser nulo , no puede ser una cadena vacia, ni espacios en blanco
	private String nombre;
	@NotBlank
	private String nombreUsuario;
	@Email
	private String correo;
	@NotBlank
	private String password;
    private Set<String> roles = new HashSet<>();//Por que se van a utilisar Json para mejorar el trafico. COn una API Rest es mejor usar cadenas (String)
    
    // Se Generan los Getter y Setter
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
