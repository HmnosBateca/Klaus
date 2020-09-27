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
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.BodegaInventario;
import com.Leather.models.services.IBodegaInventarioService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController// Api Rest
@RequestMapping("/api")// url
public class BodegaInventarioRestController {
	
	@Autowired
	private IBodegaInventarioService iBodegaInventarioService;
	private Map<String, Object>mapa;
	
	@GetMapping("/BodegaInventario")
	public List<BodegaInventario> ListarBodegaInventario(){
		return iBodegaInventarioService.ListarBodegaInventario();
	}
		
	@GetMapping("/BodegaInventario/pagina")
	public Page<BodegaInventario> ListarBodegaInventarioPaginado(Pageable paginador) {
		return iBodegaInventarioService.ListarBodegaInventarioPaginado(paginador);
	}

	
	// Listar Por Id
	@GetMapping("/BodegaInventario/{id}")
	public ResponseEntity<?> ObtenerBodegaInventarioPorID(@PathVariable Long id){
		BodegaInventario bodegaInventario = null;
		Map<String, Object> mapa = new HashMap<>();
		try {
			bodegaInventario = this.iBodegaInventarioService.ObtenerBodegaInventarioPorID(id);
		}catch(DataAccessException e) {
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener la Bodega-Inventario con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(bodegaInventario == null) {
			mapa.put("mensaje", "La Bodega-Inventario con ID " + id+ "no se encuentra registrado");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity< BodegaInventario >(bodegaInventario, HttpStatus.OK);
	}
	
	// Guardar Bodega Inventario
	@PostMapping("/BodegaInventario")
	public ResponseEntity<?> guardarProducto(@RequestBody BodegaInventario bodegaInventario){
		BodegaInventario bodegaInventarioNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		try {
			bodegaInventarioNuevo = iBodegaInventarioService.GuardarBodegaInventario(bodegaInventario);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al registrar la Bodega-Inventario en la base de datos");
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity< Map<String, Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		mapa.put("mensaje", "La Bodega-Inventario " + bodegaInventario.getId() + " ha sido registrado exitosamente");
		mapa.put("bodegaInventario", bodegaInventarioNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.CREATED);	
	}
	
	// Actualizar Bodega inventario
	@PutMapping("/BodegaInventario/{id}")
	public ResponseEntity<?> modificarColor(@PathVariable Long id, @RequestBody BodegaInventario bodegaInventarioFormulario){
		
		BodegaInventario bodegaInventarioExistente = null;
		BodegaInventario bodegaInventarioNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		bodegaInventarioExistente = iBodegaInventarioService.ObtenerBodegaInventarioPorID(id);
		if(bodegaInventarioExistente == null) {
			mapa.put("mensaje", "La Bodega-Inventario con id "+ id +" no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		try {
			bodegaInventarioExistente.setId(bodegaInventarioFormulario.getId());
			bodegaInventarioExistente.setReferencia(bodegaInventarioFormulario.getReferencia());
			bodegaInventarioExistente.setCantidad(bodegaInventarioFormulario.getCantidad());
			bodegaInventarioExistente.setEstadoDescuento(bodegaInventarioFormulario.getEstadoDescuento());
			bodegaInventarioExistente.setDescuento(bodegaInventarioFormulario.getDescuento());
			bodegaInventarioNuevo = iBodegaInventarioService.GuardarBodegaInventario(bodegaInventarioExistente);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar Bodega-Inventario "+ bodegaInventarioExistente.getId());
		}
		mapa.put("mensaje", "La Bodega-Inventario "+ bodegaInventarioExistente.getId() +" ha sido modificado exitosamente");
		mapa.put("bodegaInventario", bodegaInventarioNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	@DeleteMapping("/BodegaInventario/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
		BodegaInventario bodegaInventarioExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		bodegaInventarioExistente = iBodegaInventarioService.ObtenerBodegaInventarioPorID(id);
		if(bodegaInventarioExistente == null) {
			mapa.put("mensaje","El producto con id "+ id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		try{
			iBodegaInventarioService.EliminarBodegaInventario(id);
		} catch(DataAccessException e) {
			mapa.put("mensaje","Ocurrió un error al eliminar la Bodega-Inventario "+ bodegaInventarioExistente.getId());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		mapa.put("mensaje", "La Bodega-Inventario " +bodegaInventarioExistente.getId()+ " ha sido eliminado exitosamente");	
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
}

