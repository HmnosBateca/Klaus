package com.Leather.common;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICommonService<E> {
	
	public List<E> listarElementos();
	public Page<E> listarElementosPaginado(Pageable paginador);
	public E obtenerElementoPorID(Long id);
	public E guardarElemento(E entity);
	public void eliminarElemento(Long id);
}
