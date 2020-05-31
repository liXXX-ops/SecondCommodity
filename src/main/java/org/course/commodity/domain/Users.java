package org.course.commodity.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@GeneratedValue
	private Integer user_id;
	private String user_name;
	private String user_pwd;
	private char sex;
	private String user_address;
	private String user_tele;
	private String user_pedigree;  //系别
	private int user_integral;     //用户账号积分
}

