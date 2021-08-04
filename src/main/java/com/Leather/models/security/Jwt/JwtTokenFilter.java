package com.Leather.models.security.Jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Leather.models.security.service.UserDetailsServiceImpl;

// Mas importante
// Se ejecuta por cada peticion, comprueba que sea valido el token usando provider, si es valido permite el acceso
// al recurso de lo contrario lanza la excepcion

public class JwtTokenFilter extends OncePerRequestFilter{
	
	// para saber el metodo que esta dando error en caso de que no funcione
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    
	// Va a decir si esta autenticado y si el token es valido o no
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String token = getToken(req);// tenemos el token
			if(token != null && jwtProvider.validateToken(token)) { //si existe el token
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);// con el token se obtiene el usuario
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);// Se crea un userDetails y se autentica una ves se atiende ese usuario
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			    SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch(Exception e) {
			logger.error("Fallo el metodo dofilter"+ e.getMessage());
		}
		// Si todo va bien
		filterChain.doFilter(req, res);
	}
	
	private String getToken (HttpServletRequest request) {
		String header = request.getHeader("Authorization");// cabecera
		if(header !=null && header.startsWith("Bearer")) 
			return header.replace("Bearer", "");
		return null;
	}
}
