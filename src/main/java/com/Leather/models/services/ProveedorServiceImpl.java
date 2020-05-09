package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Leather.models.dao.IProveedorDao;
import com.Leather.models.entity.Proveedor;



@Service
public class ProveedorServiceImpl implements IProveedorService{

	
	@Autowired
	IProveedorDao iProveedorDao;
	
	/* 
	 * El método findAll lista todos los Proveedores y dentro de sus parámetros 
	 * especifica que se ordene de manera ascendente por el nombre
	 */
	@Override
	public List<Proveedor> listarProveedores() {
		return (List<Proveedor>) iProveedorDao.findAll(Sort.by(Sort.Direction.ASC,"nombres"));
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



	@Override
	public Page<Proveedor> listarProveedoresPaginado(Pageable paginador) {
		return iProveedorDao.findAll(paginador);
	}

	
	
	
}
