package com.Leather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Cliente;
import com.Leather.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200/","*"})//permiso de enviar y recibir con este dominio
@RestController//api rest
@RequestMapping("/api")//generamos la url
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;//listado de clientes
	@GetMapping("/cliente")//listar
	public List<Cliente>index(){
		return clienteService.findAll();
	}
}
