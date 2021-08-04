package com.Leather.models.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.BodegaInventario;


public interface IBodegaInventarioDao extends PagingAndSortingRepository<BodegaInventario, Long>{
	
	
	Page<BodegaInventario> findAll(Pageable pageable);
	
	@Query("select bi from BodegaInventario bi  where bi.referencia = ?1")
	BodegaInventario ListarBodegaPorRef(String referencia);
}
