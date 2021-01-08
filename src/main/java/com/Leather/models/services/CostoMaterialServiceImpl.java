package com.Leather.models.services;

import org.springframework.stereotype.Service;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.ICostoMaterialDao;
import com.Leather.models.entity.CostoMaterial;

@Service
public class CostoMaterialServiceImpl extends CommonServiceImpl<CostoMaterial, ICostoMaterialDao> implements ICostoMaterialService {

}
