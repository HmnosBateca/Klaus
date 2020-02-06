package com.Leather.springboot.backend.apirest.models.entity.models.services;

import java.util.List;

import com.Leather.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService{//metodos listar, buscar, guardar, eliminar
	public List<Cliente>findAll();
}
