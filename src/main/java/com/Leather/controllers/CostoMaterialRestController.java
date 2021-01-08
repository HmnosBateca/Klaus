package com.Leather.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.common.CommonRestController;
import com.Leather.models.entity.CostoMaterial;
import com.Leather.models.services.ICostoMaterialService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/api/costoMaterial")
public class CostoMaterialRestController extends CommonRestController<CostoMaterial, ICostoMaterialService> {

	@Autowired
	ICostoMaterialService iCostoMaterialService;
	
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
			costoMaterialExistente.setUnidadMedida(costoMaterialFormulario.getUnidadMedida());
			costoMaterialExistente.setMaterial(costoMaterialFormulario.getMaterial());
			costoMaterialExistente.setCosto(costoMaterialFormulario.getCosto());
			
			costoMaterialNuevo = iCostoMaterialService.guardarElemento(costoMaterialExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el elemento "+ costoMaterialExistente.getCantidad()
					 + " " + costoMaterialExistente.getUnidadMedida().getNombre() + "-" + costoMaterialExistente.getCosto());
		}
		
		mapa.put("mensaje", "El elemento ha sido modificado exitosamente");
		mapa.put("costoMaterial", costoMaterialNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
}
