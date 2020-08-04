package com.Leather.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Pieza;



public interface IPiezaDao extends PagingAndSortingRepository<Pieza, Long> {

}
