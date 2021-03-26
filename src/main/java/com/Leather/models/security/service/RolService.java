package com.Leather.models.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Leather.models.security.entity.Rol;
import com.Leather.models.security.enums.RolNombre;
import com.Leather.models.security.repository.RolRepository;


@Service
@Transactional
public class RolService {
	@Autowired
	RolRepository rolRepository;
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
