package org.course.commodity.dao.repo;

import java.util.Date;
import java.util.List;

import org.course.commodity.dao.entity.DiscussesEntity;
import org.course.commodity.dao.entity.ManagersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DiscussesRepository extends JpaRepository<DiscussesEntity,Integer>{
	//插入评论记录
	@Modifying
	@Transactional
	@Query(value="insert into discusses(discuss_content,discuss_date,discuss_recommid,discuss_releaseuser,discuss_user) values(?1,?2,?3,?4,?5)",nativeQuery=true)
	int insertDiscussInfo(String content,Date date,int recommid,int releaseuser,int user); 
	
	//查询评论信息
	@Query(value="select * from discusses u where u.discuss_id like ?1",nativeQuery=true)
	List<DiscussesEntity> findDiscussInfo(int id);
	
	//查询我的评论
	@Query(value="select * from discusses u where u.discuss_user like ?1",nativeQuery=true)
	List<DiscussesEntity> findMycomment(int id);
		
	//查询我发布的商品的评论
	@Query(value="select * from discusses u where u.discuss_releaseuser like ?1",nativeQuery=true)
	List<DiscussesEntity> findComment(int id);
	
}