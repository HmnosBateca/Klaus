package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IProductoDao;
import com.Leather.models.entity.Producto;


@Service
public class ProductoServiceImpl implements IProductoService{

	
	@Autowired
	IProductoDao iProductoDao;
	
	
	@Transactional(readOnly = true)
	@Override
	public List<Producto> listarProductos() {
		return (List<Producto>) iProductoDao.findAll(Sort.by(Direction.ASC, "nombre"));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Producto> listarProductosPaginado(Pageable paginador) {
		return iProductoDao.findAll(paginador);
	}

	@Transactional(readOnly = true)
	@Override
	public Producto obtenerProductoPorID(Long idProducto) {
		return iProductoDao.findById(idProducto).orElse(null);
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return iProductoDao.save(producto);
	}

	@Override
	public void eliminarProducto(Long idProducto) {
		iProductoDao.deleteById(idProducto);
	}

}
