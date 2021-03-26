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
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());//obtiene el usuario y se le sifra la contraseña
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
		http.cors().and().csrf().disable()
		.authorizeRequests()// crear usuario y relizar el login
		.antMatchers("/auth/**").permitAll() //Se le permite a todo el mundo, estan tanto login como nuevo usuario 
		.anyRequest().authenticated() // El resto debe ser autenticado
		.and() // Para el control de sesiones
		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint) // aroja el error 401 no autorizado
		.and()// Sesion
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // sin estado no se envia kookes 
		
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
