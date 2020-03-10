package com.Leather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Ciudad;
import com.Leather.models.services.ICiudadService;

@RestController
@RequestMapping("api")
public class CiudadRestController {
	@Autowired
	private ICiudadService ciudadService;
	@GetMapping("/ciudades")
	public List<Ciudad>index(){
		return ciudadService.listarCiudad();		
	}
}
