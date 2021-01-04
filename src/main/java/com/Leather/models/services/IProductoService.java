package com.Leather.models.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Leather.common.ICommonService;

import com.Leather.models.entity.Producto;





	

public interface IProductoService extends ICommonService<Producto>{
	public Page<Producto> ListarProductosEnBodegaInventario(Pageable paginador);
}
