package com.Leather.models.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UsuarioPrincipal implements UserDetails { // interface userdetails
	
	private String nombre;
	private String nombreUsuario;
	private String correo;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;// autenticacion, autorizacion
	
	// Constructor de todos los campos
	public UsuarioPrincipal(String nombre, String nombreUsuario, String correo, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.password = password;
		this.authorities = authorities;
	}
	
	// Metodo estatico mas importante, asigna los provilegios a cada usuario, autorizacion si es admin o usuario
	public static UsuarioPrincipal build(Usuario usuario) {
		
		// Se convierten los roles en authorities apartir de nombre del rol
		// obtenemos una lista de grantedAuthority apartir de los roles
		// Estamos convirtiendo la clase rol en clase grantedAuthority
		// Se devuelve un usuarioPrincipal
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		
		return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getPassword(), authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return nombreUsuario;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
