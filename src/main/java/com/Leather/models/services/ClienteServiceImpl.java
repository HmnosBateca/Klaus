package com.Leather.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.dao.IClienteDao;
import com.Leather.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
	private IClienteDao clienteDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente>findAll(){
    	return(List<Cliente>) clienteDao.findAll();
    }
    
    @Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);//optional nos permite manejar el contenido de consulta
	}
    
    @Override
    @Transactional(readOnly = true)
    public Cliente clientePorDocumento(Long documento){
    	return clienteDao.clientePorDocumento(documento);
    }
    
    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
    	return clienteDao.save(cliente);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
    	clienteDao.deleteById(id);    	
    }

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
        
}
