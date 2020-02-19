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

import com.Leather.models.entity.Cliente;
import com.Leather.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200/","*"})//permiso de enviar y recibir con este dominio
@RestController//api rest
@RequestMapping("/api")//generamos la url
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;//listado de clientes
	@GetMapping("/clientes")//listar peticion Get url  generamos empoing de la peticion
	public List<Cliente>index(){
		return clienteService.findAll();//metodo
	}
	
	@GetMapping("/clientes/{id}")//Get por id
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);//metodo Get por id
	}
	
	@PostMapping("/clientes")//Crear cliente(Post)
	public Cliente create(@RequestBody Cliente cliente) {//objeto Cliente recues modifica los datos
		return clienteService.save(cliente);
	}
	
	@PutMapping("/clientes/{id}")//con la id obtenemos de la base de datos y actualizamos 
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {//un cliente ya modificado
		Cliente clienteActual=clienteService.findById(id);//obtenemos el ciente por id
		clienteActual.setDocumento(cliente.getDocumento());
		clienteActual.setNombres(cliente.getNombres());
		clienteActual.setApellidos(cliente.getApellidos());
		clienteActual.setNumero_contacto(cliente.getNumero_contacto());
		clienteActual.setDireccion(cliente.getDireccion());
		clienteActual.setCorreo(cliente.getCorreo());
		clienteActual.setCodigo_postal(cliente.getCodigo_postal());	
		
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
}
