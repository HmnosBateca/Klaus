package com.Leather.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.Leather.models.entity.Movimiento;
import com.Leather.models.services.IMovimientoService;


@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/api/movimiento")
@RestController
public class MovimientoRestController extends CommonRestController<Movimiento, IMovimientoService> {
	
	@Autowired
	IMovimientoService iMovimientoService;
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoFormulario){
		
		Movimiento movimientoExistente = null;
		Movimiento movimientoNuevo = null;
		Map<String, Object> mapa = new HashMap<>();
		
		movimientoExistente = iMovimientoService.obtenerElementoPorID(id);
		
		if(movimientoExistente == null) {
			mapa.put("mensaje", "El elemento con id "+ id +" no está registrado");
			return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.NOT_FOUND);
		}		
		
		try {
			movimientoExistente.setId(movimientoFormulario.getId());
			movimientoExistente.setDinero(movimientoFormulario.getDinero());
			movimientoExistente.setTipo(movimientoFormulario.getTipo());
			movimientoExistente.setPedido(movimientoFormulario.getPedido());
			movimientoExistente.setBodegaInventario(movimientoFormulario.getBodegaInventario());
			
			
			movimientoNuevo = iMovimientoService.guardarElemento(movimientoExistente);
			
		}catch(DataAccessException e) {
			mapa.put("mensaje", "Ocurrio un error al modificar el elemento "+ movimientoExistente.getTipo());
		}
		
		mapa.put("mensaje", "El elemento "+ movimientoExistente.getTipo() +" ha sido modificado exitosamente");
		mapa.put("unidadMedida", movimientoNuevo);
		
		return new ResponseEntity< Map<String,Object> >(mapa, HttpStatus.OK);
	}
	
	@GetMapping("/Pedido/{tipo}/{fechaInicial}/{fechaFinal}/{horaInicial}/{horaFinal}")
	public List<Movimiento> ListarPorTipo(@PathVariable Long tipo, @PathVariable String fechaInicial, @PathVariable String fechaFinal,
			@PathVariable String horaInicial, @PathVariable String horaFinal) {
		
				Date fechaInicialDate = null;
				Date fechaFinalDate = null;
				Date horaInicialDate = null; 
				Date horaFinalDate = null;
				
				try {
					fechaInicialDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
					fechaFinalDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal);
					horaInicialDate = new SimpleDateFormat("HH:mm:ss").parse(horaInicial);
					horaFinalDate = new SimpleDateFormat("HH:mm:ss").parse(horaFinal);
					
					System.out.println(fechaInicialDate);
					System.out.println(fechaFinalDate);
					System.out.println(horaInicialDate);
					System.out.println(horaFinalDate);
					
					
				} catch (ParseException e) {
					
					e.getCause();
					e.printStackTrace();
					
				}
				
				return  iMovimientoService.ListarPorTipo(tipo, fechaInicialDate, fechaFinalDate, horaInicialDate, horaFinalDate);
		
	}
}
