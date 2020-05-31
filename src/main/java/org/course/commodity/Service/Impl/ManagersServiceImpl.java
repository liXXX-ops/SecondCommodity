package org.course.commodity.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.course.commodity.dao.entity.ManagersEntity;
import org.course.commodity.dao.entity.ReleaseCommEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.course.commodity.dao.repo.ManagersRepository;
import org.course.commodity.dao.repo.ReleaseCommRepository;
import org.course.commodity.domain.Managers;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;
import org.course.commodity.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagersServiceImpl {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UsersMapper usersMapper;
	@Autowired
	private ManagersRepository managersRepository;
	@Autowired
	private ReleaseCommRepository releaseCommRepository;
	//@Override
	//public Users findUsersById(int id) {
	//	Users user = usersMapper.findUserById(id);
	//	return user;
	//}
	//查询整个用户信息
	public Users findManagerById(int id) {
		ManagersEntity managersEntity = this.managersRepository.findManagerById(id);
		Users user = new Users();
		BeanUtils.copyProperties(managersEntity, user);
		return user;
	}
	//登录密码的验证
	public boolean checkManagerPwdById(int id,String pwd) {
		boolean result;
		logger.info("hello_service");
		String manager_pwd = this.managersRepository.findManagerPwdById(id);
		
		logger.info("managersService中的密码："+manager_pwd);
		
		if(pwd.equals(manager_pwd)) {
			result=true;
		}
		else {
			result=false;
		}
		return result;
	}
	//注册
	public boolean insertManagerRegiste(int id,String name,String pwd,char sex) {
		int resultinsert=this.managersRepository.insertManagerRegiste(id, name, pwd, sex);
		boolean result;
		if(resultinsert==1) {
			logger.info("insertManagerRegiste success!");
			result=true;
		}
		else {
			logger.info("insertManagerRegiste error!");
			result=false;
		}
		return result;
	}
	
	//查询管理员的所有信息
		public List<Managers> findManagerAllInfo(int id){
			List<ManagersEntity> usersEntity = this.managersRepository.findManagerAllInfo(id);
			List<Managers> user = usersEntity.stream().map(entity ->{
				Managers managers = new Managers();
				BeanUtils.copyProperties(entity,managers);	
				return managers;
						
			}).collect(Collectors.toList());
			return user;
		}
		
		//查询我审核的商品
		public List<ReleaseComm> queryMyShenheComm(int id) {
			List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findMyShenheAll(id);
			List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
				ReleaseComm releaseComm = new ReleaseComm();
				BeanUtils.copyProperties(entity,releaseComm);	
				return releaseComm;
						
			}).collect(Collectors.toList());
			return releaseComms;
		}
		
		//查询可以审核的商品
		public List<ReleaseComm> queryShenheComm() {
			List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findShenheComm();
			List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
				ReleaseComm releaseComm = new ReleaseComm();
				BeanUtils.copyProperties(entity,releaseComm);	
				return releaseComm;
								
			}).collect(Collectors.toList());
			return releaseComms;
		}
		
		//将管理员审核的商品更新到数据库中
		public boolean updateShenheComm(int id,int shenhe,int manager,Date date) {
			int resultupdate = this.releaseCommRepository.updateShenheComm(id, shenhe, manager, date);
			boolean result;
			if(resultupdate==1) {
				logger.info("update success!");
				result=true;
			}
			else {
				logger.info("update error!");
				result=false;
			}
			return result;
		}
		
		//
		public boolean editManagerInfo(int id,int user_id,String user_name,String user_pwd,char sex) {
			int resultupdate = this.managersRepository.updateManagerInfo(id,user_id,user_name,user_pwd,sex);
			boolean result;
			if(resultupdate==1) {
				logger.info("update success!");
				result=true;
			}
			else {
				logger.info("update error!");
				result=false;
			}
			return result;
		}
		
		//管理员进行商品的搜索
		public List<ReleaseComm> ManagerSearch(int id) {
			List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.dateSearch(id);
			List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
				ReleaseComm releaseComm = new ReleaseComm();
				BeanUtils.copyProperties(entity,releaseComm);	
				return releaseComm;
						
			}).collect(Collectors.toList());
			return releaseComms;
		}

}

