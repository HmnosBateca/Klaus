package com.Leather.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Leather.models.dao.IDepartamentoDao;
import com.Leather.models.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	private IDepartamentoDao iDepartamentoDao;
	
	@Override
	public List<Departamento> listarDepartamentos() {
		return (List<Departamento>)iDepartamentoDao.findAll();
	}
}
