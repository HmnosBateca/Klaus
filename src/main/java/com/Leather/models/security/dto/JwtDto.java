package com.Leather.models.security.dto;

// se va a utilizar cuando se haga un login esta clase devuelve Jwt del controlador
public class JwtDto {
	
	private String token;
	  
	public JwtDto() {}

	  
	public JwtDto(String token) {
		this.token = token;
	}
	// Getters y Setters
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

