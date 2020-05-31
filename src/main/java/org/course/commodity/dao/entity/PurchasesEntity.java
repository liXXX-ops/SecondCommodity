package org.course.commodity.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="purchases")
public class PurchasesEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer purchase_id;           //id
	@Column
	private Integer purchase_user;         //购买者
	@Column
	private Integer purchase_releaseuser;  //商品发布者
	@Column
	private Integer purchase_recommid;     //购买商品id
	@Column
	private Date purchase_date;            //购买日期
	@Column
	private double purchase_price;         //购买金额
}
