package com.Leather.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Leather.models.entity.Ciudad;
import com.Leather.models.entity.Cliente;

public interface ICiudadDao extends CrudRepository<Ciudad, Long> {

	@Query("SELECT c FROM Ciudad c WHERE c.departamento.id = ?1")
	List<Ciudad> listarCiudadesPorDpto(Long id);
	
	@Query("SELECT c FROM Cliente c WHERE c.ciu.id = ?1")
	List<Cliente>listarClientesPorCiudades(Long id);
	
}
