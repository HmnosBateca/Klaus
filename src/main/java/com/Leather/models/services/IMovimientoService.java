package com.Leather.models.services;

import java.util.Date;
import java.util.List;

import com.Leather.common.ICommonService;
import com.Leather.models.entity.Movimiento;

public interface IMovimientoService extends ICommonService<Movimiento>{
	
	
	public List<Movimiento> ListarPorTipo(Long tipo, Date fechaInicial, Date fechaFinal, Date horaInicial, Date horaFinal);
}
