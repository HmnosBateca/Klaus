package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.ReferenciaProducto;

public interface IReferenciaProductoService {

	public List<ReferenciaProducto> ListarReferenciaProducto(); // Listar
	public ReferenciaProducto ListarReferenciaProductoPorId(Long id); // Listar por id
	Page<ReferenciaProducto> ListarPaginadoReferenciaProducto(Pageable paginador);
	public ReferenciaProducto GuardarReferenciaProducto(ReferenciaProducto referenciaProducto); // Guardar
	public void EliminarReferenciaProducto (Long id);
}
