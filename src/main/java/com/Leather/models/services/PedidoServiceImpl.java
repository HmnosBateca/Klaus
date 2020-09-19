package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IPedidoDao;
import com.Leather.models.entity.Pedido;

@Service
public class PedidoServiceImpl implements IPedidoService{
	
	@Autowired
	private IPedidoDao iPedidoDao;

	
	// Ver Listado
	@Override
	@Transactional(readOnly = true)
	public List<Pedido> ListarPedido() {
	return (List<Pedido>)iPedidoDao.findAll();
	}

	// Listado Paginado
	@Override
	@Transactional(readOnly = true)
	public Page<Pedido> PaginarPedido(Pageable pageable) {
		return iPedidoDao.findAll(pageable);
	}

	// Ver Por Id
	@Override
	@Transactional(readOnly=true)
	public Pedido ObtenerPedidoPorId(Long id) {
		return iPedidoDao.findById(id).orElse(null);
	}

	// Guardar
	@Override
	public Pedido GuardarPedido(Pedido pedido) {
		return iPedidoDao.save(pedido);
	}

	// Eliminar
	@Override
	public void EliminarPedido(Long id) {
		iPedidoDao.deleteById(id);		
	}

}
