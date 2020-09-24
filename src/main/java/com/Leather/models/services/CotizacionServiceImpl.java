package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.ICotizacionDao;
import com.Leather.models.entity.Cotizacion;

@Service
public class CotizacionServiceImpl implements ICotizacionService {
	
	@Autowired
	public ICotizacionDao iCotizacionDao;

	// Listar Cotizacion
	@Override
	@Transactional(readOnly =  true)
	public List<Cotizacion> ListarCotizacion() {
		return (List<Cotizacion>) iCotizacionDao.findAll();
	}

	// Listar Paginado Cotizacion
	@Override
	@Transactional(readOnly =  true)
	public Page<Cotizacion> ListarCotizacionPaginado(Pageable paginador) {
		return iCotizacionDao.findAll(paginador);
	}

	// Obtener Cotizacion Por Id
	@Override
	@Transactional(readOnly =  true)
	public Cotizacion ObtenerCotizacionPorId(Long id) {
		return iCotizacionDao.findById(id).orElse(null);
	}

	// Guardar Cotizacion
	@Override
	@Transactional
	public Cotizacion GuardarCotizacion(Cotizacion cotizacion) {
		return iCotizacionDao.save(cotizacion);
	}

	// Eliminar Cotizacion
	@Override
	@Transactional
	public void EliminarCotizacion(Long id) {
		iCotizacionDao.deleteById(id);		
	}

}
