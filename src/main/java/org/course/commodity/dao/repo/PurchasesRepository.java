package org.course.commodity.dao.repo;

import java.util.Date;
import java.util.List;

import org.course.commodity.dao.entity.PurchasesEntity;
import org.course.commodity.dao.entity.ReleaseCommEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PurchasesRepository extends JpaRepository<PurchasesEntity,Integer>{
	//插入购买记录
	@Modifying
	@Transactional
	@Query(value="insert into purchases(purchase_releaseuser,purchase_date,purchase_price,purchase_recommid,purchase_user) values(?1,?2,?3,?4,?5)",nativeQuery=true)
	int insertPurchaseInfo(int purchase_releaseuser,Date purchase_date,double purchase_price,int purchase_recommid,int purchase_user); 
	
	
	//查询用户的订单
	@Query(value="select * from purchases u where u.purchase_user like ?1",nativeQuery=true)
	List<PurchasesEntity> goumaiRecode(int id);   //管理员列为0未审核
}
