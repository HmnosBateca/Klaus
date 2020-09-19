package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.Leather.models.dao.IEstadoEnvioCiudadDao;
import com.Leather.models.entity.EstadoEnvioCiudad;

@Service
public class EstadoEnvioCiudadServiceImpl implements IEstadoEnvioCiudadService{
	@Autowired
	private IEstadoEnvioCiudadDao iEstadoEnvioCiudadDao;

	@Override
	@Transactional(readOnly = true)// lectura
	public List<EstadoEnvioCiudad> ListarEstadoEnvioCiudad() {
		return(List<EstadoEnvioCiudad>) iEstadoEnvioCiudadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<EstadoEnvioCiudad> findAll(Pageable pageable) {
		return iEstadoEnvioCiudadDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public EstadoEnvioCiudad findById(Long id) {
		return iEstadoEnvioCiudadDao.findById(id).orElse(null);
	}

	@Override
	public EstadoEnvioCiudad save(EstadoEnvioCiudad estadoEnvioCiudad) {
		return iEstadoEnvioCiudadDao.save(estadoEnvioCiudad);
	}

	@Override
	public void delete(Long id) {
		iEstadoEnvioCiudadDao.deleteById(id);
		
	}
	
}
