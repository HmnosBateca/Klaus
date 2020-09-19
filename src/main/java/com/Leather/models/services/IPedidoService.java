package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Pedido;

public interface IPedidoService { // Metodos
	public List<Pedido> ListarPedido(); //Listar Pedido
	public Page<Pedido> PaginarPedido(Pageable pageable); // Obtener Paginador
	public Pedido ObtenerPedidoPorId(Long id); // Obtener Pedido por Id
	public Pedido GuardarPedido(Pedido pedido); // Guardar
	public void EliminarPedido(Long id); // Elimina por id
}
