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


import com.Leather.models.entity.TipoEnvio;
import com.Leather.models.services.ITipoEnvioService;
// import com.Leather.models.services.TipoEnvioServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200/ ","*" })
@RestController//Api Rest
@RequestMapping("/api")
public class TipoEnvioRestController {
	
	@Autowired//inyectamos
	ITipoEnvioService iTipoEnvioService;// vamos a la interface y obtenemos el listado de tipos de envio
	private Map<String, Object>mapa;
	
	@GetMapping("/TipoEnvio")
    public List<TipoEnvio>ListarTipoEnvio(){
    	return iTipoEnvioService.findAll();
    }
	
	
	@GetMapping("/TipoEnvio/{id}")
	public ResponseEntity<?> ListarTipoEnviosPorId(@PathVariable Long id){
		TipoEnvio tipoEnvio = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			tipoEnvio = iTipoEnvioService.findById(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if(tipoEnvio == null) {
			mapa.put("mensaje", "El Tipo Envio Id: ".concat(id.toString().concat(" no existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.NOT_FOUND);// Status 404
		}
		return new ResponseEntity<TipoEnvio>(tipoEnvio, HttpStatus.OK);
		
	}
	
	@GetMapping("/TipoEnvio/pagina")
	public Page<TipoEnvio> index(Pageable pageable) {
		return iTipoEnvioService.findAll(pageable);
	}
	
	@PostMapping("/TipoEnvio")
	public ResponseEntity<?> CrearTipoEnvio(@RequestBody TipoEnvio tipoEnvio) {
		TipoEnvio tipoEnvioCrear = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			tipoEnvioCrear = iTipoEnvioService.save(tipoEnvio);
		}catch(DataAccessException e){
			mapa.put("mensaje","Error al insertar en la Base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 mapa.put("mensaje", "Tipo envio se a creado con éxito");
		 mapa.put("tipoEnvio", tipoEnvioCrear);
		 return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	@PutMapping("/TipoEnvio/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Editar(@RequestBody TipoEnvio tipoEnvio, @PathVariable Long id) {
		
		TipoEnvio tipoEnvioActual = iTipoEnvioService.findById(id);
		TipoEnvio tipoEnvioEditar = null;
		Map<String, Object>mapa = new HashMap<>();
		if(tipoEnvioActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, el Tipo Envio ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
			
		}
		try {
		tipoEnvioActual.setNombre(tipoEnvio.getNombre());
		tipoEnvioActual.setDescripcion(tipoEnvio.getDescripcion());
		tipoEnvioEditar = iTipoEnvioService.save(tipoEnvioActual);
		}catch(DataAccessException e){
			mapa.put("mensaje", "Error al actualizar Tipo Envio en la base de datos.");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR); // status 404
		}
		mapa.put("mensaje", "El Tipo Envio ha sido actualizado con éxito!");
		mapa.put("tipoEnvio", tipoEnvioEditar);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/TipoEnvio/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object>mapa = new HashMap<>();
		try {
			iTipoEnvioService.delete(id);
		}catch(DataAccessException e){
			mapa.put("mensaje", "Error al eliminar Tipo Envio de la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 404
		}
		mapa.put("mensaje", "El Tipo Envio ha sido eliminando con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
	}
}
