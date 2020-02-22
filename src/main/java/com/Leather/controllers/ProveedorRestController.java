package com.Leather.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.entity.Proveedor;
import com.Leather.models.services.IProveedorService;


@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorRestController {

	
	//Inyeccion de dependencias
	@Autowired
	IProveedorService iProveedorService;
	
	
	
	
	//Este método obtiene todos los proveedores de la base de datos
	// Se anota con @GetMapping pues es va a mapear una petición get: /proveedores
	@GetMapping("/proveedores")
	public List<Proveedor> listarProveedores(){
		return iProveedorService.listarProveedores();
	}
	
	
	
	
	
	// Este método obtiene el proveedor por su ID. Retorna un ResponseEntity de tipo genérico "?"
	// Se anota con @GetMapping pues es va a mapear una petición get: /proveedores/{id}
	@GetMapping("/proveedores/{id}")
	public ResponseEntity<?>obtenerProveedorPorId(@PathVariable Long id){
		
		
		Proveedor proveedor = null;
		Map<String, Object> mapa = new HashMap<>(); // acá se va a guardar la respuesta de la operación
		
			
		try {
			
			proveedor = iProveedorService.obtenerProveedorPorId(id);
			
		}catch(DataAccessException e) {

			//	si ocurre un error en base de datos se asigna el texto del error en el Map que luego va a ser retornado dentro del ResponseEntity
			mapa.put("mensaje","Ocurrió un error al obtener el proveedor con id " + id);
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object>  >(mapa,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		// Si no existe el proveedor con el id se retorna el ResponseEntity con el texto de error
		if(proveedor == null) {
			mapa.put("mensaje", "no se encuentra el proveedor con id " + id + " en la base de datos");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}	
		
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK); 
		
	}
	
	
	
	/*
	 * El método guardarProveedor() guarda en base de datos un proveedor.
	 * 
	 * Parametros:
	 * 		El contenido de la petición Http (Un proveedor en formato JSON)
	 * 
	 * Retorna:
	 * 		Un ResponseEntity con su contenido (un Map)
	 * 			El map tiene almacenado el mensaje y el obbjeto Proveedor
	 * 
	 * Se anota con @PostMapping pues es va a mapear una petición post: /proveedores
	*/
	
	@PostMapping("/proveedores")
	public ResponseEntity<?> guardarProveedor(@RequestBody Proveedor proveedor) {
		
		Map<String, Object> mapa = new HashMap<>();
		Proveedor proveedorNuevo = null;
		
		
		try {
			proveedorNuevo = iProveedorService.guardarProveedor(proveedor);
		}catch(DataAccessException e) {
			mapa.put("mensaje","Ocurrió un error al registrar al proveedor " + proveedor.getNombres());
			mapa.put("error", e.getMessage() + " : " + e.getMostSpecificCause());
		}
		
		mapa.put("mensaje", "El proveedor " + proveedor.getNombres() + " ha sido registrado exitosamente");
		mapa.put("proveedor", proveedorNuevo);
		
		return new ResponseEntity<  Map<String,Object>  >(mapa, HttpStatus.CREATED);
		
	}
	
	
	
	
	
	/*
	 * El método actualizarProveedor permite actualizar la informacion de un proveedor por medio de su ID
	 * 
	 * Parámetros:
	 * 		- El id del proveedor a actualizar
	 * 		- El objeto Proveedor con la información nueva (Se obtiene con un @RequestBody)
	 * Retorna:
	 * 		- Un objeto "ResponseEntity" de tipo "Map"
	 * 				El Map tiene almacenado el mensaje y el objeto actualizado (Proveedor)
	 * 
	 *  Se anota con @PutMapping pues va a mapear una petición put (update): /proveedores/{id}
	*/
	
	@PutMapping("/proveedores/{id}")
	public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor proveedor, @PathVariable Long id){
		
		Map<String,Object> mapa = new HashMap<>();
		
		Proveedor proveedorConsultado = iProveedorService.obtenerProveedorPorId(id);
		Proveedor proveedorNuevo = null;
		
		if(proveedorConsultado == null) {
			mapa.put("mensaje", "El proveedor con id " + id + " no se encuentra registrado");
			return new ResponseEntity< Map<String,Object>  > (mapa,HttpStatus.NOT_FOUND); 
		}
		
		try {
			
			// actualizamos la información del objeto proveedorConsultado con la información que viene por parámetros
			proveedorConsultado.setNombres(proveedor.getNombres());
			proveedorConsultado.setApellidos(proveedor.getApellidos());
			proveedorConsultado.setCorreoElectronico(proveedor.getCorreoElectronico());
			proveedorConsultado.setDireccionResidencia(proveedor.getDireccionResidencia());
			proveedorConsultado.setDocumento(proveedor.getDocumento());
			proveedorConsultado.setNit(proveedor.getNit());
			proveedorConsultado.setNumeroContacto(proveedor.getNumeroContacto());
			
			proveedorNuevo = iProveedorService.guardarProveedor(proveedorConsultado); // actualizamos el proveedor
			
		}catch(DataAccessException e) {
			mapa.put("mensaje","Hubo un error al actualizar la información del proveedor " + proveedor.getNombres());
			mapa.put("error",e.getMessage() + " : " + e.getMostSpecificCause());
			return new ResponseEntity< Map<String,Object> >(mapa,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		mapa.put("mensaje","El proveedor " + proveedor.getNombres() + " ha sido actualizado exitosamente");
		mapa.put("proveedor",proveedorNuevo);
		return new ResponseEntity< Map<String,Object> >(mapa,HttpStatus.OK);
		
	}
	
	
	/*
	 * El método eliminar Proveedor borra n proveedor por su id
	 * 
	 * Parámetros:
	 * 		- El ID del proveedor que va a ser eliminado
	 * 
	 * No retorna valor alguno
	 * 
	 *  Se anota con @DeleteMapping pues va a mapear una petición delete (eliminar): /proveedores/{id}
	*/
	
	@DeleteMapping("/proveedores/{id}")
	public ResponseEntity<?> eliminarProveedor(@PathVariable Long id) {
		
		Map<String,Object> mapa = new HashMap<>();
		
		Proveedor proveedorConsultado = null;
		proveedorConsultado = iProveedorService.obtenerProveedorPorId(id);
		
		if(proveedorConsultado == null) {
			mapa.put("mensaje","El proveedor con ID " + id +" no existe en la base de datos");
			return new ResponseEntity<  Map<String,Object>  >(mapa,HttpStatus.NOT_FOUND);
		}
		
		try {			
			iProveedorService.eliminarProveedor(id);			
		}catch(DataAccessException e) {
			mapa.put("mensaje","Ha ocurrido un error al eliminar al proveedor");
			mapa.put("error",e.getMessage() + " : " +e.getMostSpecificCause());
		}
		
		mapa.put("mensaje","El proveedor ha sido eliminado exitosamente");
		return new ResponseEntity< Map<String,Object> >(mapa,HttpStatus.OK);
		
	}
	
	
	
}
