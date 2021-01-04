package com.Leather.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.entity.Producto;





public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {

}
