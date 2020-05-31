package org.course.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.course.commodity.domain.Users;


@Mapper
public interface UsersMapper {
	
	Users findUserById(int id);
	String findPwdById(int id);
	boolean insertRegiste(int userid,String username,String userpwd,char usersex);
}
