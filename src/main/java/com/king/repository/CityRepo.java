package com.king.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer> {

	public List<CityEntity> findByStateStateId(Integer stateId); 
}
