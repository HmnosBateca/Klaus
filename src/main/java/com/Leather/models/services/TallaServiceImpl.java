package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Leather.models.dao.ITallaDao;
import com.Leather.models.entity.Talla;



@Service
public class TallaServiceImpl implements ITallaService{

	
	// Inyeccción de dependencia TallaDao
	@Autowired
	ITallaDao iTallaDao;
	
	@Override
	public List<Talla> listarTallas() {
		return (List<Talla>) iTallaDao.findAll(Sort.by(Direction.ASC, "talla"));
	}

	@Override
	public Page<Talla> listarTallasPaginado(Pageable paginador) {
		return iTallaDao.findAll(paginador);
	}

	@Override
	public Talla obtenerTallaPorID(Long id) {
		return iTallaDao.findById(id).orElse(null);
	}

	@Override
	public Talla guardarTalla(Talla talla) {
		return iTallaDao.save(talla);
	}

	@Override
	public void eliminarTalla(Long id) {
		iTallaDao.deleteById(id);
	}
	
	

}
