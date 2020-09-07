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

import com.Leather.models.entity.EnvioCiudad;
import com.Leather.models.entity.TipoEnvio;
import com.Leather.models.services.IEnvioCiudadService;

@CrossOrigin(origins = {"http://localhost:4200/ ","*" })
@RestController//Api Rest
@RequestMapping("/api")
public class EnvioCiudadRestController {

	@Autowired
	IEnvioCiudadService iEnvioCiudadService;
	private Map<String, Object> mapa;
	
	@GetMapping("/EnvioCiudad")
    public List<EnvioCiudad>listarEnvioCiudad(){
    	return iEnvioCiudadService.findAll();
    }
	
	@GetMapping("/EnvioCiudad/pagina")
	public Page<EnvioCiudad> index(Pageable pageable) {
		return iEnvioCiudadService.findAll(pageable);
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/EnvioCiudad/{id}")
	public ResponseEntity<?> ListarEnvioCiudadPorId(@PathVariable Long id){
		EnvioCiudad enviociudad = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			enviociudad = iEnvioCiudadService.findById(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if(enviociudad == null) {
			mapa.put("mensaje", "El Envio Ciudad Id: ".concat(id.toString().concat(" no existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.NOT_FOUND);// Status 404
		}
		return new ResponseEntity<EnvioCiudad>(enviociudad, HttpStatus.OK);
	}
	
	@PostMapping("/EnvioCiudad")
	public ResponseEntity<?> CrearEnvioCiudad(@RequestBody EnvioCiudad envioCiudad) {
		EnvioCiudad enviociudadcrear = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			enviociudadcrear = iEnvioCiudadService.save(envioCiudad);
		}catch(DataAccessException e){
			mapa.put("mensaje","Error al insertar en la Base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 mapa.put("mensaje", "Envio Ciudad creado con éxito");
		 mapa.put("enviociudad", enviociudadcrear);
		 return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/EnvioCiudad/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Editar(@RequestBody EnvioCiudad envioCiudad, @PathVariable Long id) {
		
		EnvioCiudad envioCiudadActual = iEnvioCiudadService.findById(id);
		EnvioCiudad envioCiudadEditar = null;
		Map<String, Object>mapa = new HashMap<>();
		if(envioCiudadActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, el Envio Ciudad ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
		}
		try {
				envioCiudadActual.setTipoEnvio(envioCiudad.getTipoEnvio());
				envioCiudadActual.setCiudad(envioCiudad.getCiudad());
				envioCiudadActual.setValorEnvio(envioCiudad.getValorEnvio());
				envioCiudadEditar = iEnvioCiudadService.save(envioCiudadActual);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al actualizar Envio Ciudad en la base de datos ");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
						
		mapa.put("mensaje", "El Envio Ciudad ha sido actualizado con éxito!");
		mapa.put("tipo envio", envioCiudadEditar);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/EnvioCiudad/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object>mapa = new HashMap<>();
		try {
			iEnvioCiudadService.delete(id);
		}catch(DataAccessException e){
			mapa.put("mensaje", "Error al eliminar Envio Ciudad de la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 404
		}
		mapa.put("mensaje", "El Envio Ciudad ha sido eliminando con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
	}
}
