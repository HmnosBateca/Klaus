package com.Leather.models.services;

import java.util.List;

import com.Leather.common.ICommonService;
import com.Leather.models.entity.EstadoPedido;

public interface IEstadoPedidoService extends ICommonService<EstadoPedido>{
	public List<EstadoPedido>ListarEstadosPedidosPorPedidos(Long idPedido);
}
