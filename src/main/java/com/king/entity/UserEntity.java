package com.king.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_tbl")
@Setter
@Getter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private String pwdUpdate;
	private Long phno;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity countrId;
	@ManyToOne
	@JoinColumn(name = "state_id")
	private StateEntity stateId;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityEntity cityId;
	

}
