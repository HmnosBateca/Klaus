package com.Leather.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.Leather.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
