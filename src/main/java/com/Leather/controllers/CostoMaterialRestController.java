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
import com.Leather.models.entity.CostoMaterial;
import com.Leather.models.entity.Material;
import com.Leather.models.entity.Talla;
import com.Leather.models.services.ICostoMaterialService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/api/costoMaterial")
public class CostoMaterialRestController extends CommonRestController<CostoMaterial, ICostoMaterialService> {

	@Autowired
	ICostoMaterialService iCostoMaterialService;
	
	
	
	@GetMapping("/material/{id}")
	public ResponseEntity<?> obtenerCostoMaterialPorIDMaterial(@PathVariable Long id) {
		
		CostoMaterial costoMaterial = null;
		Map<String, Object> mapa = new HashMap<>();
		
		try {
			
			costoMaterial = this.iCostoMaterialService.obtenerCostoMaterialIdMaterial(id);
			
		}catch(DataAccessException e) {
			
			// armo el mapa para agregarlo al ResponseEntity
			mapa.put("mensaje", "Ha ocurrido un error al obtener el costo del material con ID " + id);
			mapa.put("error", e.getMessage() + ": " + e.getMostSpecificCause());
			return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(costoMaterial == null) {
			mapa.put("mensaje", "El costo del material con ID " + id + " no se encuentra activo");
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity< CostoMaterial >(costoMaterial, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarCostoMaterial(@PathVariable Long id, @RequestBody CostoMaterial costoMaterialFormulario){
		
		CostoMaterial costoMaterialExistente = null;
		CostoMaterial costoMaterialNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		costoMaterialExistente = iCostoMaterialService.obtenerElementoPorID(id);
		
		if(costoMaterialExistente == null) {
			mapa.put("mensaje", "El costo no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		
		try {
			costoMaterialExistente.setId(costoMaterialFormulario.getId());
			costoMaterialExistente.setCantidad(costoMaterialFormulario.getCantidad());
			costoMaterialExistente.setMaterial(costoMaterialFormulario.getMaterial());
			costoMaterialExistente.setCosto(costoMaterialFormulario.getCosto());
			
			costoMaterialNuevo = iCostoMaterialService.guardarElemento(costoMaterialExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el elemento "+ costoMaterialExistente.getCantidad()
					 + " " + costoMaterialExistente.getMaterial().getUnidadMedida().getNombre() + "-" + costoMaterialExistente.getCosto());
			mapa.put("error", e.getMessage() + "Detalle: "+ e.getMostSpecificCause());
			
			return new ResponseEntity<Map<String,Object>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "El elemento ha sido modificado exitosamente");
		mapa.put("costoMaterial", costoMaterialNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/material")
	public ResponseEntity<?> obtenerMaterialRegistrado(){
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Material> listaMateriales = null;
		
		try {
			
			listaMateriales = this.iCostoMaterialService.obtenerMaterialRegistrado();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		if(listaMateriales == null) {
			mapa.put("mensaje", "No hay materiales registrados");
			return new ResponseEntity<  Map<String,Object> >( mapa , HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Material>>(listaMateriales, HttpStatus.OK);
	}
	
	
}
