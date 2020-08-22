package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Material;




public interface IMaterialService {
	
	public List<Material> listarMateriales();
	public Page<Material> obtenerMaterialPaginado(Pageable paginador);
	public Material obtenerMaterialPorID(Long id);
	public Material agregarMaterial(Material material);
	public void eliminarMaterial(Long id);
	public List<Material> buscarMaterialPorNombre(String nombre);

}
