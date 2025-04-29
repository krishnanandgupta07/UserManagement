package com.king.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "state_master")
public class StateEntity {
	
	@Id
	private Integer stateId;
	private String stateName;
	
	
	
	@ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<CityEntity> cities;
}
