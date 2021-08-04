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
	
	
	/*
	 * Consulta que determina las tallas de un TipoTalla específico que no se han registrado en GastoMaterialProducto.
	 * Es decir, esta consulta muestra las tallas a las cuales no se les ha calculado el costo de sus materiales para un producto determinado
	 * 
	 *  SQL:
	 *  
	 *  select TAL.id, TAL.talla from tallas TAL, tipo_tallas TT where TAL.id = TT.talla_id
		except
		select TA.id, TA.talla from tallas TA, gasto_material_producto GMP
		where TA.id = GMP.talla_id and producto_id = '1'
	 * */
	@Query(value= "(select TAL.id, TAL.talla,TAL.descripcion,TAL.fecha_modificacion, TAL.fecha_registro, TAL.hora_modificacion, TAL.hora_registro, TAL.tipo_talla_id "
			+ "		from tallas TAL where TAL.tipo_talla_id = ?1) "
			+ "	 except "
			+ "		(select TA.id, TA.talla,TA.descripcion,TA.fecha_modificacion, TA.fecha_registro, TA.hora_modificacion, TA.hora_registro, TA.tipo_talla_id "
					+ "from tallas TA, gasto_material_producto GMP "
					+ "where TA.id = GMP.talla_id and GMP.producto_id = ?2)", nativeQuery=true)
	List<Talla> ListarTallasNoAsignadasGastoMaterialProducto(Long idTipoTalla, Long idProducto);
	
	
}
