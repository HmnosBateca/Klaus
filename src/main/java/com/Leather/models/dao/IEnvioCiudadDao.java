package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.EnvioCiudad;

public interface IEnvioCiudadDao extends PagingAndSortingRepository<EnvioCiudad, Long>{

	Page<EnvioCiudad> findAll(Pageable pageable);
}
