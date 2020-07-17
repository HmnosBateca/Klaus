package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Color;



public interface IColorService {
	
	public List<Color> listarColores();
	public Page<Color> listarColoresPaginado(Pageable paginador);
	public Color obtenerColorPorID(Long id);
	public Color guardarColor(Color color);
	public void eliminarColor(Long id);
}
