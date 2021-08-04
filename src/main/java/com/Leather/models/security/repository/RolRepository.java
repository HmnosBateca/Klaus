package com.Leather.models.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Leather.models.security.entity.Rol;
import com.Leather.models.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	// Encontrar por Rol nombre
	Optional<Rol>findByRolNombre(RolNombre rolNombre);
}
