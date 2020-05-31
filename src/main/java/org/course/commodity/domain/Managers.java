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
public class Managers {
	@GeneratedValue
	private Integer manager_id;
	private String manager_name;
	private String manager_pwd;
	private char manager_sex;
}
