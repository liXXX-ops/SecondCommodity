package org.course.commodity.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UsersEntity {
	@Id
	@GeneratedValue
	@Column
	private Integer user_id;
	@Column
	private String user_name;
	@Column
	private String user_pwd;
	@Column
	private char sex;
	@Column
	private String user_address;
	@Column
	private String user_tele;
	@Column
	private String user_pedigree;  //系别
	@Column
	private int user_integral;     //用户账号积分
	
}
