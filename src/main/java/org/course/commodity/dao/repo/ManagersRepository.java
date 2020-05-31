package org.course.commodity.dao.repo;

import java.util.List;

import org.course.commodity.dao.entity.ManagersEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.course.commodity.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ManagersRepository extends JpaRepository<ManagersEntity,Integer>{
	//通过id查询整个用户信息
		@Query(value="select * from managers m where m.manager_id like ?1",nativeQuery=true)
		public ManagersEntity findManagerById(@Param("id")int id);
		
		//通过id查询该用户的密码，登录中验证
		@Query(value="select manager_pwd from managers m where m.manager_id like :id",nativeQuery=true)    ///?1
		String findManagerPwdById(int id);
		
		//注册用户
		@Modifying
		@Transactional
		@Query(value="insert into managers(manager_id,manager_name,manager_pwd,manager_sex) values(?1,?2,?3,?4)",nativeQuery=true)
		int insertManagerRegiste(int managerid,String managername,String managerpwd,char managersex);  //注册成功后返回值为数据库中发生变化的行数
		
		//查询管理员信息
		@Query(value="select * from managers u where u.manager_id like ?1",nativeQuery=true)
		List<ManagersEntity> findManagerAllInfo(int id);
		
		//更新管理员的信息
		@Modifying
		@Transactional
		@Query(value = "update managers set manager_id=?2,manager_name=?3,manager_pwd=?4,manager_sex=?5 where manager_id=?1",nativeQuery=true)
		int updateManagerInfo(int id,int user_id,String user_name,String user_pwd,char sex);
}
