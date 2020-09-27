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

import com.Leather.models.entity.ReferenciaProducto;
import com.Leather.models.services.IReferenciaProductoService;

@CrossOrigin(origins = {"http://localhost:4200/ ","*" })
@RestController//Api Rest
@RequestMapping("/api")

public class ReferenciaProductoRestController {
	
	@Autowired
	IReferenciaProductoService iReferenciaProductoService;
	private Map<String, Object> mapa;
	
	// Listar Referencia Producto
	@GetMapping("/ReferenciaProducto")
    public List<ReferenciaProducto>ListarReferenciaProducto(){
    	return iReferenciaProductoService.ListarReferenciaProducto();
    }
	
	// Listar Paginado Referencia Producto
	@GetMapping("/ReferenciaProducto/pagina")
	public Page<ReferenciaProducto> ListarPaginadoReferenciaProducto(Pageable paginador) {
		return iReferenciaProductoService.ListarPaginadoReferenciaProducto(paginador);
	}
	
	// Obtener Por Id
	@GetMapping("/ReferenciaProducto/{id}") // Get por id
	public ResponseEntity<?> show(@PathVariable Long id) {
		ReferenciaProducto referenciaProducto = null;
		Map<String, Object> mapa = new HashMap<>();
	try {// si hay errror
		referenciaProducto = iReferenciaProductoService.ListarReferenciaProductoPorId(id);
	} catch (DataAccessException e) {
		mapa.put("mensaje", "Error al realizar la consulta en base de datos");
		mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
	}
	if (referenciaProducto == null) {
		mapa.put("mensaje", "La Referencia Producto ID:".concat(id.toString().concat("No existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
	}
		return new ResponseEntity<ReferenciaProducto>(referenciaProducto, HttpStatus.OK);
	}
	
	// Post Agregar 
	@PostMapping("/ReferenciaProducto")
	public ResponseEntity<?> create(@RequestBody ReferenciaProducto referenciaProducto) {
		ReferenciaProducto referenciaProductoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
	try {
		referenciaProductoNuevo = iReferenciaProductoService.GuardarReferenciaProducto(referenciaProducto);
	} catch (DataAccessException e) {
		mapa.put("mensaje", "Error al realizar insert en la base de datos");
		mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
																													 
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		mapa.put("mensaje", "La Referencia Producto ha sido creado con éxito!");
		mapa.put("referenciaProducto", referenciaProductoNuevo);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	// Actualizar
	@PutMapping("/ReferenciaProducto/{id}") // con la id obtenemos de la base de datos y actualizamos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody ReferenciaProducto referenciaProducto, @PathVariable Long id) {// modificado
		ReferenciaProducto referenciaProductoActual = null;
		Map<String, Object> mapa = new HashMap<>();
		referenciaProductoActual = iReferenciaProductoService.ListarReferenciaProductoPorId(id);// Pedido por id
		if (referenciaProductoActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, la Referencia Producto iD:".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// Estatus 404
		}
	try {
		    referenciaProducto.setId(referenciaProducto.getId());
			referenciaProducto.setProducto(referenciaProducto.getProducto());;
			referenciaProducto.setTalla(referenciaProducto.getTalla());
			referenciaProductoActual = iReferenciaProductoService.GuardarReferenciaProducto(referenciaProductoActual);// persistir o guardar
	} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al actualizar la Referencia Producto en la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// Staus
	}
		mapa.put("mensaje", "La Referencia Producto ha sido actualizado con éxitos!");
		mapa.put("referenciaProducto", referenciaProductoActual);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	// Eliminar
	@DeleteMapping("/referenciaProducto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mapa = new HashMap<>();
		try {
			iReferenciaProductoService.EliminarReferenciaProducto(id);;
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al eliminar de la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);//status 404	
		}
			mapa.put("mensaje", "La Referencia Producto ha sido eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
		}
	
}
