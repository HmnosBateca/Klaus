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

import com.Leather.models.entity.Color;
import com.Leather.models.entity.Talla;
import com.Leather.models.services.IColorService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ColorRestController {
	
	
	// inyección de dependencia
	@Autowired IColorService iColorService;

	
	/*
	 * El método listarColores() método obtiene todos los colores registrados en base de datos
	 * - Parámetros: ninguno
	 * - Retorna: Una lista de Colores
	*/
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@GetMapping("/colores")
	public List<Color> listarColores(){
		return iColorService.listarColores();
	}
	
	
	
	/*
	 *	El método listarColoresPaginado permite listar los colores usando paginación
	 *	- Parámetros: El paginador (tiene la información de la paginación)
	 *	- Retorna: un ResponseEntity con la lista paginada
	*/
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@GetMapping("/colores/pagina")
	public ResponseEntity<?> listarColoresPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by("nombre").ascending());
		return ResponseEntity.ok(iColorService.listarColoresPaginado(pag));
	}
	
	
	
	
	
	/*
	 * El metodo obtenerColorPorID permite obtener el color por su ID.
	 * - Parámetros: el ID
	 * - Retorna: Un ResponseEntity de tipo genérico 
	*/
	@GetMapping("/colores/{id}")
	public ResponseEntity<?> obtenerColorPorID(@PathVariable Long id) {
		
		Color color = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			color = this.iColorService.obtenerColorPorID(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el color con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(color == null) {
			mapa.put("mensaje", "La talla con ID " + id + "no se encuentra en base de datos");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< Color >(color, HttpStatus.OK);
	}
	
	
	@GetMapping("/colores/filtro/{nombre}")
	public ResponseEntity<?> buscarColorPornombre(@PathVariable String nombre){
		List<Color> listaColores = iColorService.buscarColorPorNombre(nombre);
		return ResponseEntity.ok(listaColores);
	}
	
		
	/*
	 * El método guardarColor permite guardar el Color en base de datos
	 * - Parámetros: El color a guardar en base de datos
	 * - Retorna: Un ResposeEntity con el resultado del proceso (un error o el Color creado)
	*/
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PostMapping("/colores")
	public ResponseEntity<?> guardarColor(@RequestBody Color color){
		
		Color colorNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		
		try {
			iColorService.guardarColor(color);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar el color " + color.getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El color " + color.getNombre() + " ha sido registrado exitosamente");
		mapa.put("color", colorNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
		
	}
		
	/*
	 * El método modificarColor obtiene el color con el ID especificado en la URL para luego establecer los
	 * nuevos valores con los que se han proporcionado en el color que viene como parámetro en el Body de la petición
	 * 
	 * Parámetros: 
	 * 			- El ID del color a modificar (URL de la petición)
	 * 			- El color con la nueva información (Body de la petición) 
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (el color modificado o el mensaje de error)
	*/	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PutMapping("/colores/{id}")
	public ResponseEntity<?> modificarColor(@PathVariable Long id, @RequestBody Color color){
		
		Color colorExistente = null;
		Color colorNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		colorExistente = iColorService.obtenerColorPorID(id);
		
		if(colorExistente == null) {
			mapa.put("mensaje", "El color con id "+color.getNombre()+" no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		
		try {
			colorExistente.setNombre(color.getNombre());
			colorExistente.setCodigoColor(color.getCodigoColor());
			colorNuevo = iColorService.guardarColor(colorExistente);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el color "+ color.getNombre());
		}
		
		mapa.put("mensaje", "El color "+ color.getNombre() +" ha sido modificado exitosamente");
		mapa.put("color", colorNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	/*
	 * El método eliminarColor obtiene el color con el ID especificado en la URL para luego
	 * eliminar el color de la base de datos
	 * 
	 * Parámetros: 
	 * 			- El ID del color a eliminar (URL de la petición)
	 * 
	 * Retorna: Un ResponseEntity con la repsuesta de la petición (el mensaje de étixo o error)
	*/
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@DeleteMapping("/colores/{id}")
	public ResponseEntity<?> eliminarColor(@PathVariable Long id){
		
		Color colorExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		colorExistente = iColorService.obtenerColorPorID(id);
		
		if(colorExistente == null) {
			mapa.put("mensaje","El color con id "+id + " no se encuentra regitrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		try{
			iColorService.eliminarColor(id);
		} catch(DataAccessException e) {
			mapa.put("mensaje","Ocurrió un error al eliminar el color "+colorExistente.getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "El color " +colorExistente.getNombre()+ " ha sido eliminado exitosamente");	
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
}
