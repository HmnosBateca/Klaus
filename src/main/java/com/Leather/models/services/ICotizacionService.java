package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Cotizacion;

public interface ICotizacionService {
	public List<Cotizacion>ListarCotizacion();// Obtener
	public Page<Cotizacion>ListarCotizacionPaginado(Pageable paginador);// Obtener por paginas
	public Cotizacion ObtenerCotizacionPorId(Long id);// Obtener por id
	public Cotizacion GuardarCotizacion(Cotizacion cotizacion);// Guarda
	public void EliminarCotizacion(Long id); //Eliminar
}

