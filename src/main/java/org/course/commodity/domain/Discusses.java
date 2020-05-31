package org.course.commodity.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discusses {
	@GeneratedValue
	private Integer discuss_id;            //id
	private Integer discuss_user;          //评论者
	private Integer discuss_releaseuser;   //评论商品发布者
	private Integer discuss_recommid;      //评论商品id
	private String discuss_content;        //评论内容
	private Date discuss_date;             //评论时间
	
}
