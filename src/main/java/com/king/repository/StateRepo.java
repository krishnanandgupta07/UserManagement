package com.king.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer> {

	public List<StateEntity> findByCountryCountryId(Integer countryId);
}
