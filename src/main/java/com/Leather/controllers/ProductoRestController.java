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

import com.Leather.models.entity.Color;
import com.Leather.models.entity.Producto;
import com.Leather.models.services.IProductoService;


@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/api")
@RestController
public class ProductoRestController {

	
	@Autowired
	IProductoService iProductoService;
	
	
	
	@GetMapping("/producto")
	public List<Producto> listarProductos(){
		return iProductoService.listarProductos();
	}
	
	
	
	
	@GetMapping("/producto/pagina")
	public ResponseEntity<?> listarProductosPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(), Sort.by(Direction.ASC, "nombre"));
		return ResponseEntity.ok(iProductoService.listarProductosPaginado(pag));
	}
	
	
	
	
	
	@GetMapping("/producto/{idProducto}")
	public ResponseEntity<?> obtenerProductoPorID(@PathVariable Long idProducto){
		
		
		Producto producto = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			producto = this.iProductoService.obtenerProductoPorID(idProducto);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el producto con ID " + idProducto);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(producto == null) {
			mapa.put("mensaje", "El producto con ID " + idProducto + "no se encuentra registrado");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< Producto >(producto, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	@PostMapping("/producto")
	public ResponseEntity<?> guardarProducto(@RequestBody Producto producto){
		
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		
		try {
			productoNuevo = iProductoService.guardarProducto(producto);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar el producto " + producto.getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El producto " + producto.getNombre() + " ha sido registrado exitosamente");
		mapa.put("producto", productoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> modificarColor(@PathVariable Long id, @RequestBody Producto productoFormulario){
		
		Producto productoExistente = null;
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		productoExistente = iProductoService.obtenerProductoPorID(id);
		
		if(productoExistente == null) {
			mapa.put("mensaje", "El producto con id "+ id +" no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		
		try {
			productoExistente.setId(productoFormulario.getId());
			productoExistente.setNombre(productoFormulario.getNombre());
			productoExistente.setReferencia(productoFormulario.getReferencia());
			productoExistente.setCosto(productoFormulario.getCosto());
			productoExistente.setPrecioVenta(productoFormulario.getPrecioVenta());
			productoExistente.setActivo(productoFormulario.getActivo());
			productoExistente.setPiezas(productoFormulario.getPiezas());
			
			productoNuevo = iProductoService.guardarProducto(productoExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el producto "+ productoExistente.getNombre());
		}
		
		mapa.put("mensaje", "El producto "+ productoExistente.getNombre() +" ha sido modificado exitosamente");
		mapa.put("producto", productoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
		
		Producto productoExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		productoExistente = iProductoService.obtenerProductoPorID(id);
		
		if(productoExistente == null) {
			mapa.put("mensaje","El producto con id "+ id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		try{
			iProductoService.eliminarProducto(id);
		} catch(DataAccessException e) {
			mapa.put("mensaje","Ocurrió un error al eliminar el producto "+ productoExistente.getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "El producto " +productoExistente.getNombre()+ " ha sido eliminado exitosamente");	
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	
	
}
