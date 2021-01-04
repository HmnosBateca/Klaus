package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Producto;


public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {
	
	
	@Query("select distinct pr from BodegaInventario bi, Producto pr where bi.producto.id = pr.id")
	Page<Producto> ListarProductosEnBodegaInventario(Pageable pageable);

}
