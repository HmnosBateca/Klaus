package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.EmpresaTransportadora;

public interface IEmpresaTransportadoraDao extends PagingAndSortingRepository<EmpresaTransportadora, Long> {
	Page<EmpresaTransportadora>findAll(Pageable pageable);
}
