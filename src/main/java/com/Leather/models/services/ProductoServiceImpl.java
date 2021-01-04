package com.Leather.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.common.CommonServiceImpl;

import com.Leather.models.dao.IProductoDao;
import com.Leather.models.entity.Producto;


@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, IProductoDao> implements IProductoService{
	
	@Autowired
	IProductoDao iProductoDao;
	
	// Listar Paginado los productos que se encuentran en Bodega Inventario
	@Override
	@Transactional(readOnly = true)
	public Page<Producto> ListarProductosEnBodegaInventario(Pageable paginador) {
		return iProductoDao.ListarProductosEnBodegaInventario(paginador);
	}
}
