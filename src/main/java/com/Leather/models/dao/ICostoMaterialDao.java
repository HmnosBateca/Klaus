package com.Leather.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.CostoMaterial;
import com.Leather.models.entity.Material;


public interface ICostoMaterialDao extends PagingAndSortingRepository<CostoMaterial, Long>{
	
	/**
	 * Este método obtiene el costo material de acuerdo al ID del material
	 * @param id
	 * @return CostoMaterial
	*/
	@Query("SELECT cm FROM CostoMaterial cm WHERE cm.material.id = ?1")
	CostoMaterial obtenerCostoMaterialIdMaterial(Long id);
	
	/**
	 * Este método obtiene la lista de materiales que han sido vinculados en la tabla CostoMateriales
	*/
	@Query("SELECT mat FROM CostoMaterial cm, Material mat WHERE mat.id = cm.material.id")
	List<Material> obtenerMaterialesRegistrados();
}
