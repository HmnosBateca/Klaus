package com.Leather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Ciudad;
import com.Leather.models.entity.Cliente;
import com.Leather.models.services.ICiudadService;

@CrossOrigin(origins= {"http://localhost:4200", "*"})//dominio o ip del servidor y soporta un arreglo para angular damos acceso al dominio de enviar y resivir
@RestController
@RequestMapping("api")
public class CiudadRestController {
	@Autowired
	private ICiudadService ciudadService;
	@GetMapping("/ciudades")
	public List<Ciudad>index(){
		return ciudadService.listarCiudad();		
	}
	
	@GetMapping("/ciudades/{id}")
	public Ciudad mostrar(@PathVariable Long id) {
		return ciudadService.listarCiudadId(id);
	}
	
	@GetMapping("/ciudadesDpto/{id}")
	public List<Ciudad> listarCiudadesPorDpto(@PathVariable Long id) {
		System.out.print(ciudadService.listarCiudadesPorDpto(id));
		return ciudadService.listarCiudadesPorDpto(id);
	}
	
	@GetMapping("/clientesciud/{id}")
	public  List<Cliente>listarClientesPorCiudad(@PathVariable Long id) {
		System.out.print(ciudadService.listarClientesPorCiudades(id));
		return ciudadService.listarClientesPorCiudades(id);
	}
	
}
