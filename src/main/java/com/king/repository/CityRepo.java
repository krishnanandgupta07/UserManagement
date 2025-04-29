package com.king.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer> {

}
