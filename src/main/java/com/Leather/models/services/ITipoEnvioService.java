package com.Leather.models.services;

import java.util.List;

import com.Leather.models.entity.TipoEnvio;

public interface ITipoEnvioService {

	public List<TipoEnvio> findAll();//Listar
	public TipoEnvio findById(Long id);//Bucar por id
	public TipoEnvio save(TipoEnvio tipoEnvio);//Guardar
	public void delete(Long id);//Borrar por id
	}
