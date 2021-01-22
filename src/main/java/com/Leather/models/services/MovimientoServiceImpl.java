package com.Leather.models.services;

import org.springframework.stereotype.Service;

import com.Leather.common.CommonServiceImpl;
import com.Leather.models.dao.IMovimientoDao;
import com.Leather.models.entity.Movimiento;

@Service
public class MovimientoServiceImpl extends CommonServiceImpl<Movimiento, IMovimientoDao> implements IMovimientoService{

}
