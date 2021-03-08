package com.Leather.models.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.IEstadoPedidoDao;
import com.Leather.models.entity.EstadoPedido;

@Service
public class EstadoPedidoServiceImpl extends CommonServiceImpl<EstadoPedido, IEstadoPedidoDao> implements IEstadoPedidoService{
	@Override
	@Transactional(readOnly = true)
	public List<EstadoPedido> ListarEstadosPedidosPorPedidos(Long idPedido) {
		return super.iRepositorio.ListarEstadosPedidosPorPedidos(idPedido);
	}	
}
