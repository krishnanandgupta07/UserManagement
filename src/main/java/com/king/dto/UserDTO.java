package com.king.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private String pwdUpdate;
	private Long phno;
	
	private Integer countrId;
	private Integer stateId;
	private Integer cityId;
}
