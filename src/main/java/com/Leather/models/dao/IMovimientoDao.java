package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Movimiento;

public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento, Long>{
	Page<Movimiento> findAll(Pageable pageable);
}
