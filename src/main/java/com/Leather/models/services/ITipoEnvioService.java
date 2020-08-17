package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.TipoEnvio;

public interface ITipoEnvioService {

	public List<TipoEnvio> findAll();//Listar
	public TipoEnvio findById(Long id);//Bucar por id
	public TipoEnvio save(TipoEnvio tipoEnvio);//Guardar
	public void delete(Long id);//Borrar por id
	Page<TipoEnvio> findAll(Pageable pageable);
	
	}
