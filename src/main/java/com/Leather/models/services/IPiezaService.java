package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Pieza;



public interface IPiezaService {
	
	public List<Pieza> listarPiezas();
	public Page<Pieza> listarPiezasPaginado(Pageable paginador);
	public Pieza obtenerPiezaPorID(Long id);
	public Pieza guardarPieza(Pieza pieza);
	public void eliminarPieza(Long id);
}
