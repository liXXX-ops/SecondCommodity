package org.course.commodity.domain;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseComm {
	private Integer recomm_id;
	private String recomm_number;
	private String recomm_name;
	private String recomm_kind;
	private String recomm_describe;
	private String recomm_picture;
	private double recomm_price;
	private int recomm_shenhe;
	private Integer recomm_user;
	private Integer recomm_manager;
	private Date recomm_date;
	private Date shcomm_date; 
}
