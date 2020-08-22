package com.Leather.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import com.Leather.models.entity.ProductoPieza;
import com.Leather.models.services.IProductoPiezaService;




@CrossOrigin(origins = {"http://localhost:4200","*"})
@RequestMapping("/api")
@RestController
public class ProductoPiezaRestController {
	
	
	
	@Autowired
	IProductoPiezaService iProductoPiezaService;
	
	
	@GetMapping("/productoPieza")
	public List<ProductoPieza> listarProductoPieza(){
		return iProductoPiezaService.listarProductoPieza();
	}
	
	
	@GetMapping("/productoPieza/pagina")
	public ResponseEntity<?>listarProductoPiezaPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Direction.ASC, "producto"));
		return ResponseEntity.ok(this.iProductoPiezaService.obtenerProductoPiezaPaginado(pag));
	}
	
	
	
	
	
	@GetMapping("/productoPieza/{id}")
	public ResponseEntity<?> obtenerProductoPiezaPorID(@PathVariable Long id) {
		
		ProductoPieza productoPieza = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			productoPieza = this.iProductoPiezaService.obtenerProductoPiezaPorID(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el producto-pieza con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(productoPieza == null) {
			mapa.put("mensaje", "El producto-pieza con ID " + id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< ProductoPieza >(productoPieza, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/productoPieza")
	public ResponseEntity<?> guardarProductoPieza(@RequestBody ProductoPieza productoPieza ){
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		ProductoPieza productoPiezaNuevo = null;
		
		
		try {
			productoPiezaNuevo = this.iProductoPiezaService.agregarProductoPieza(productoPieza);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ha ocurrido un error al guardar la pieza "+ productoPieza.getPieza().getNombre() + " para el producto " + productoPieza.getProducto().getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "La pieza " + productoPiezaNuevo.getPieza().getNombre() + " para el producto "+ productoPiezaNuevo.getProducto().getNombre() +" ha sido registrada exitosamente");
		mapa.put("pieza", productoPiezaNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.CREATED);
		
	}
	
	
	
	
	
	
	@PutMapping("/productoPieza/{id}")
	public ResponseEntity<?> actualizarProductoPieza(@PathVariable Long id, @RequestBody ProductoPieza productoPiezaFormulario){
		
		ProductoPieza productoPiezaExistente = this.iProductoPiezaService.obtenerProductoPiezaPorID(id);
		ProductoPieza productoPiezaNuevo = null;
		Map<String,Object> mapa = new HashMap<>();
		
		
		if(productoPiezaExistente == null) {
			mapa.put("mensaje","El producto-pieza con id " + id + " no se encuentra registrado");
			return new ResponseEntity<  Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			productoPiezaExistente.setId(productoPiezaFormulario.getId());
			productoPiezaExistente.setPieza(productoPiezaFormulario.getPieza());
			productoPiezaExistente.setProducto(productoPiezaFormulario.getProducto());
			
			productoPiezaNuevo = iProductoPiezaService.agregarProductoPieza(productoPiezaExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al modificar el producto-pieza con ID " + productoPiezaExistente.getId());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El producto-pieza " + productoPiezaExistente.getId() + " ha sido modificado exitosamente");
		mapa.put("pieza", productoPiezaNuevo);
		return new ResponseEntity< Map<String, Object>  > ( mapa,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	@DeleteMapping("/productoPieza/{id}")
	public ResponseEntity<?> eliminarProductoPieza(@PathVariable Long id) {
		
		ProductoPieza productoPiezaExistente = null;
		Map<String, Object> mapa = new HashMap<>();
		
		productoPiezaExistente = iProductoPiezaService.obtenerProductoPiezaPorID(id);
		
		if(productoPiezaExistente == null) {
			mapa.put("mensaje", "El producto-pieza con ID" + id + "no se encuentra registrado");
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			iProductoPiezaService.eliminarProductoPieza(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al eliminar el producto-pieza con ID "+ productoPiezaExistente.getId());
			mapa.put("error", e.getMessage()+" : "+e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje","El producto-pieza con ID" + productoPiezaExistente.getId() + " ha sido eliminado exitosamente");
		return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.OK);
		
	}
	
	
	

}
