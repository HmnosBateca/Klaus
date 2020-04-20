package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Proveedor;




public interface IProveedorService {
	
	public List<Proveedor> listarProveedores();
	public Page<Proveedor> listarProveedoresPaginado(Pageable paginador); // con paginación
	public Proveedor obtenerProveedorPorId(Long id);
	public Proveedor guardarProveedor(Proveedor proveedor);
	public void eliminarProveedor(Long id);

}
