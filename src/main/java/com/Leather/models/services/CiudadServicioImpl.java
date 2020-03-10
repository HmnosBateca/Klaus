package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.ICiudadDao;
import com.Leather.models.dao.IClienteDao;
import com.Leather.models.entity.Ciudad;

@Service
public class CiudadServicioImpl implements ICiudadService {

	@Autowired
	private ICiudadDao ciudadDao;
	@Override
	@Transactional(readOnly=true)
	public List<Ciudad> listarCiudad() {
		return (List<Ciudad>)ciudadDao.findAll();
	}
}
