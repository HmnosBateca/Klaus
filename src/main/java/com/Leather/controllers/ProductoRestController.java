package com.Leather.controllers;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.Leather.common.CommonRestController;

import com.Leather.models.entity.Producto;
import com.Leather.models.services.IBodegaInventarioService;
import com.Leather.models.services.IProductoService;



@CrossOrigin(origins = {"http://localhost:4200", "*"}, exposedHeaders = {"Access-Control-Expose-Headers", "Content-Disposition"})
@RequestMapping("/api/producto")
@RestController
public class ProductoRestController extends CommonRestController<Producto, IProductoService> {

	
	@Autowired
	IProductoService iProductoService;
		
	
	@GetMapping("/productoFoto/{idProducto}")
	public ResponseEntity<?> obtenerFotoProductoPorID(@PathVariable Long idProducto){
		
		
		Producto producto = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			producto = this.iProductoService.obtenerElementoPorID(idProducto);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el producto con ID " + idProducto);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(producto == null || producto.getFoto() == null) {
			mapa.put("mensaje", "El producto con ID " + idProducto + "no se encuentra registrado o no tiene imagen registrada");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		
		ByteArrayResource imagen = new ByteArrayResource(producto.getFoto());
		
		return ResponseEntity.ok()
				.header("Content-Disposition", producto.getNombreFoto()) // en el header envío el nombre del archivo para poderlo usar en el Front
				.contentType(MediaType.IMAGE_JPEG).body(imagen);
	}
	
	
	
	@PostMapping("/productoFoto")
	public ResponseEntity<?> guardarProductoConFoto(@Valid Producto producto, @RequestParam MultipartFile archivo ) throws IOException{	
		
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		if(!archivo.isEmpty()) {
			producto.setNombreFoto(archivo.getOriginalFilename());
			producto.setFoto(archivo.getBytes());
		}
		
		try {
			productoNuevo = iService.guardarElemento(producto);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar el elemento");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		mapa.put("mensaje", "Registro exitoso");
		mapa.put("elemento", productoNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);	
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto productoFormulario){
		
		Producto productoExistente = null;
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		productoExistente = iProductoService.obtenerElementoPorID(id);
		
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
			
			productoNuevo = iProductoService.guardarElemento(productoExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el producto "+ productoExistente.getNombre());
		}
		
		mapa.put("mensaje", "El producto "+ productoExistente.getNombre() +" ha sido modificado exitosamente");
		mapa.put("producto", productoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/productoFoto/{id}")
	public ResponseEntity<?> modificarColorConFoto(@PathVariable Long id, @Valid Producto productoFormulario, @RequestParam MultipartFile archivo) throws IOException{
		
		System.out.println("entró a modificar");
		
		Producto productoExistente = null;
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		productoExistente = iProductoService.obtenerElementoPorID(id);
		
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
			
			
			if(!archivo.isEmpty()) {
				productoExistente.setFoto(archivo.getBytes());
				productoExistente.setNombreFoto(productoFormulario.getNombreFoto());
			}
					
			
			productoNuevo = iProductoService.guardarElemento(productoExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el producto "+ productoExistente.getNombre());
		}
		
		mapa.put("mensaje", "El producto "+ productoExistente.getNombre() +" ha sido modificado exitosamente");
		mapa.put("producto", productoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/productoFotoNull/{id}")
	public ResponseEntity<?> modificarProductoConFotoNull(@PathVariable Long id, @RequestBody Producto productoFormulario){
		
		Producto productoExistente = null;
		Producto productoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		productoExistente = iProductoService.obtenerElementoPorID(id);
		
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
			
			productoExistente.setFoto(null);
			productoExistente.setNombreFoto(null);
			
			productoNuevo = iProductoService.guardarElemento(productoExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el producto "+ productoExistente.getNombre());
		}
		
		mapa.put("mensaje", "El producto "+ productoExistente.getNombre() +" ha sido modificado exitosamente");
		mapa.put("producto", productoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}

	
	
	
	
	
	@GetMapping("/bodega/pagina")
	public Page<Producto> ListarProductosBodegaInventario(Pageable paginador){
		return iProductoService.ListarProductosEnBodegaInventario(paginador);
	}

}
