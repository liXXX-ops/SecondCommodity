package org.course.commodity.dao.entity;
import java.sql.Date;

import javax.persistence.Column;	
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="releasecomm")
public class ReleaseCommEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recomm_id;   //自增id  定义为long时打印出来的值为null，
	
	@Column
	private String recomm_number;   //编号
	
	@Column
	private String recomm_name;   //名称
	
	@Column
	private String recomm_kind;    //类型
	
	@Column
	private String recomm_describe;   //描述
	
	@Column
	private String recomm_picture;  //保存其路径名称，而非image照片
	
	@Column
	private double recomm_price;    //价格
	
	@Column
	private int recomm_shenhe=0;    //审核与否1为审核、0为未审核表示
	
	@Column
	private Integer recomm_manager=0000000;    //审核人
	
	@Column
	private Integer recomm_user;    //发布人
	
	@Column
	private Date recomm_date;   //getDate()发布日期
	
	@Column
	private Date shcomm_date;   //getDate()发布日期
	
}
