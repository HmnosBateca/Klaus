package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.EstadoEnvioCiudad;

public interface IEstadoEnvioCiudadService {// Metodos
	public List<EstadoEnvioCiudad>ListarEstadoEnvioCiudad();// Obtener
	public Page<EstadoEnvioCiudad>findAll(Pageable pageable);// Obtener por paginas
	public EstadoEnvioCiudad findById(Long id);// Obtener por id
	public EstadoEnvioCiudad save (EstadoEnvioCiudad estadoEnvioCiudad);// Guarda
	public void delete(Long id); //Eliminar
}
