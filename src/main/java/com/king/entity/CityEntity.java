package com.king.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "city_master")
public class CityEntity {
	@Id
	private Integer cityId;
	private String cityName;
	
	 @ManyToOne
	 @JoinColumn(name = "state_id")
	  private StateEntity state;
	

}
