package com.Leather.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Color;
import com.Leather.models.entity.Material;
import com.Leather.models.services.IMaterialService;




@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MaterialRestController {


	
	// Inyección de dependencia
	@Autowired
	IMaterialService iMaterialService;
	
	
	
	
	@GetMapping("/material")
	public List<Material> obtenerMateriales(){
		return iMaterialService.listarMateriales();
	}
	
	
	
	
	
	@GetMapping("/material/pagina")
	public ResponseEntity<?> listarMaterialPaginado(Pageable paginador){
		Pageable pag = PageRequest.of(paginador.getPageNumber(), paginador.getPageSize(),Sort.by("nombre").ascending());
		return ResponseEntity.ok().body(iMaterialService.obtenerMaterialPaginado(pag));
	}
	
	
	
	
	@GetMapping("/material/{id}")
	public ResponseEntity<?> obtenerMaterialPorID(@PathVariable Long id){
		
		Material material = null;
		Map<String, Object> mapa = new HashMap<>();
		
		// primer caso: al consultar en base de datos hay un error
		try {
			material = iMaterialService.obtenerMaterialPorID(id);
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrió un error al consultar el material con ID: "+ id);
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object>  >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// segundo caso: el material njo fué encontrado
		if(material == null) {
			mapa.put("mensaje", "El material con ID "+ id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<Material>(material, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	@GetMapping("/material/filtro/{nombre}")
	public ResponseEntity<?> buscarMaterialPornombre(@PathVariable String nombre){
		List<Material> listaMateriales = iMaterialService.buscarMaterialPorNombre(nombre);
		return ResponseEntity.ok(listaMateriales);
	}
	
	
	
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PostMapping("/material")
	public ResponseEntity<?> guardarMaterial(@RequestBody Material material){
		
		Material materialnuevo = null;
		Map<String,Object> mapa = new HashMap<>();
		
		try {
			materialnuevo = iMaterialService.agregarMaterial(material);
		}catch (DataAccessException e) {
			mapa.put("mensaje", "Hubo un error al registrar el material " + material.getNombre());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "El material " + material.getNombre() + " ha sido registrado exitosamente");
		mapa.put("material", materialnuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@PutMapping("material/{id}")
	public ResponseEntity<?> modificarMaterial(@RequestBody Material materialFormulario, @PathVariable Long id){
		
		Material materialNuevo = null;
		Material materialExistente = null;
		Map<String,Object> mapa = new HashMap<>();
		
		try {
			materialExistente = iMaterialService.obtenerMaterialPorID(id);
		}catch (DataAccessException e) {
			mapa.put("mensaje", "Hubo un error al consultar el material con ID " + id);
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(materialExistente == null) {
			mapa.put("mensaje", "El material con ID "+ id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);			
		}
		
		
		try {
			materialExistente.setNombre(materialFormulario.getNombre());
			materialExistente.setDescripcion(materialFormulario.getDescripcion());
			materialExistente.setCantidad(materialFormulario.getCantidad());
			materialExistente.setUnidadMedida(materialFormulario.getUnidadMedida());
			materialExistente.setPiezas(materialFormulario.getPiezas());
			
			materialNuevo = iMaterialService.agregarMaterial(materialExistente);
			
		}catch (DataAccessException e) {
			mapa.put("mensaje", "Hubo un error al registrar el material con ID " + id);
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		mapa.put("mensaje", "El material "+ materialFormulario.getNombre() + " ha sido modificado exitosamente");
		mapa.put("material", materialNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);		
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERADOR')")
	@DeleteMapping("/material/{id}")
	public ResponseEntity<?> eliminarMaterial(@PathVariable Long id){
		
		Map<String,Object> mapa = new HashMap<>();
		Material materialConsultado = null;
		
				
				
		try {
			materialConsultado = iMaterialService.obtenerMaterialPorID(id);
		}catch (DataAccessException e) {
			mapa.put("mensaje", "Hubo un error al consultar el material con ID " + id);
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
		if(materialConsultado == null) {
			mapa.put("mensaje", "El material con ID "+ id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);			
		}
		
		
		
		
		try {
			iMaterialService.eliminarMaterial(id);
		}catch (DataAccessException e) {
			mapa.put("mensaje", "Hubo un error al consultar el material con ID " + id);
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje","El material" + materialConsultado.getNombre() + "ha sido eliminado exitosamente");
		return new ResponseEntity< Map<String,Object> >(mapa,HttpStatus.OK);
		
	}
	
	
}
