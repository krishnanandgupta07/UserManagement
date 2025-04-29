package com.king.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {

}
