package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IEnvioCiudadDao;
import com.Leather.models.entity.EnvioCiudad;


@Service
public class EnvioCiudadServicioIplm implements IEnvioCiudadService {
	
	@Autowired
	private IEnvioCiudadDao iEnvioCiudadDao;
	
	@Override
	@Transactional(readOnly = true)//lectura
	public List<EnvioCiudad> findAll(){//Listar
		return (List<EnvioCiudad>)iEnvioCiudadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)// lectura
	public EnvioCiudad findById(Long id) {
		return iEnvioCiudadDao.findById(id).orElse(null);
	}

	@Override
	public EnvioCiudad save(EnvioCiudad envioCiudad) {
		return iEnvioCiudadDao.save(envioCiudad);
	}

	@Override
	public void delete(Long id) {
		iEnvioCiudadDao.deleteById(id);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<EnvioCiudad> findAll(Pageable pageable) {
		return iEnvioCiudadDao.findAll(pageable);
	}
}
