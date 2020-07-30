package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.TipoTalla;




public interface ITipoTallaService {
	
	public List<TipoTalla> listarTipoTalla();
	public Page<TipoTalla> listarTipoTallaPaginado(Pageable paginador);
	public TipoTalla obtenerTallaPorID(Long id);
	public TipoTalla guardarTipoTalla(TipoTalla tipoTalla);
	public void eliminarTipoTalla(Long id);

}
