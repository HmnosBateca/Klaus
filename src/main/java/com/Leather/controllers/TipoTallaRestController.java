package com.Leather.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Talla;
import com.Leather.models.entity.TipoTalla;
import com.Leather.models.services.ITipoTallaService;



@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoTallaRestController {

	// inyección de dependencia
	@Autowired
	ITipoTallaService iTipoTallaService;
	
	/*
	 * El método listarTipoTallas() método obtiene todos los tipos de talla registradas en base de datos
	 * - Parámetros: ninguno
	 * - Retorna: Una lista de tipos de tallas 
	*/
	@GetMapping("/tipoTalla")
	public List<TipoTalla> listarTipoTallas(){
		return iTipoTallaService.listarTipoTalla();
	}
	
	
	/*
	 * El metodo obtenerTipoTallaPorID permite obtener el tipo de talla por su ID.
	 * - Parámetros: el ID
	 * - Retorna: Un ResponseEntity de tipo genérico 
	*/
	@GetMapping("tipoTalla/{id}")
	public ResponseEntity<?> obtenerTipoTallaPorID(@PathVariable Long id){
		
		
		TipoTalla tipoTalla = null; // acá quedará almacenado el tipo de talla
		Map<String,Object> mapa = new HashMap<>(); // el mapa que será enviado en el ReponseEntity
		
		// Obtenemos el tipo de talla por su id. Se maneja un try/catch por si ocurre algo (es mejor)
		try {
			tipoTalla = iTipoTallaService.obtenerTallaPorID(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje","Ha ocurrido un error al obtener el tipo de talla con ID "+ id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		/* 
		 Si tipoTalla sigue nulo es porque no fué encontrado, por lo tanto hay que retornar un 
		 ResponseEntity con el mensaje de error y el HttpStatus
		*/
		
		if(tipoTalla == null) {
			mapa.put("mensaje","El tipo de talla con ID " + id + " no se encuentra registrado");
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoTalla>(tipoTalla, HttpStatus.OK);
	}
	
	
	/*
	 *	El método listarTipoTallaPaginado permite listar los tipos de talla usando paginación
	 *	- Parámetros: El paginador (tiene la información de la paginación)
	 *	- Retorna: un ResponseEntity con la lista paginada 
	*/
	@GetMapping("/tipoTalla/pagina")
	public ResponseEntity<?> listarTipoTallaPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(),Sort.by("tipoTalla").ascending());
		return ResponseEntity.ok(iTipoTallaService.listarTipoTallaPaginado(pag));
	}
	
	
	/*
	 * El método guardarTipoTalla permite guardar la Talla en base de datos
	 * - Parámetros: La talla a guardar en base de datos
	 * - Retorna un ResposeEntity con el resultado del proceso (un error o la Talla creada)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PostMapping("/tipoTalla")
	public ResponseEntity<?> guardarTipoTalla(@RequestBody TipoTalla tipoTalla ){
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		TipoTalla TipoTallaNuevo = null;
		
		
		try {
			tipoTalla = iTipoTallaService.guardarTipoTalla(tipoTalla);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ha ocurrido un error al guardar el tipo de talla");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El tipo de talla " + tipoTalla.getTipoTalla() +" ha sido registrado exitosamente");
		mapa.put("talla", TipoTallaNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.CREATED);
	}
	
	
	/*
	 * El método actualizarTipoTalla obtiene la talla con el ID especificado en la URL para luego establecer los
	 * nuevos valores con los que se han proporcionado en el TipoTalla que viene como parámetro en el Body de la petición
	 * 
	 * Parámetros: 
	 * 			- El ID del TipoTalla a modificar (URL de la petición)
	 * 			- El TipoTalla con la nueva información (Body de la petición) 
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (el tipoTala modificado o el mensaje de error)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PutMapping("/tipoTalla/{id}")
	public ResponseEntity<?> editarTipoTalla(@PathVariable Long id, @RequestBody TipoTalla tipoTallaForm){
		
		
		TipoTalla tipoTallaExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		try {
			tipoTallaExistente = iTipoTallaService.obtenerTallaPorID(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al consultar el tipo de talla con ID "+ id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(tipoTallaExistente == null) {
			mapa.put("mensaje", "El tipo de talla con ID "+ id + " no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		try {
			tipoTallaExistente.setTipoTalla(tipoTallaForm.getTipoTalla());
			tipoTallaExistente.setDescripcion(tipoTallaForm.getDescripcion());
			iTipoTallaService.guardarTipoTalla(tipoTallaExistente);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar el tipo de talla con ID "+ id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El tipo de talla "+ tipoTallaForm.getTipoTalla() + " ha sido modificado exitosamente");
		mapa.put("tipoTalla", tipoTallaExistente);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	/*
	 * El método eliminarTipoTalla obtiene el tipo de talla con el ID especificado en la URL para luego
	 * eliminar la talla en base de datos
	 * 
	 * Parámetros: 
	 * 			- El ID del tipo de talla a eliminar (URL de la petición)
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (el mensaje de étixo o error)
	*/
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@DeleteMapping("/tipoTalla/{id}")
	public ResponseEntity<?> eliminarTipoTalla(@PathVariable Long id){
		
		TipoTalla tipoTallaExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		try {
			tipoTallaExistente = iTipoTallaService.obtenerTallaPorID(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al consultar el tipo de talla con ID "+ id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(tipoTallaExistente == null) {
			mapa.put("mensaje", "El tipo de talla con ID "+ id + " no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		
		try {
			iTipoTallaService.eliminarTipoTalla(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al consultar el tipo de talla con ID "+ id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		mapa.put("mensaje","El tipo de talla " + tipoTallaExistente.getTipoTalla() + " ha sido eliminado exitosamente");
		return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.OK);	}
	
	
}