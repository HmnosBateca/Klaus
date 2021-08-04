package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.Talla;



public interface ITallaService {

	
	public List<Talla> listarTallas();
	public Page<Talla> listarTallasPaginado(Pageable paginador);
	public Talla obtenerTallaPorID(Long id);
	public Talla guardarTalla(Talla talla);
	public void eliminarTalla(Long id);
	public List<Talla> obtenerTallaPorProductoBodega(Long id);
	public List<Talla> ListarTallasNoAsignadasGastoMaterialProducto(Long idTipoTalla, Long idProducto);
}
