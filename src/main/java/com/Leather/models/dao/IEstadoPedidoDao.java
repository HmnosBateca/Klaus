package com.Leather.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.EstadoPedido;

public interface IEstadoPedidoDao extends PagingAndSortingRepository<EstadoPedido, Long>{
	
	@Query("select distinct e from EstadoPedido e,Pedido p  where e.pedido.id = p.id and p.id = ?1")
	List<EstadoPedido> ListarEstadosPedidosPorPedidos(Long idPedido);
}
