package com.Leather.models.security.Jwt;


import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.Leather.models.security.dto.JwtDto;
import com.Leather.models.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// genera un token, metodo de validacion, ve que este bien formado, y que no este expirado etc
@Component
public class JwtProvider {
	
	// para saber el metodo que esta dando error en caso de que no funcione
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
		
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration; 
	
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();// Implementar los metodos
		List<String> roles= usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList());//lista de cadena autorities en cadenas
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()) // Se construye el token
	    .claim("roles", roles)
		.setIssuedAt(new Date())// Fecha de creacion de el momento
		.setExpiration(new Date( new Date().getTime() + expiration)) // una fecha, desde el momento actual
		.signWith(SignatureAlgorithm.HS512, secret.getBytes())// tipo de algoritmo, se firma con secret
		.compact();// con eso se tiene el token generado
	}
	
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();// firmamos con secret, se pasa el token, 
		                                                                                         // obtenemos el body, subject es el nombre de usuario
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);// Se valida
			return true;
		}
		// Varias excepciones
		catch(MalformedJwtException e) { // Malformado
			logger.error("token mal formado");
		}
		catch(UnsupportedJwtException e) { // no soportado
			logger.error("token no soportado");
		}
		catch(ExpiredJwtException e) { // ha Expirado
			logger.error("token ha expirado");
		}
		catch(IllegalArgumentException e) { // vacio
			logger.error("token vacío");
		}
		catch(SignatureException e) { // Fallo con la firma
			logger.error("fallo con la firma");
		}
		return false;
	}
	
	// metodo de refreshToken
	public String refreshToken(JwtDto jwtDto) throws ParseException {
		JWT jwt = JWTParser.parse(jwtDto.getToken());// parseamos la cadena
		JWTClaimsSet claims = jwt.getJWTClaimsSet();
		String nombreUsuario = claims.getSubject();// obtener los valores
		List<String> roles = (List<String>) claims.getClaim("roles");
		return Jwts.builder()
				.setSubject(nombreUsuario) // Se construye el token
			    .claim("roles", roles)
				.setIssuedAt(new Date())// Fecha de creacion de el momento
				.setExpiration(new Date( new Date().getTime() + expiration)) // una fecha, desde el momento actual
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())// tipo de algoritmo, se firma con secret
				.compact();// con eso se tiene el token generado
	} 
}
