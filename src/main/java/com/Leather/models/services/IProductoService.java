package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Producto;




public interface IProductoService {
	public List<Producto> listarProductos();
	public Page<Producto> listarProductosPaginado(Pageable paginador);
	public Producto obtenerProductoPorID(Long idProducto);
	public Producto guardarProducto(Producto producto);
	public void eliminarProducto(Long idProducto);
}
