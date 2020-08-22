package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IProductoPiezaDao;
import com.Leather.models.entity.ProductoPieza;





@Service
public class ProductoPiezaServiceImpl implements IProductoPiezaService{

	
	@Autowired
	IProductoPiezaDao iProductoPiezaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<ProductoPieza> listarProductoPieza() {
		return (List<ProductoPieza>)iProductoPiezaDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ProductoPieza> obtenerProductoPiezaPaginado(Pageable paginador) {
		return iProductoPiezaDao.findAll(paginador);
	}

	@Transactional(readOnly = true)
	@Override
	public ProductoPieza obtenerProductoPiezaPorID(Long id) {
		return iProductoPiezaDao.findById(id).orElse(null);
	}

	@Override
	public ProductoPieza agregarProductoPieza(ProductoPieza productoPieza) {
		return iProductoPiezaDao.save(productoPieza);
	}

	@Override
	public void eliminarProductoPieza(Long id) {
		iProductoPiezaDao.deleteById(id);
	}
	
	

}
