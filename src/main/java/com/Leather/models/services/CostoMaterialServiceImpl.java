package com.Leather.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.ICostoMaterialDao;
import com.Leather.models.entity.CostoMaterial;
import com.Leather.models.entity.Material;

@Service
public class CostoMaterialServiceImpl extends CommonServiceImpl<CostoMaterial, ICostoMaterialDao> implements ICostoMaterialService {

	@Autowired
	ICostoMaterialDao iCostoMaterialDao;
	
	@Override
	public CostoMaterial obtenerCostoMaterialIdMaterial(Long id) {
		return iCostoMaterialDao.obtenerCostoMaterialIdMaterial(id);
	}

	@Override
	public List<Material> obtenerMaterialRegistrado() {
		return iCostoMaterialDao.obtenerMaterialesRegistrados();
	}

}
