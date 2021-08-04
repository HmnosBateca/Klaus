package com.Leather.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.IMovimientoDao;
import com.Leather.models.entity.Movimiento;

@Service
public class MovimientoServiceImpl extends CommonServiceImpl<Movimiento, IMovimientoDao> implements IMovimientoService{

	@Override
	@Transactional(readOnly = true)
	public List<Movimiento> ListarPorTipo(Long tipo,
			Date fechaInicial, Date fechaFinal, Date horaInicial, Date horaFinal) {
		
		return super.iRepositorio.ListarPorTipo(tipo, fechaInicial, fechaFinal, horaInicial, horaFinal);
	}

}
