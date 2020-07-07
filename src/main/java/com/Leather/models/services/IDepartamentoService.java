package com.Leather.models.services;

import java.util.List;

import com.Leather.models.entity.Departamento;

public interface IDepartamentoService {
	
	public List<Departamento>listarDepartamentos();	
	public Departamento obtenerDepartamentoPorID(Long id);
	
}
