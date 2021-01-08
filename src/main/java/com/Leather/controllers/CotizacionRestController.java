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

import com.Leather.models.entity.Cotizacion;
import com.Leather.models.entity.Pieza;
import com.Leather.models.services.ICotizacionService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController //api Rest
@RequestMapping("/api")// Generamos url
public class CotizacionRestController {
	
	@Autowired
	private ICotizacionService iCotizacionService;
	private Map<String, Object>mapa;
	
	// Listar
	@GetMapping("/Cotizacion") // end poing de Get
	public List<Cotizacion> ListarCotizacion() {
		return iCotizacionService.ListarCotizacion();// metodo
	}
	
	// Listar Paginado
	@GetMapping("/Cotizacion/pagina")
	public Page<Cotizacion> PaginarCotizacion(Pageable paginador) {
		return iCotizacionService.ListarCotizacionPaginado(paginador);
	}
	
	// Obtener Por Id
	@GetMapping("/Cotizacion/{id}") // Get por id
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cotizacion cotizacion = null;
		Map<String, Object> mapa = new HashMap<>();
	try {// si hay errror
		cotizacion = iCotizacionService.ObtenerCotizacionPorId(id);
	} catch (DataAccessException e) {
		mapa.put("mensaje", "Error al realizar la consulta en base de datos");
		mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
	}
	if (cotizacion == null) {
		mapa.put("mensaje", "La Cotizacion ID:".concat(id.toString().concat("No existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
	}
		return new ResponseEntity<Cotizacion>(cotizacion, HttpStatus.OK);
	}

	// Post Agregar 
	@PostMapping("/Cotizacion")
	public ResponseEntity<?> create(@RequestBody Cotizacion cotizacion) {
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		Cotizacion cotizacionNuevo = null;
	try {
		cotizacionNuevo = iCotizacionService.GuardarCotizacion(cotizacion);
	} catch (DataAccessException e) {
		mapa.put("mensaje", "Error al realizar insert en la base de datos");
		mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
																												 
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		mapa.put("mensaje", "La Cotizacion ha sido creado con éxito!");
		mapa.put("cotizacion", cotizacionNuevo);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
		
	// Actualizar
	@PutMapping("/cotizacion/{id}") // con la id obtenemos de la base de datos y actualizamos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cotizacion cotizacion, @PathVariable Long id) {// modificado
		Cotizacion cotizacionActual = null;
		Map<String, Object> mapa = new HashMap<>();
		cotizacionActual = iCotizacionService.ObtenerCotizacionPorId(id);// Pedido por id
		if (cotizacionActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, la Cotizacion iD:".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// Estatus 404
		}
		try {
				cotizacion.setCantidad(cotizacion.getCantidad());
				cotizacion.setDescuento(cotizacion.getDescuento());
				cotizacion.setImporte(cotizacion.getImporte());
				cotizacionActual = iCotizacionService.GuardarCotizacion(cotizacionActual);// persistir o guardar
			} catch (DataAccessException e) {
				mapa.put("mensaje", "Error al actualizar la Cotizacion en la base de datos");
				mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
				return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// Staus
			}
			mapa.put("mensaje", "La Cotizacion ha sido actualizado con éxitos!");
			mapa.put("cotizacion", cotizacionActual);
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
		}
	// Eliminar
	@DeleteMapping("/Cotizacion/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mapa = new HashMap<>();
		try {
			iCotizacionService.EliminarCotizacion(id);;
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al eliminar de la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);//status 404	
		}
			mapa.put("mensaje", "La Cotizacion ha sido eliminado con éxito!");
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
		}
}