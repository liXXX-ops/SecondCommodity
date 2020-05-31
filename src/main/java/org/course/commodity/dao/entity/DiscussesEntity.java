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
@Table(name="discusses")
public class DiscussesEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer discuss_id;            //id
	@Column
	private Integer discuss_user;          //评论者
	@Column
	private Integer discuss_releaseuser;   //评论商品发布者
	@Column
	private Integer discuss_recommid;      //评论商品id
	@Column
	private String discuss_content;        //评论内容
	@Column
	private Date discuss_date;             //评论时间
}
