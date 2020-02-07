package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IClienteDao;
import com.Leather.models.entity.Cliente;

@Service
public class IClienteServiceImpl implements IClienteService{
    @Autowired
	private IClienteDao clienteDao;
    
    
    @Transactional(readOnly = true)
    public List<Cliente>findAll(){
    	return(List<Cliente>) clienteDao.findAll();
    }
}
