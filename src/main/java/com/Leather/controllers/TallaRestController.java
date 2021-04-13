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
import com.Leather.models.services.ITallaService;


@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TallaRestController {

	// inyección de dependencia
	@Autowired
	ITallaService iTallaService;
	
	
	/*
	 * El método listarTallas() método obtiene todos las tallas registradas en base de datos
	 * - Parámetros: ninguno
	 * - Retorna: Una lista de Tallas 
	*/
	
	@GetMapping("/tallas")
	public List<Talla> listarTallas(){
		return iTallaService.listarTallas();
	}
	
	
	
	
	/*
	 * El metodo obtenerTallaPorID permite obtener la talla por su ID.
	 * - Parámetros: el ID
	 * - Retorna: Un ResponseEntity de tipo genérico 
	*/
	@GetMapping("/tallas/{id}")
	public ResponseEntity<?> obtenerTallaPorID(@PathVariable Long id) {
		
		Talla talla = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			talla = this.iTallaService.obtenerTallaPorID(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener la talla con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(talla == null) {
			mapa.put("mensaje", "La talla con ID " + id + "no se encuentra en base de datos");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< Talla >(talla, HttpStatus.OK);
	}
	
	
	
	
	
	/*
	 *	El método listarTallasPaginado permite listar las tallas usando paginación
	 *	- Parámetros: El paginador (tiene la información de la paginación)
	 *	- Retorna: un ResponseEntity con la lista paginada 
	*/
	
	@GetMapping("/tallas/pagina")
	public ResponseEntity<?> listarTallasPaginado(Pageable paginador){
		
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(),Sort.by("talla").ascending());
		return ResponseEntity.ok(iTallaService.listarTallasPaginado(pag));
	}
	
	
	
	
	/*
	 * El método guardarTalla permite guardar la Talla en base de datos
	 * - Parámetros: La talla a guardar en base de datos
	 * - Retorna un ResposeEntity con el resultado del proceso (un error o la Talla creada)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PostMapping("/tallas")
	public ResponseEntity<?> guardarTalla(@RequestBody Talla talla ){
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		Talla tallaNueva = null;
		
		
		try {
			tallaNueva = iTallaService.guardarTalla(talla);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ha ocurrido un error al guardar la talla");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "La talla " + talla.getTalla() +" ha sido registrada exitosamente");
		mapa.put("talla", tallaNueva);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.CREATED);
		
	}
	
	
	
	
	/*
	 * El método actualizar obtiene la talla con el ID especificado en la URL para luego establecer los
	 * nuevos valores con los que se han proporcionado en la talla que viene como parámetro en el Body de la petición
	 * 
	 * Parámetros: 
	 * 			- El ID de la talla a modificar (URL de la petición)
	 * 			- La talla con la nueva información (Body de la petición) 
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (la talla modificada o el mensaje de error)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PutMapping("/tallas/{id}")
	public ResponseEntity<?> actualizarTalla(@PathVariable Long id, @RequestBody Talla talla){
		
		Talla tallaExistente = iTallaService.obtenerTallaPorID(id);
		Talla tallaNueva = null;
		Map<String,Object> mapa = new HashMap<>();
		
		
		if(tallaExistente == null) {
			mapa.put("mensaje","La talla con id " + id + " no se encuentra registrada");
			return new ResponseEntity<  Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			tallaExistente.setTalla(talla.getTalla());
			tallaExistente.setDescripcion(talla.getDescripcion());
			tallaExistente.setTipoTalla(talla.getTipoTalla());
			tallaNueva = iTallaService.guardarTalla(tallaExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al modificar la talla");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "La talla " + talla.getTalla() + " ha sido modificada exitosamente");
		mapa.put("talla", tallaNueva);
		return new ResponseEntity< Map<String, Object>  > ( mapa,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	/*
	 * El método eliminarTalla obtiene la talla con el ID especificado en la URL para luego
	 * eliminar la talla en base de datos
	 * 
	 * Parámetros: 
	 * 			- El ID de la talla a eliminar (URL de la petición)
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (el mensaje de étixo o error)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@DeleteMapping("/tallas/{id}")
	public ResponseEntity<?> eliminarTalla(@PathVariable Long id) {
		
		Talla tallaExistente = null;
		Map<String, Object> mapa = new HashMap<>();
		
		tallaExistente = iTallaService.obtenerTallaPorID(id);
		
		if(tallaExistente == null) {
			mapa.put("mensaje", "La talla no se encuentra registrada");
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			iTallaService.eliminarTalla(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al eliminar la talla "+ tallaExistente.getTalla());
			mapa.put("error", e.getMessage()+" : "+e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje","La talla " + tallaExistente.getTalla() + " ha sido eliminada exitosamente");
		return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	/*
	 * El metodo obtenerTallasPorProductoEnBodega permite obtener la talla por el ID del producto que se encuentra en bodega.
	 * - Parámetros: el ID
	 * - Retorna: Un ResponseEntity de tipo genérico 
	*/
	@GetMapping("/tallas/bodega/producto/{id}")
	public ResponseEntity<?> obtenerTallasPorProductoEnBodega(@PathVariable Long id) {
		
		List<Talla> listaTallas = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			listaTallas = this.iTallaService.obtenerTallaPorProductoBodega(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener la talla con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(listaTallas == null) {
			mapa.put("mensaje", "El producto con ID " + id + "no tiene tallas registradas en bodega");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< List<Talla> >(listaTallas, HttpStatus.OK);
	}
	
	
}
