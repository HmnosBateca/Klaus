package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IEmpresaTransportadoraDao;
import com.Leather.models.entity.EmpresaTransportadora;

@Service
public class EmpresaTransportadoraServicioImpl implements IEmpresaTransportadoraService {
	
	@Autowired
	private IEmpresaTransportadoraDao iEmpresaTransportadoraDao;
	
	@Override
	@Transactional(readOnly = true)// Lectura 
	public List<EmpresaTransportadora>findAll(){
		return (List<EmpresaTransportadora>) iEmpresaTransportadoraDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)// Lectura
	public EmpresaTransportadora findById(Long id) {
		return iEmpresaTransportadoraDao.findById(id).orElse(null);
	}

	@Override
	public EmpresaTransportadora Save(EmpresaTransportadora empresaTransportadora) {
		return iEmpresaTransportadoraDao.save(empresaTransportadora);
	}

	@Override
	public void delete(Long id) {
		iEmpresaTransportadoraDao.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)// lectura
	public Page<EmpresaTransportadora> findAll(Pageable pageable) {
		return iEmpresaTransportadoraDao.findAll(pageable);
	}	
}
