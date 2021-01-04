package com.Leather.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


public class CommonRestController<E, S extends ICommonService<E>> {
	
	
	// inyección de dependencia
	@Autowired
	protected S iService;

	
	/*
	 * El método listarElementos() método obtiene todos los elementos<E> registrados en base de datos
	 * - Parámetros: ninguno
	 * - Retorna: Una lista de Elementos de la clase "E"
	*/
	@GetMapping
	public List<E> listarElementos(){
		return iService.listarElementos();
	}
	
		
	/*
	 *	El método listarElementosPaginado permite listar los elementos usando paginación
	 *	- Parámetros: El paginador (tiene la información de la paginación)
	 *	- Retorna: un ResponseEntity con la lista paginada
	*/
	@GetMapping("/pagina")
	public ResponseEntity<?> listarElementosPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize());
		return ResponseEntity.ok(iService.listarElementosPaginado(pag));
	}
	
	
	
	
	
	/*
	 * El metodo obtenerElementoPorID permite obtener un elemento por su ID.
	 * - Parámetros: el ID
	 * - Retorna: Un ResponseEntity de tipo genérico 
	*/
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerElementoPorID(@PathVariable Long id) {
		
		E entity = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			entity = this.iService.obtenerElementoPorID(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el elemento con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(entity == null) {
			mapa.put("mensaje", "El elemento con ID " + id + "no se encuentra en base de datos");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< E >(entity, HttpStatus.OK);
	}
	
	
	
	/*
	 * El método guardarElemento permite guardar el Elemento<E> en base de datos
	 * - Parámetros: El elemento a guardar en base de datos
	 * - Retorna: Un ResposeEntity con el resultado del proceso (un error o el Elemento creado)
	*/
	@PostMapping
	public ResponseEntity<?> guardarElemento(@RequestBody E entity){
		
		E entityNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		
		try {
			entityNuevo = iService.guardarElemento(entity);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar el elemento");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "Registro exitoso");
		mapa.put("elemento", entityNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
		
	}
	
		
	/*
	 * El método eliminarElemento obtiene el elemento con el ID especificado en la URL para luego
	 * eliminar el elemento de la base de datos
	 * 
	 * Parámetros: 
	 * 			- El ID del elemento a eliminar (URL de la petición)
	 * 
	 * Retorna: Un ResponseEntity con la respuesta de la petición (el mensaje de éxito o error)
	*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarElemento(@PathVariable Long id){
		
		E elementoExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		elementoExistente = iService.obtenerElementoPorID(id);
		
		if(elementoExistente == null) {
			mapa.put("mensaje","El elemento con id "+id + " no se encuentra regitrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		try{
			iService.eliminarElemento(id);
		} catch(DataAccessException e) {
			mapa.put("mensaje","Ocurrió un error al eliminar el elemento");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "Registro exitoso");	
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
}
