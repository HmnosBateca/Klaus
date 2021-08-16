package com.Leather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Departamento;
import com.Leather.models.services.IDepartamentoService;

@CrossOrigin(origins = {"http://localhost:4200/","*" })//dominio o ip del servidor y soporta un arreglo para angular damos acceso al dominio de enviar y resivir
@RestController
@RequestMapping("/api")
public class DepartamentoRestController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	
	@GetMapping("/departamentos")
	public List<Departamento>index() {
		return departamentoService.listarDepartamentos();	
	}
	
	
	@GetMapping("/departamentos/{id}")
	public Departamento obtenerDepartamentoPorID(@PathVariable Long id) {
		return this.departamentoService.obtenerDepartamentoPorID(id);
	}
	

}
