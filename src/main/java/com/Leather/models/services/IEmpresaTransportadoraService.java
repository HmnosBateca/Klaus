package com.Leather.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.models.entity.EmpresaTransportadora;

public interface IEmpresaTransportadoraService {
	public List<EmpresaTransportadora>findAll();// Listar
	public EmpresaTransportadora findById(Long id); //Buscar por id
	public EmpresaTransportadora Save (EmpresaTransportadora empresaTransportadora);// crear
	public void delete(Long id);// Eliminar
	public Page<EmpresaTransportadora> findAll(Pageable pageable);// Paginador
	
}
