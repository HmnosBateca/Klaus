package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.BodegaInventario;

public interface IBodegaInventarioDao extends PagingAndSortingRepository<BodegaInventario, Long>{
	Page<BodegaInventario> findAll(Pageable pageable);
}
