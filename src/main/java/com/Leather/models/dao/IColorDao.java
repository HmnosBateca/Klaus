package com.Leather.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Color;



public interface IColorDao extends PagingAndSortingRepository<Color, Long> {
	
	
	@Query("select c from Color c where lower(c.nombre) like lower(concat('%',?1,'%'))")
	public List<Color> buscarColorPorNombre(String nombre); 
	
}