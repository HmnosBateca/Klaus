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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.Leather.models.entity.Ciudad;
import com.Leather.models.entity.Cliente;
import com.Leather.models.services.ICiudadService;
import com.Leather.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController // api rest
@RequestMapping("/api") // generamos la url

public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;// listado de clientes
	private Map<String, Object>mapa;

	@GetMapping("/clientes") // listar peticion Get url generamos empoing de la peticion
	public List<Cliente> index() {
		return clienteService.findAll();// metodo
	}

	@GetMapping("/clientes/pagina")
	public Page<Cliente> index(Pageable pageable) {
		return clienteService.findAll(pageable);
	}

	@GetMapping("/clientes/{id}") // Get por id
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> mapa = new HashMap<>();
		try {// si hay errror
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if (cliente == null) {
			mapa.put("mensaje", "El cliente ID:".concat(id.toString().concat("No existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Post Agregar Cliente
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String, Object> mapa = new HashMap<>();
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al realizar insert en la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));// por que
																											// ocurrio
																											// el error
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		mapa.put("mensaje", "El cliente ha sido creado con éxito!");
		mapa.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);

	}

	@PutMapping("/clientes/{id}") // con la id obtenemos de la base de datos y actualizamos
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {// un cliente ya modificado
		Cliente clienteActual = null;
		Map<String, Object> mapa = new HashMap<>();
		clienteActual = clienteService.findById(id);// obtenemos el ciente por id
		if (clienteActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, el cliente iD:"
					.concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// Estatus 404
		}
		try {
			clienteActual.setDocumento(cliente.getDocumento());
			clienteActual.setNombres(cliente.getNombres());
			clienteActual.setApellidos(cliente.getApellidos());
			clienteActual.setNumero_contacto(cliente.getNumero_contacto());
			clienteActual.setDireccion(cliente.getDireccion());
			clienteActual.setCorreo(cliente.getCorreo());
			clienteActual.setCodigo_postal(cliente.getCodigo_postal());
			clienteActual.setCiudad(cliente.getCiudad());
			clienteActual = clienteService.save(clienteActual);// persistir o guardar
		} catch (DataAccessException e) {
			mapa.put("mensaje", "Error al actualizar cliente en la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));// por que ocurrio el error
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// Staus
		}
		mapa.put("mensaje", "El cliente ha sido actualizado con éxitos!");
		mapa.put("cliente", clienteActual);
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mapa = new HashMap<>();
		try {
			clienteService.delete(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al eliminar de la base de datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);//status 404
			
		}
		mapa.put("mensaje", "El cliente ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
		
	}
}
