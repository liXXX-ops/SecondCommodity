package org.course.commodity.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchases {
	private Integer purchase_id;           //id
	private Integer purchase_user;         //购买者
	private Integer purchase_releaseuser;  //商品发布者
	private Integer purchase_recommid;     //购买商品id
	private Date purchase_date;            //购买日期
	private double purchase_price;         //购买金额
}
