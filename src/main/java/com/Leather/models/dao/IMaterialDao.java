package com.Leather.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Leather.models.entity.Material;




public interface IMaterialDao extends PagingAndSortingRepository<Material, Long> {

}
