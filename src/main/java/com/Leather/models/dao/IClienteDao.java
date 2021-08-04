package com.Leather.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	Page<Cliente> findAll(Pageable pageable);
	
	@Query("select cli from Cliente cli  where cli.documento = ?1")
	Cliente clientePorDocumento(Long documento);

}
