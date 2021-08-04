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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Pieza;
import com.Leather.models.services.IPiezaService;



@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class PiezaRestController {

	@Autowired
	IPiezaService iPiezaService;
	
	
	@GetMapping("/pieza")
	public List<Pieza> listarPiezas(){
		return iPiezaService.listarPiezas();
	}
	
	
	@GetMapping("/pieza/{id}")
	public ResponseEntity<?> obtenerPiezaPorID(@PathVariable Long id) {
		
		Pieza pieza = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			pieza = this.iPiezaService.obtenerPiezaPorID(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener la pieza con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(pieza == null) {
			mapa.put("mensaje", "La pieza con ID " + id + " no se encuentra registrada");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< Pieza >(pieza, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/pieza/pagina")
	public ResponseEntity<?> listarPiezasPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(),Sort.by("nombre").ascending());
		return ResponseEntity.ok(iPiezaService.listarPiezasPaginado(pag));
	}
	
	
	
	
	@PostMapping("/pieza")
	public ResponseEntity<?> guardarPieza(@RequestBody Pieza pieza ){
				
		Map<String, Object> mapa = new HashMap<String, Object>();
		Pieza piezaNueva = null;
		
		
		try {
			piezaNueva = this.iPiezaService.guardarPieza(pieza);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ha ocurrido un error al guardar la pieza "+ pieza.getNombrePieza());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "La pieza " + pieza.getNombrePieza() +" ha sido registrada exitosamente");
		mapa.put("pieza", piezaNueva);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.CREATED);
		
	}
	
	
	
	
	
	@PutMapping("/pieza/{id}")
	public ResponseEntity<?> actualizarPieza(@PathVariable Long id, @RequestBody Pieza piezaFormulario){
		
		Pieza piezaExistente = this.iPiezaService.obtenerPiezaPorID(id);
		Pieza piezaNueva = null;
		Map<String,Object> mapa = new HashMap<>();
		
		
		if(piezaExistente == null) {
			mapa.put("mensaje","La pieza con id " + id + " no se encuentra registrada");
			return new ResponseEntity<  Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			piezaExistente.setNombrePieza(piezaFormulario.getNombrePieza());
			piezaExistente.setObservacion(piezaFormulario.getObservacion());
			piezaExistente.setColor(piezaFormulario.getColor());
			piezaExistente.setMaterial(piezaFormulario.getMaterial());
			piezaExistente.setProducto(piezaFormulario.getProducto());
			
			
			piezaNueva = iPiezaService.guardarPieza(piezaExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al modificar la pieza " + piezaExistente.getNombrePieza());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "La pieza " + piezaExistente.getNombrePieza() + " ha sido modificada exitosamente");
		mapa.put("pieza", piezaNueva);
		return new ResponseEntity< Map<String, Object>  > ( mapa,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/pieza/{id}")
	public ResponseEntity<?> eliminarPieza(@PathVariable Long id) {
		
		Pieza piezaExistente = null;
		Map<String, Object> mapa = new HashMap<>();
		
		piezaExistente = iPiezaService.obtenerPiezaPorID(id);
		
		if(piezaExistente == null) {
			mapa.put("mensaje", "La pieza con ID" + id + "no se encuentra registrada");
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			iPiezaService.eliminarPieza(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al eliminar la pieza "+ piezaExistente.getNombrePieza());
			mapa.put("error", e.getMessage()+" : "+e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje","La pieza " + piezaExistente.getNombrePieza() + " ha sido eliminada exitosamente");
		return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.OK);
		
	}
	
	
	
}
