package com.king.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Integer> {

}
