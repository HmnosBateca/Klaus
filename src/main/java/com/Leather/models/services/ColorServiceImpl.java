package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Leather.models.dao.IColorDao;
import com.Leather.models.entity.Color;




@Service
public class ColorServiceImpl implements IColorService{

	// inyección de dependencia
	@Autowired
	IColorDao iColorDao;
	
	
	@Override
	public List<Color> listarColores() {
		return (List<Color>) iColorDao.findAll(Sort.by(Direction.ASC, "nombre"));
	}

	@Override
	public Page<Color> listarColoresPaginado(Pageable paginador) {
		return iColorDao.findAll(paginador);
	}

	@Override
	public Color obtenerColorPorID(Long id) {
		return iColorDao.findById(id).orElse(null);
	}

	@Override
	public Color guardarColor(Color color) {
		return iColorDao.save(color);
	}

	@Override
	public void eliminarColor(Long id) {
		iColorDao.deleteById(id);
	}

	
	
	
}
