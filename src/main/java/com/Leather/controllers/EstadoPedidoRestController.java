package com.Leather.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.common.CommonRestController;
import com.Leather.models.entity.EstadoPedido;
import com.Leather.models.services.IEstadoPedidoService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RequestMapping("/api/EstadoPedido")
@RestController
public class EstadoPedidoRestController extends CommonRestController<EstadoPedido, IEstadoPedidoService>{
	
	@GetMapping("/Pedido/{idPedido}")
	public List<EstadoPedido> ListarEstadosPedidosPorPedidos(@PathVariable Long idPedido){
		return super.iService.ListarEstadosPedidosPorPedidos(idPedido);
	}
}
