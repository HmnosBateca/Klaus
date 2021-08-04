package com.Leather.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.GastoMaterialProducto;



public interface IGastoMaterialProductoDao extends PagingAndSortingRepository<GastoMaterialProducto, Long>{
	
	
	/*
	 * Esle método obtiene el gasto de un material por talla, tipo de talla y pieza
	 * 
	 * select * from gasto_material_producto GMP, tallas TA, tipo_tallas TT, piezas PI
		where 	GMP.talla_id = TA.id and
				TT.ID = TA.tipo_talla_id and 
				PI.id = GMP.pieza_id and
				TA.id = '1' and TT.id = '1' and
				PI.id = '2'
	 * */
	@Query("select GMP from GastoMaterialProducto GMP, Talla TA, TipoTalla TT, Pieza PI where GMP.talla.id = TA.id and TT.id = TA.tipoTalla.id and PI.id = GMP.pieza.id and TA.id = ?1 and TT.id = ?2 and PI.id = ?3")
	public GastoMaterialProducto obtenerGastoMaterialPorTallaTipoTalla(Long idTalla, Long idTipoTalla, Long idPieza);
	
	
	@Query("select GMP from GastoMaterialProducto GMP, Producto PR where GMP.producto.id = PR.id and PR.id = ?1")
	public List<GastoMaterialProducto> obtenerGastoMaterialPorProducto(Long idProducto);
}
