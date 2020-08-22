package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IMaterialDao;
import com.Leather.models.entity.Material;




@Service
public class MaterialServiceImpl implements IMaterialService{

	
	// inyección de dependencia
	@Autowired
	IMaterialDao iMaterialDAO;	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Material> listarMateriales() {
		return (List<Material>) iMaterialDAO.findAll(Sort.by(Direction.ASC, "nombre"));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Material> obtenerMaterialPaginado(Pageable paginador) {
		return iMaterialDAO.findAll(paginador);
	}

	@Override
	@Transactional(readOnly = true)
	public Material obtenerMaterialPorID(Long id) {
		return iMaterialDAO.findById(id).orElse(null);
	}

	@Override
	public Material agregarMaterial(Material material) {
		return iMaterialDAO.save(material);
	}

	@Override
	public void eliminarMaterial(Long id) {
		iMaterialDAO.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Material> buscarMaterialPorNombre(String nombre) {
		return iMaterialDAO.buscarMaterialPorNombre(nombre);
	}

	
	
}
