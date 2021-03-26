package com.Leather.models.security.Jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// Comprueba Si hay un token valido, si no devuelve una respuesta 401(no autorizado)
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

	// para saber el metodo que esta dando error en caso de que no funcione
	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException e) throws IOException, ServletException {
		logger.error("Fail en el metodo commence");// Para programar
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
	}
}
