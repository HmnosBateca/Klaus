package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IReferenciaProductoDao;
import com.Leather.models.entity.ReferenciaProducto;

@Service
public class ReferenciaProductoServiceImpl implements IReferenciaProductoService{

	@Autowired
	private IReferenciaProductoDao iReferenciaProductoDao ;
	
	// Listar Referencia Producto
	@Override
	@Transactional(readOnly = true)//lectura
	public List<ReferenciaProducto> ListarReferenciaProducto() {
		return (List<ReferenciaProducto>) iReferenciaProductoDao.findAll();
	}

	// Listar Referencia Producto Por Id
	@Override
	@Transactional(readOnly = true)// lectura
	public ReferenciaProducto ListarReferenciaProductoPorId(Long id) {
		return iReferenciaProductoDao.findById(id).orElse(null);
	}

	// Listar Paginado Referencia Producto
	@Override
	@Transactional(readOnly = true)// lectura
	public Page<ReferenciaProducto> ListarPaginadoReferenciaProducto(Pageable paginador) {
		return iReferenciaProductoDao.findAll(paginador) ;
	}

	// Guardar Referencia Producto
	@Override
	public ReferenciaProducto GuardarReferenciaProducto(ReferenciaProducto referenciaProducto) {
		return iReferenciaProductoDao.save(referenciaProducto);
	}

	// Eliminar Referencia Producto
	@Override
	public void EliminarReferenciaProducto(Long id) {
		iReferenciaProductoDao.deleteById(id);
	}

}
