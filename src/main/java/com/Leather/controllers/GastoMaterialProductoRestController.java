package com.Leather.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.common.CommonRestController;
import com.Leather.models.entity.GastoMaterialProducto;
import com.Leather.models.services.IGastoMaterialProductoService;

@RestController
@RequestMapping("/api/GastoMaterialProducto")
@CrossOrigin(origins = {"http://localhost:4200","*"})
public class GastoMaterialProductoRestController extends CommonRestController<GastoMaterialProducto, IGastoMaterialProductoService>{

	
	@Autowired
	IGastoMaterialProductoService iGastoMaterialProductoService;
	
	
	@GetMapping("/Talla/{idTalla}/TipoTalla/{idTipoTalla}/pieza/{idPieza}")
	public ResponseEntity<?> ObtenerGastoPorTallaTipoTallaPieza(@PathVariable Long idTalla, @PathVariable Long idTipoTalla, @PathVariable Long idPieza){
		
		GastoMaterialProducto gastoMaterial = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			gastoMaterial = (GastoMaterialProducto) super.iService.obtenerGastoMaterialPorTallaTipoTalla(idTalla, idTipoTalla, idPieza);
		}catch(DataAccessException e) {
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el gasto material");
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(gastoMaterial == null) {
			// si es nulo es porque el gasto no está registrado. Muy probablemente porque esté en el formulario listo para ser agregado
			// es importante enviarlo nulo, pues en el Front se va a validar
			mapa.put("mensaje", "El gasto no se encuentra registrado");
			mapa.put("gastoMaterial",gastoMaterial);
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
		}
		
		mapa.put("gastoMaterial",gastoMaterial);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/Producto/{id}")
	public ResponseEntity<?> ObtenerGastoPorProducto(@PathVariable Long id){
		
		List<GastoMaterialProducto> gastoMaterial = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			gastoMaterial = (List<GastoMaterialProducto>) super.iService.obtenerGastoMaterialPorProducto(id);
		}catch(DataAccessException e) {
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el gasto material");
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(gastoMaterial == null) {
			mapa.put("mensaje", "El gasto no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
		
		mapa.put("listaGastoMaterial",gastoMaterial);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarGastoMaterialProducto(@PathVariable Long id, @RequestBody GastoMaterialProducto gastoMaterialFormulario){

		GastoMaterialProducto gastoMaterialProductoExistente;
		GastoMaterialProducto gastoMaterialProductoModificado;
		
		Map<String, Object> mapa = new HashMap<>();
		
		gastoMaterialProductoExistente = this.iGastoMaterialProductoService.obtenerElementoPorID(id);
		
		if(gastoMaterialProductoExistente == null) {
			mapa.put("mensaje", "El elemento no se encuentra registrado");
			return new ResponseEntity<Map<String,Object>>(mapa, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			gastoMaterialProductoExistente.setId(gastoMaterialFormulario.getId());
			gastoMaterialProductoExistente.setValor(gastoMaterialFormulario.getValor());
			gastoMaterialProductoExistente.setTalla(gastoMaterialFormulario.getTalla());
			gastoMaterialProductoExistente.setPieza(gastoMaterialFormulario.getPieza());
			gastoMaterialProductoExistente.setCantidad(gastoMaterialFormulario.getCantidad());
			gastoMaterialProductoExistente.setUnidadMedida(gastoMaterialFormulario.getUnidadMedida());
			
			gastoMaterialProductoModificado = this.iGastoMaterialProductoService.guardarElemento(gastoMaterialProductoExistente);
			
				
		}catch(DataAccessException e) {
			
			mapa.put("mensaje", "Ha ocurrido un error al modificar el elemento ");
			mapa.put("error", e.getMessage() + "Detalle del error: " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje", "El elemento ha sido modificado exitosamente");
		mapa.put("gastoMaterialProducto", gastoMaterialProductoModificado);
		
		return new ResponseEntity<Map<String,Object>>(mapa,HttpStatus.OK);
		
		
	}
	
}
