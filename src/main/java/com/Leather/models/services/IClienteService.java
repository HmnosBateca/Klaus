package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Cliente;

public interface IClienteService{//metodos listar, buscar, guardar, eliminar
	public List<Cliente>findAll();
	public Page<Cliente>findAll(Pageable pageable);
	public Cliente findById(Long id);//busca por id
	public Cliente save(Cliente cliente);//guarda
	public void delete(Long id);//Borra por id
}
