package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.EstadoEnvioCiudad;

public interface IEstadoEnvioCiudadDao extends PagingAndSortingRepository<EstadoEnvioCiudad,Long> {
	Page<EstadoEnvioCiudad> findAll(Pageable pageable);
}
