package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Pedido;

public interface IPedidoDao extends PagingAndSortingRepository<Pedido, Long>{
	Page<Pedido> findAll(Pageable pageable);
}
