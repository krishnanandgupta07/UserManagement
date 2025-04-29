package com.king.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByEmailAndPwd(String email,String pwd);
	public UserEntity findByEmail(String email);
}
