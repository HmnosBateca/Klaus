package com.Leather.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Leather.models.security.entity.Rol;
import com.Leather.models.security.enums.RolNombre;
import com.Leather.models.security.service.RolService;

@Component
public class CreateRoles implements CommandLineRunner {
	
	@Autowired
	RolService rolService;
	@Override
	public void run(String... args) throws Exception {
		rolService.save( new Rol(RolNombre.ROLE_ADMIN));
		rolService.save( new Rol(RolNombre.ROLE_USER));        
	} 	
}
	