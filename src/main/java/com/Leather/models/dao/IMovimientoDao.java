package com.Leather.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.Leather.models.entity.Movimiento;


public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento, Long>{
	
	Page<Movimiento> findAll(Pageable pageable);
	
	@Query("select mo from Movimiento mo  where mo.tipo = ?1 and mo.fechaRegistro >= ?2 and mo.fechaRegistro <= ?3 and mo.horaRegistro >= ?4 and mo.horaRegistro <= ?5")
	List<Movimiento> ListarPorTipo(Long tipo, Date fechaInicial, Date fechaFinal, Date horaInicial, Date horaFinal);
}
