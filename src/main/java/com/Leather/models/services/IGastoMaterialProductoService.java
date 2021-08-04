package com.Leather.models.services;

import java.util.List;

import com.Leather.common.ICommonService;
import com.Leather.models.entity.GastoMaterialProducto;


public interface IGastoMaterialProductoService extends ICommonService<GastoMaterialProducto>{
	
	public GastoMaterialProducto obtenerGastoMaterialPorTallaTipoTalla(Long idTalla, Long idTipoTalla, Long idPieza);
	public List<GastoMaterialProducto> obtenerGastoMaterialPorProducto(Long idProducto);

}
