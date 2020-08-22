package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.ProductoPieza;

public interface IProductoPiezaService {

	public List<ProductoPieza> listarProductoPieza();
	public Page<ProductoPieza> obtenerProductoPiezaPaginado(Pageable paginador);
	public ProductoPieza obtenerProductoPiezaPorID(Long id);
	public ProductoPieza agregarProductoPieza(ProductoPieza productoPieza);
	public void eliminarProductoPieza(Long id);	
	
}
