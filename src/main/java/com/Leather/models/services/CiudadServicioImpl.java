package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.ICiudadDao;
import com.Leather.models.dao.IClienteDao;
import com.Leather.models.entity.Ciudad;
import com.Leather.models.entity.Cliente;

@Service
public class CiudadServicioImpl implements ICiudadService {

	@Autowired
	private ICiudadDao ciudadDao;
	@Override
	@Transactional(readOnly=true)
	public List<Ciudad> listarCiudad() {
		return (List<Ciudad>)ciudadDao.findAll();
	}
	
	@Override//implementar el metodo findById(id)
	@Transactional(readOnly=true)
	public Ciudad listarCiudadId(Long id){
		return ciudadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> listarCiudadesPorDpto(Long id) {
		return ciudadDao.listarCiudadesPorDpto(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listarClientesPorCiudades(Long id) {
		return ciudadDao.listarClientesPorCiudades(id);
	}
}
