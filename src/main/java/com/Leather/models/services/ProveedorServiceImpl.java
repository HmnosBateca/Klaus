package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Leather.models.dao.IProveedorDao;
import com.Leather.models.entity.Proveedor;



@Service
public class ProveedorServiceImpl implements IProveedorService{

	
	@Autowired
	IProveedorDao iProveedorDao;
	
	
	@Override
	public List<Proveedor> listarProveedores() {
		return (List<Proveedor>) iProveedorDao.findAll();
	}
	
	

	@Override
	public Proveedor obtenerProveedorPorId(Long id) {
		return iProveedorDao.findById(id).orElse(null);
	}
	
	

	@Override
	public Proveedor guardarProveedor(Proveedor proveedor) {
		return iProveedorDao.save(proveedor);
	}
	
	

	@Override
	public void eliminarProveedor(Long id) {
		iProveedorDao.deleteById(id);
	}

	
	
	
}
