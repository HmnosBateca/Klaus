package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Cotizacion;

public interface ICotizacionDao extends PagingAndSortingRepository<Cotizacion, Long>{
	Page<Cotizacion> findAll(Pageable pageable);
}
