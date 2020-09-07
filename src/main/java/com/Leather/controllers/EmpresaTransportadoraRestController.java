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

import com.Leather.models.entity.EmpresaTransportadora;
import com.Leather.models.entity.TipoEnvio;
import com.Leather.models.services.IEmpresaTransportadoraService;

@CrossOrigin(origins = {"http://localhost:4200/","*"})
@RestController // Es API Rest
@RequestMapping("/api")
public class EmpresaTransportadoraRestController {
	
	@Autowired// inyectamos
	public IEmpresaTransportadoraService iEmpresaTransportadoraService;
	private Map<String, Object> mapa;
	

	@GetMapping("/EmpresaTransportadora")
    public List<EmpresaTransportadora>ListarEmpresaTransportadora(){
    	return iEmpresaTransportadoraService.findAll();
    }
	
	@GetMapping("/EmpresaTransportadora/{id}")
	public ResponseEntity<?> ListarEmpresaTransportadoraPorId(@PathVariable Long id){
		EmpresaTransportadora empresaTransportadora = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			empresaTransportadora = iEmpresaTransportadoraService.findById(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Error al realizar la consulta en la Base de Datos");
			mapa.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 500
		}
		if(empresaTransportadora == null) {
			mapa.put("mensaje", "La Empresa Transportadora Id: ".concat(id.toString().concat(" no existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.NOT_FOUND);// Status 404
		}
		return new ResponseEntity<EmpresaTransportadora>(empresaTransportadora, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/EmpresaTransportadora")
	public ResponseEntity<?> CrearEmpresaTransportadora(@RequestBody EmpresaTransportadora empresaTransportadora) {
		EmpresaTransportadora empresaTransportadoraCrear = null;
		Map<String, Object>mapa = new HashMap<>();
		try {
			empresaTransportadoraCrear = iEmpresaTransportadoraService.Save(empresaTransportadora);
		}catch(DataAccessException e){
			mapa.put("mensaje","Error al insertar en la Base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 mapa.put("mensaje", "Tipo envio se a creado con éxito");
		 mapa.put("tipoenvio", empresaTransportadoraCrear);
		 return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.CREATED);
	}
	
	@GetMapping("/EmpresaTransportadora/pagina")
	public Page<EmpresaTransportadora> index(Pageable pageable) {
		return iEmpresaTransportadoraService.findAll(pageable);
	}
	
	@PutMapping("/EmpresaTransportadora/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> Editar(@RequestBody EmpresaTransportadora Empresatransportadora, @PathVariable Long id) {
		
		EmpresaTransportadora empresaTransportadoraActual = iEmpresaTransportadoraService.findById(id);
		EmpresaTransportadora empresaTransportadoraEditar = null;
		Map<String, Object>mapa = new HashMap<>();
		if(empresaTransportadoraActual == null) {
			mapa.put("mensaje", "Error: no se puede editar, La Empresa Transportadora ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);// status 404
			
		}
		try {
		empresaTransportadoraActual.setNombre(Empresatransportadora.getNombre());
		empresaTransportadoraActual.setDescripcion(Empresatransportadora.getDescripcion());
		empresaTransportadoraEditar = iEmpresaTransportadoraService.Save(empresaTransportadoraActual);
		}catch(DataAccessException e){
			mapa.put("mensaje", "Error al actualizar Empresa Tranportadora en la base de datos.");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR); // status 404
		}
		mapa.put("mensaje", "La Empresa Transportadora ha sido actualizado con éxito!");
		mapa.put("Empresa Transportadora", empresaTransportadoraEditar);
		return new ResponseEntity<Map<String, Object>>(mapa,HttpStatus.ACCEPTED);
	}
		
	@DeleteMapping("/EmpresaTransportadora/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object>mapa = new HashMap<>();
		try {
			iEmpresaTransportadoraService.delete(id);
		}catch(DataAccessException e){
			mapa.put("mensaje", "Error al eliminar Empresa Transportadora de la base de datos");
			mapa.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);// status 404
		}
		mapa.put("mensaje", "La Empresa Transportadora ha sido eliminando con éxito!");
		return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
	}
	
	
}
