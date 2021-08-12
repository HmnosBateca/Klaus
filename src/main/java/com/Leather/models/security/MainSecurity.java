package com.Leather.models.security;



import com.Leather.models.security.Jwt.JwtEntryPoint;
import com.Leather.models.security.Jwt.JwtTokenFilter;
import com.Leather.models.security.service.UserDetailsServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)// a que metodos accede el administrador
public class MainSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;// Devuelve el mensaje de no autorizado
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	//Para la contraseña
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//  Se generan los methodos los 4 primeros
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());//obtiene el usuario y se le cifra la contraseña
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	
	// Parte mas importante donde se configura todo
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.cors()
		.and().csrf().disable() // se deshabilitan las cookies
		.authorizeRequests()// autorizar request
		.antMatchers("/auth/**").permitAll() //Se le permite a todo el mundo el acceso a la URL "/auth/**", ya que ahí está el login como el registrar usuario 
		.anyRequest().authenticated() // Cualquier otro tipo de request debe estar autenticado
		.and() // Para el control de sesiones
		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint) // Para el manejo de errores se va a usar el error 401 no autorizado a través del método "authenticationEntryPoint"
		.and()// Sesion
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Se establecen políticas de la sesión. Se va a dejar sin estado (STATELESS) debido a que se usa TOKEN 
		
		//se añade el jwtTokenFilter antes de cada petición. Allí se va a comprobar el token y va a enviar el usuaro al contexto de autenticación
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
}
