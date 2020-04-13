package com.Leather.models.services;

import java.util.List;

import com.Leather.models.entity.Ciudad;
import com.Leather.models.entity.Cliente;

public interface ICiudadService {
	public List<Ciudad>listarCiudad();
	public Ciudad listarCiudadId(Long id);
	List<Ciudad> listarCiudadesPorDpto(Long id);
	List<Cliente> listarClientesPorCiudades(Long id);
}
