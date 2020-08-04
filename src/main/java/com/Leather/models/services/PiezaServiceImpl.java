package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IPiezaDao;
import com.Leather.models.entity.Pieza;


@Service
public class PiezaServiceImpl implements IPiezaService{

	
	@Autowired
	IPiezaDao iPiezaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Pieza> listarPiezas() {
		return (List<Pieza>) iPiezaDao.findAll(Sort.by(Direction.ASC, "nombre"));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Pieza> listarPiezasPaginado(Pageable paginador) {
		return iPiezaDao.findAll(paginador);
	}

	@Transactional(readOnly = true)
	@Override
	public Pieza obtenerPiezaPorID(Long id) {
		return iPiezaDao.findById(id).orElse(null);
	}

	@Override
	public Pieza guardarPieza(Pieza pieza) {
		return iPiezaDao.save(pieza);
	}

	@Override
	public void eliminarPieza(Long id) {
		iPiezaDao.deleteById(id);
	}

	
	
}
