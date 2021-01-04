package com.Leather.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.common.CommonRestController;
import com.Leather.models.entity.Producto;
import com.Leather.models.entity.UnidadMedida;
import com.Leather.models.services.IUnidadMedidaService;


@CrossOrigin(origins = {"http://localhost:4200", "*"}, exposedHeaders = {"Access-Control-Expose-Headers", "Content-Disposition"})
@RequestMapping("/api/UnidadMedida")
@RestController
public class UnidadMedidaRestController extends CommonRestController<UnidadMedida, IUnidadMedidaService>{

	@Autowired
	IUnidadMedidaService iUnidadMedidaService;
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarUnidadMedida(@PathVariable Long id, @RequestBody UnidadMedida unidadMedidaFormulario){
		
		UnidadMedida unidadMedidaExistente = null;
		UnidadMedida unidadMedidaNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		unidadMedidaExistente = iUnidadMedidaService.obtenerElementoPorID(id);
		
		if(unidadMedidaExistente == null) {
			mapa.put("mensaje", "El elemento con id "+ id +" no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		
		try {
			unidadMedidaExistente.setId(unidadMedidaFormulario.getId());
			unidadMedidaExistente.setCategoria(unidadMedidaFormulario.getCategoria());
			unidadMedidaExistente.setNombre(unidadMedidaFormulario.getNombre());
			unidadMedidaExistente.setAbreviatura(unidadMedidaFormulario.getAbreviatura());
			
			unidadMedidaNuevo = iUnidadMedidaService.guardarElemento(unidadMedidaExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el elemento "+ unidadMedidaExistente.getNombre());
		}
		
		mapa.put("mensaje", "El elemento "+ unidadMedidaExistente.getNombre() +" ha sido modificado exitosamente");
		mapa.put("unidadMedida", unidadMedidaNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
}
