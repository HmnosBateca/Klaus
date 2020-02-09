package com.Leather.models.services;

import java.util.List;

import com.Leather.models.entity.Cliente;

public interface IClienteService{//metodos listar, buscar, guardar, eliminar
	public List<Cliente>findAll();
	public Cliente findById(Long id);//busca por id
	public Cliente save(Cliente cliente);//guarda
	public void delete(Long id);//Borra por id
}
