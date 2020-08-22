package com.Leather.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Producto;





public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {

}
