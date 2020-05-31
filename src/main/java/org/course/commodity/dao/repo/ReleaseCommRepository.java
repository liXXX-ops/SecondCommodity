package org.course.commodity.dao.repo;

import java.util.Date;
import java.util.List;

import org.course.commodity.dao.entity.ReleaseCommEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReleaseCommRepository extends JpaRepository<ReleaseCommEntity,Integer>{
	//将发布商品信息保存到数据库中
	@Modifying
	@Transactional
	@Query(value="insert into releasecomm(recomm_number,recomm_name,recomm_kind,recomm_describe,recomm_picture,recomm_price,recomm_user,recomm_shenhe,recomm_manager,recomm_date) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)",nativeQuery=true)
	int insertReleaseComm(String number,String name,String kind,String describe,String picture,double price,Integer user,int shenhe,Integer manager,Date date);
	
	//@Query(value="select * from releasecomm")
	//boolean getAllMyRelease();
	//查询用户发布的商品
	@Query(value="select * from releasecomm u where u.recomm_user like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findMyReleaseAll(int id);
	
	//查询管理员审核 ——“过”——的商品
	@Query(value="select * from releasecomm u where u.recomm_manager like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findMyShenheAll(int id);
	
	//查询管理员可以审核的商品，即未审核的商品
	@Query(value="select * from releasecomm u where u.recomm_manager like 0",nativeQuery=true)
	List<ReleaseCommEntity> findShenheComm();   //管理员列为0未审核
	
	//管理员完成审核---进行结果的更新
	@Modifying
	@Transactional
	@Query(value = "update releasecomm set recomm_manager=?3,recomm_shenhe=?2,shcomm_date=?4 where recomm_id=?1",nativeQuery=true)
	int updateShenheComm(Integer id,int shenhe,Integer manager,Date date);
	
	//查询鞋子的商品信息
	@Query(value="select * from releasecomm u where u.recomm_kind like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findXieziComm(String kind);   //管理员列为0未审核
	
	//服装
	@Query(value="select * from releasecomm u where u.recomm_kind like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findFuzhuangComm(String kind);   //管理员列为0未审核
		
	//学习
	@Query(value="select * from releasecomm u where u.recomm_kind like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findXuexiComm(String kind);   //管理员列为0未审核
		
	//生活
	@Query(value="select * from releasecomm u where u.recomm_kind like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findShenghuoComm(String kind);   //管理员列为0未审核
		
	//其他
	@Query(value="select * from releasecomm u where u.recomm_kind like ?1",nativeQuery=true)
	List<ReleaseCommEntity> findOthersComm(String kind);   //管理员列为0未审核
	
	//用户搜索内容
	//@Query(value="select * from releasecomm u where u.recomm_name like '%name%'",nativeQuery=true)
	@Query(value="select * from releasecomm u where u.recomm_name like concat('%',?1,'%')",nativeQuery=true)
	//@Query(value="select * from releasecomm u where u.recomm_name like '%name%'",nativeQuery=true)
	List<ReleaseCommEntity> userSearch(String name); 
	
	//管理员通过日期搜索
	@Query(value="select * from releasecomm u where u.recomm_manager = ?1",nativeQuery=true)
	//@Query(value="select * from releasecomm u where u.recomm_manager = :id and u.shcomm_date = :date",nativeQuery=true)
	List<ReleaseCommEntity> dateSearch(int id); 
	
	
}










