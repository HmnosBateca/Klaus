package com.Leather.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Color;



public interface IColorDao extends PagingAndSortingRepository<Color, Long> {

}
