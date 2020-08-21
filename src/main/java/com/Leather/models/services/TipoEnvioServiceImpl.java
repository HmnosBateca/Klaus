package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.ITipoEnvioDao;
import com.Leather.models.entity.TipoEnvio;

@Service
public class TipoEnvioServiceImpl implements ITipoEnvioService {

	@Autowired
	private ITipoEnvioDao iTipoEnvioDao;

	@Override
	@Transactional(readOnly = true)// true modo lectura
	public List<TipoEnvio> findAll() {
		return (List<TipoEnvio>) iTipoEnvioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)// true modo lectura
	public TipoEnvio findById(Long id) {
		return iTipoEnvioDao.findById(id).orElse(null);
	}

	@Override
	public TipoEnvio save (TipoEnvio tipoEnvio) {
		 return iTipoEnvioDao.save(tipoEnvio);
	}

	@Override
	public void delete(Long id) {
		iTipoEnvioDao.deleteById(id);
	}
	

	@Override
    @Transactional(readOnly = true)
	public Page<TipoEnvio> findAll(Pageable pageable) {
	  return iTipoEnvioDao.findAll(pageable);
	}
}
