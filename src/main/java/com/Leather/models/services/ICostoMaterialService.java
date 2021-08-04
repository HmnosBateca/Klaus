package com.Leather.models.services;

import java.util.List;

import com.Leather.common.ICommonService;
import com.Leather.models.entity.CostoMaterial;
import com.Leather.models.entity.Material;

public interface ICostoMaterialService extends ICommonService<CostoMaterial>{
	
	CostoMaterial obtenerCostoMaterialIdMaterial(Long id);
	
	List<Material> obtenerMaterialRegistrado();

}
