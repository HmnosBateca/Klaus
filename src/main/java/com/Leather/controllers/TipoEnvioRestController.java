package com.Leather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	// private Map<String, Object> mapa;
	
	@GetMapping("/TipoEnvios")
    public List<TipoEnvio>ListarTipoEnvio(){
    	return iTipoEnvioService.findAll();
    }
	
	@GetMapping("/TipoEnvios/{id}")
	public TipoEnvio ListarTipoEnviosPorId(@PathVariable Long id){
		return iTipoEnvioService.findById(id);
	}
	
	@PostMapping("/TipoEnvios")
	public TipoEnvio CrearTipoEnvio(@RequestBody TipoEnvio tipoEnvio) {
		return iTipoEnvioService.save(tipoEnvio);
	}
	
	@PutMapping("/TipoEnvios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoEnvio Editar(@RequestBody TipoEnvio tipoEnvio, @PathVariable Long id) {
		TipoEnvio tipoEnvioActual = iTipoEnvioService.findById(id);
		tipoEnvioActual.setNombre(tipoEnvio.getNombre());
		tipoEnvioActual.setDescripcion(tipoEnvio.getDescripcion());
		return iTipoEnvioService.save(tipoEnvioActual);
		
	}
	
	@DeleteMapping("/TipoEnvios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		iTipoEnvioService.delete(id);
	}
	
}
