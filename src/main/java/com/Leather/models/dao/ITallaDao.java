package com.Leather.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Talla;



public interface ITallaDao extends PagingAndSortingRepository<Talla, Long>{

	
	/*
	 * Consulta en postgres: tallas del producto con id "?" que se encuentran en bodega
	 * 
	 	SELECT TA
	 	FROM bodega_inventarios BI, productos PR, tallas TA
	 	WHERE BI.producto_id = '1' and BI.producto_id = PR.id and BI.talla_id = TA.id;
	*/
	
	@Query("select TA from BodegaInventario BI, Producto PR, Talla TA where BI.producto.id = ?1 and BI.producto.id = PR.id and BI.talla.id = TA.id")
	List<Talla> ListarTallasPorProductoEnBodega(Long id);

}
