package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.ReferenciaProducto;

public interface IReferenciaProductoDao extends PagingAndSortingRepository<ReferenciaProducto, Long>{
	Page<ReferenciaProducto> findAll(Pageable pageable);
}
