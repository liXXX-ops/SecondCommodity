package org.course.commodity.dao.repo;

import java.util.Date;
import java.util.List;

import org.course.commodity.dao.entity.ReleaseCommEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.course.commodity.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer>{
	
	//通过id查询整个用户信息
	@Query(value="select * from users u where u.user_id like ?1",nativeQuery=true)
	public UsersEntity findUserById(@Param("id")int id);
	
	//通过id查询该用户的密码，登录中验证
	@Query(value="select user_pwd from users u where u.user_id like :id",nativeQuery=true)    ///?1
	String findUserPwdById(int id);
	
	//注册用户
	@Modifying
	@Transactional
	@Query(value="insert into users(user_id,user_name,user_pwd,sex) values(?1,?2,?3,?4)",nativeQuery=true)
	int insertUserRegiste(int userid,String username,String userpwd,char usersex);  //注册成功后返回值为数据库中发生变化的行数
	
	//将发布商品信息保存到数据库中
	@Modifying
	@Transactional
	@Query(value="insert into releasecomm(recomm_number,recomm_name,recomm_kind,recomm_describe,recomm_picture,recomm_price,recomm_user,recomm_date) values(?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery=true)
	int insertReleaseComm(String number,String name,String kind,String describe,String picture,double price,Integer user,Date date);
	
	//查询用户信息
	@Query(value="select * from users u where u.user_id like ?1",nativeQuery=true)
	List<UsersEntity> findUserAllInfo(int id);
	
	//购买商品成功后修改用户积分
	@Modifying
	@Transactional
	@Query(value = "update users set user_integral=user_integral+20 where user_id=?1",nativeQuery=true)
	int updateUserIntegral(Integer id);
	
	//更新用户的信息
	@Modifying
	@Transactional
	@Query(value = "update users set user_id=?2,user_name=?3,user_pwd=?4,sex=?5,user_address=?6,user_tele=?7,user_pedigree=?8 where user_id=?1",nativeQuery=true)
	int updateUserInfo(int id,int user_id,String user_name,String user_pwd,char sex,String user_address,String user_tele,String user_pedigree);
}
	
	/*
	public List<UserEntity> findByNameLike(String keyword);
	public List<UserEntity> findByAgeBetween(Integer startAge, Integer endAge);
	
	//JPQL锛??1, ?2: 鍩轰簬浣嶇疆鐨勫崰浣嶇锛屽熀浜庡悕绉扮殑鍗犱綅绗?	
	@Query("SELECT entity from UserEntity entity where entity.name like :keyword")
	public List<UserEntity> find ByNameLike2(@Param("keyword") String keyword);
	
	//鍩轰簬MySQL鐨凷QL鏌ヨ
	@Query(value="select * from a_user t where t.name like :keyword", nativeQuery=true)
	public List<UserEntity> findByNameLikeRawSQL(@Param("keyword") String keyName);
	
	@Modifying
	@Query("update UserEntity set career = :career where id = :id")
	public void updateUserName(Long id, String career);
	 */
	
