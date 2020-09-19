package com.Leather.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.EstadoEnvioCiudad;
import com.Leather.models.services.IEstadoEnvioCiudadService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController//Api Rest
@RequestMapping("/api") // Generamos url

public class EstadoEnvioCiudadRestController {
	
	@Autowired
	private IEstadoEnvioCiudadService iEstadoEnvioCiudadService;
	private Map<String, Object> mapa;
	
	// Obtener Lista
	@GetMapping("/EstadoEnvioCiudad")
	public List<EstadoEnvioCiudad> ListarEstadoEnvioCiudad() {
		return iEstadoEnvioCiudadService.ListarEstadoEnvioCiudad();// metodo
	}
	
	// Obtener Paginado
	@GetMapping("/EstadoEnvioCiudad/pagina")
	public Page<EstadoEnvioCiudad> index(Pageable pageable) {
		return iEstadoEnvioCiudadService.findAll(pageable);
	}
	
	// Obtener por Id 
	@GetMapping("/EstadoEnvioCiudad/{id}") // Get por id
	public ResponseEntity<?> show(@PathVariable Long id) {
		EstadoEnvioCiudad estadoEnvioCiudad = null;
		Map<String, Object> mapa = new HashMap<>();
		try {// si hay errror
			estadoEnvioCiudad = iEstadoEnvioCiudadService.findById(id);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if (estadoEnvioCiudad == null) {
			mapa.put("mensaje", "El Estado Envio Ciudad ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
		}
		return new ResponseEntity<EstadoEnvioCiudad>(estadoEnvioCiudad, HttpStatus.OK);
	}
	
	// Crear Post Agregar 
	@PostMapping("/EstadoEnvioCiudad")
	public ResponseEntity<?> create(@RequestBody EstadoEnvioCiudad estadoEnvioCiudad) {
		EstadoEnvioCiudad estadoEnvioCiudadNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		try {
			estadoEnvioCiudadNuevo = iEstadoEnvioCiudadService.save(estadoEnvioCiudad);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar insert en la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));// Por que ocurrio el error																		 
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		mapa.put("mensaje", "El Estado Envio Ciudad ha sido creado con éxito!");
		mapa.put("estadoEnvioCiudad", estadoEnvioCiudadNuevo);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	//Actualizar Put
	@PutMapping("/EstadoEnvioCiudad/{id}") // con la id obtenemos de la base de datos y actualizamos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody EstadoEnvioCiudad estadoEnvioCiudad, @PathVariable Long id) {// Ya modificado
		EstadoEnvioCiudad estadoEnvioCiudadActual = null;
		Map<String, Object> mapa = new HashMap<>();
		estadoEnvioCiudadActual = iEstadoEnvioCiudadService.findById(id);// obtenemos por id
		if (estadoEnvioCiudadActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, el Estado Envio Ciudad iD: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// Estatus 404
		}
		try {
			estadoEnvioCiudadActual.setFechaRegistro(estadoEnvioCiudad.getFechaRegistro());
			estadoEnvioCiudadActual.setFechaEnvio(estadoEnvioCiudad.getFechaEnvio());
			estadoEnvioCiudadActual.setEntrega(estadoEnvioCiudad.getEntrega());
			estadoEnvioCiudadActual.setNumeroGuia(estadoEnvioCiudad.getNumeroGuia());
			estadoEnvioCiudadActual.setEstadoEnvio(estadoEnvioCiudad.getEstadoEnvio());
			estadoEnvioCiudadActual.setArchivoAdjunto(estadoEnvioCiudad.getArchivoAdjunto());
			estadoEnvioCiudadActual.setObservaciones(estadoEnvioCiudad.getObservaciones());
			
			estadoEnvioCiudadActual = iEstadoEnvioCiudadService.save(estadoEnvioCiudadActual);// persistir o guardar
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al actualizar Estado Envío Ciudad en la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// Staus
		}
		mapa.put("mensaje", "El Estado Envío Ciudad ha sido actualizado con éxitos!");
		mapa.put("estadoEnvioCiudad", estadoEnvioCiudadActual);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	// Eliminar
	@DeleteMapping("/EstadoEnvioCiudad/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mapa = new HashMap<>();
		try {
			iEstadoEnvioCiudadService.delete(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al eliminar de la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);//status 404
			
		}
		mapa.put("mensaje", "El Estado Envío ciudad ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
		
	}
}


