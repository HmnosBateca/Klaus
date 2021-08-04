package com.Leather.models.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.IGastoMaterialProductoDao;
import com.Leather.models.entity.GastoMaterialProducto;



@Service
public class GastoMaterialProductoServiceImpl extends CommonServiceImpl<GastoMaterialProducto, IGastoMaterialProductoDao> implements IGastoMaterialProductoService{

	@Override
	@Transactional(readOnly = true)
	public GastoMaterialProducto obtenerGastoMaterialPorTallaTipoTalla(Long idTalla, Long idTipoTalla, Long idPieza) {
		return super.iRepositorio.obtenerGastoMaterialPorTallaTipoTalla(idTalla, idTipoTalla, idPieza);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GastoMaterialProducto> obtenerGastoMaterialPorProducto(Long idProducto) {
		return super.iRepositorio.obtenerGastoMaterialPorProducto(idProducto);
	}

	

}
