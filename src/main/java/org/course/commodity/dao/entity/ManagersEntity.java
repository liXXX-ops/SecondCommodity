package org.course.commodity.dao.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="managers")
public class ManagersEntity {
	@Id
	@GeneratedValue
	@Column
	private Integer manager_id;
	@Column
	private String manager_name;
	@Column
	private String manager_pwd;
	@Column
	private char manager_sex;
}

