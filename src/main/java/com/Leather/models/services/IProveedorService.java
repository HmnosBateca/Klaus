package com.Leather.models.services;

import java.util.List;

import com.Leather.models.entity.Proveedor;




public interface IProveedorService {
	
	public List<Proveedor> listarProveedores();
	public Proveedor obtenerProveedorPorId(Long id);
	public Proveedor guardarProveedor(Proveedor proveedor);
	public void eliminarProveedor(Long id);

}
