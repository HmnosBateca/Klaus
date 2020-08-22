package com.Leather.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Color;
import com.Leather.models.entity.Material;




public interface IMaterialDao extends PagingAndSortingRepository<Material, Long> {
	
	
	@Query("select m from Material m where lower(m.nombre) like lower(concat('%',?1,'%'))")
	public List<Material> buscarMaterialPorNombre(String nombre);

}
