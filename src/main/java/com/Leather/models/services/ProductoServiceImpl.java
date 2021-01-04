package com.Leather.models.services;

import org.springframework.stereotype.Service;

import com.Leather.common.CommonServiceImpl;

import com.Leather.models.dao.IProductoDao;
import com.Leather.models.entity.Producto;


@Service
public class ProductoServiceImpl extends CommonServiceImpl<Producto, IProductoDao> implements IProductoService{

}
