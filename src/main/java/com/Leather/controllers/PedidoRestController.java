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
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.Leather.models.entity.Pedido;
import com.Leather.models.services.IPedidoService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController //api Rest
@RequestMapping("/api")// Generamos url

public class PedidoRestController {
	
	@Autowired
	private IPedidoService iPedidoService;
	private Map<String, Object>mapa;
	
	// Listar
	@GetMapping("/Pedido") // end poing de Get
	public List<Pedido> ListarPedido() {
		return iPedidoService.ListarPedido();// metodo
	}
	
	// Listar Paginado
	@GetMapping("/Pedido/pagina")
	public Page<Pedido> Paginar(Pageable pageable) {
		return iPedidoService.PaginarPedido(pageable);
	}
	
	// Obtener Por Id
	@GetMapping("/Pedido/{id}") // Get por id
	public ResponseEntity<?> show(@PathVariable Long id) {
		Pedido pedido = null;
		Map<String, Object> mapa = new HashMap<>();
		try {// si hay errror
			pedido = iPedidoService.ObtenerPedidoPorId(id);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if (pedido == null) {
			mapa.put("mensaje", "El Pedido ID:".concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
		}
		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}
	
	// Post Agregar 
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PostMapping("/Pedido")
	public ResponseEntity<?> create(@RequestBody Pedido pedido) {
		Pedido pedidoNuevo = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			pedidoNuevo = iPedidoService.GuardarPedido(pedido);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar insert en la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
																												 
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		mapa.put("mensaje", "El Pedido ha sido creado con éxito!");
		mapa.put("pedido", pedidoNuevo);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);

	}

	// Actualizar
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PutMapping("/Pedido/{id}") // con la id obtenemos de la base de datos y actualizamos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Pedido pedido, @PathVariable Long id) {// modificado
		Pedido pedidoActual = null;
		Map<String, Object> mapa = new HashMap<>();
		pedidoActual = iPedidoService.ObtenerPedidoPorId(id);// Pedido por id
		if (pedidoActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, el Pedido iD:"
					.concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// Estatus 404
		}
		try {
			// pedidoActual.setFechaPedido(pedido.getFechaPedido());
			// pedidoActual.setHoraPedido(pedido.getHoraPedido());
			pedidoActual.setValorIva(pedido.getValorIva());
			pedidoActual.setValorFinalVenta(pedido.getValorFinalVenta());
			pedidoActual.setObservaciones(pedido.getObservaciones());
			pedidoActual = iPedidoService.GuardarPedido(pedidoActual);// persistir o guardar
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al actualizar Pedido en la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// Staus
		}
		mapa.put("mensaje", "El Pedido ha sido actualizado con éxitos!");
		mapa.put("pedido", pedidoActual);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	// Eliminar
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@DeleteMapping("/Pedido/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mapa = new HashMap<>();
		try {
			iPedidoService.EliminarPedido(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al eliminar de la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);//status 404
			
		}
		mapa.put("mensaje", "El Pedido ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
	}
}

