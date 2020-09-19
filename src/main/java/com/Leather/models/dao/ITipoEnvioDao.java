package com.Leather.models.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.Leather.models.entity.TipoEnvio;

public interface ITipoEnvioDao extends PagingAndSortingRepository<TipoEnvio, Long>{
//public interface ITipoEnvioDao extends CrudRepository<TipoEnvio, Long> {
	Page<TipoEnvio> findAll(Pageable pageable);
}
