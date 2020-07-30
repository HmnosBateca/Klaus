package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.ITipoTallaDao;
import com.Leather.models.entity.TipoTalla;




@Service
public class TipoTallaServiceImpl implements ITipoTallaService{

	
	// inyección de dependencia
	@Autowired
	ITipoTallaDao iTipoTallaDao;
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoTalla> listarTipoTalla() {
		return (List<TipoTalla>) iTipoTallaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipoTalla> listarTipoTallaPaginado(Pageable paginador) {
		return iTipoTallaDao.findAll(paginador);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoTalla obtenerTallaPorID(Long id) {
		return iTipoTallaDao.findById(id).orElse(null);
	}

	@Override
	public TipoTalla guardarTipoTalla(TipoTalla tipoTalla) {
		return iTipoTallaDao.save(tipoTalla);
	}

	@Override
	public void eliminarTipoTalla(Long id) {
		iTipoTallaDao.deleteById(id);
	}
	

}
