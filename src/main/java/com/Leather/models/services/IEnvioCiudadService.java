package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.EnvioCiudad;

public interface IEnvioCiudadService {
	
	public List<EnvioCiudad> findAll(); // Listar
	public EnvioCiudad findById(Long id); // Listar por id
	public EnvioCiudad save(EnvioCiudad envioCiudad); // Guardar
	public void delete (Long id);
    Page<EnvioCiudad> findAll(Pageable pageable);

}
