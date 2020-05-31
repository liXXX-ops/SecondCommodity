package org.course.commodity.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.course.commodity.Service.UsersSerive;
import org.course.commodity.dao.entity.DiscussesEntity;
import org.course.commodity.dao.entity.PurchasesEntity;
import org.course.commodity.dao.entity.ReleaseCommEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.course.commodity.dao.repo.DiscussesRepository;
import org.course.commodity.dao.repo.PurchasesRepository;
import org.course.commodity.dao.repo.ReleaseCommRepository;
import org.course.commodity.dao.repo.UsersRepository;
import org.course.commodity.domain.Discusses;
import org.course.commodity.domain.Purchases;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;
import org.course.commodity.mapper.UsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersSerive{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UsersMapper usersMapper;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ReleaseCommRepository releaseCommRepository;
	@Autowired
	private PurchasesRepository purchasesRepository;
	@Autowired
	private DiscussesRepository discussesRepository;
	
	//@Override
	//public Users findUsersById(int id) {
	//	Users user = usersMapper.findUserById(id);
	//	return user;
	//}
	
	//查询整个用户信息
	public Users findUserById(int id) {
		UsersEntity usersEntity = this.usersRepository.findUserById(id);
		Users user = new Users();
		BeanUtils.copyProperties(usersEntity, user);
		return user;
	}
	
	//登录密码的验证
	public boolean checkUserPwdById(int id,String pwd) {
		boolean result;
		logger.info("hello_service");
		String user_pwd = this.usersRepository.findUserPwdById(id);
		
		logger.info("usersService中的密码："+user_pwd);
		
		if(pwd.equals(user_pwd)) {
			result=true;
		}
		else {
			result=false;
		}
		return result;
	}
	
	//注册
	public boolean insertUserRegiste(int id,String name,String pwd,char sex) {
		int resultinsert=this.usersRepository.insertUserRegiste(id, name, pwd, sex);
		boolean result;
		if(resultinsert==1) {
			logger.info("insertUserRegiste success!");
			result=true;
		}
		else {
			logger.info("insertUserRegiste error!");
			result=false;
		}
		return result;
	}
	
	//将用户发布的商品保存到数据库中
	public boolean insertReleaseComm(String number,String name,String kind,String describe,String picture,double price,Integer user,int shenhe,Integer manager,Date date) {
		int resultinsert=this.releaseCommRepository.insertReleaseComm(number, name, kind, describe, picture, price, user,shenhe,manager, date);
		boolean result;
		if(resultinsert==1) {
			logger.info("insertReleaseComm success!");
			result=true;
		}
		else {
			logger.info("insertReleaseComm error!");
			result=false;
		}
		return result;
	}
	
	//查询数据库中的所有发布的商品
	public List<ReleaseComm> queryMyReleaseComm(int id) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findMyReleaseAll(id);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	
	//查询用户的所有信息
	public List<Users> findUserAllInfo(int id){
		List<UsersEntity> usersEntity = this.usersRepository.findUserAllInfo(id);
		List<Users> user = usersEntity.stream().map(entity ->{
			Users users = new Users();
			BeanUtils.copyProperties(entity,users);	
			return users;
					
		}).collect(Collectors.toList());
		return user;
	}
	
	//鞋子
	public List<ReleaseComm> queryXieziComm(String kind) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findXieziComm(kind);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	//服装
	public List<ReleaseComm> queryFuzhuangComm(String kind) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findFuzhuangComm(kind);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	//学习
	public List<ReleaseComm> queryXuexiComm(String kind) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findXuexiComm(kind);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	//生活
	public List<ReleaseComm> queryShenghuoComm(String kind) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findShenghuoComm(kind);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	//其他
	public List<ReleaseComm> queryOthersComm(String kind) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.findOthersComm(kind);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		return releaseComms;
	}
	
	//插入购买记录
	public boolean insertPurchaseInfo(int purchase_releaseuser,Date purchase_date,double purchase_price,int purchase_recommid, int purchase_user) {
		int resultinsert=this.purchasesRepository.insertPurchaseInfo(purchase_releaseuser, purchase_date, purchase_price, purchase_recommid, purchase_user);
		boolean result;
		if(resultinsert==1) {
			logger.info("insertPurchaseInfo success!");
			result=true;
		}
		else {
			logger.info("insertPurchaseInfo error!");
			result=false;
		}
		return result;
	}
	
	//插入评论记录
	public boolean insertDiscussInfo(String content,Date date,int recommid,int releaseuser,int user) {
		int resultinsert=this.discussesRepository.insertDiscussInfo(content, date, recommid, releaseuser, user);
		boolean result;
		if(resultinsert==1) {
			logger.info("insertDiscussInfo success!");
			result=true;
		}
		else {
			logger.info("insertDiscussInfo error!");
			result=false;
		}
		return result;
	}
	
	//查询评论信息
	public List<Discusses> queryDiscussInfo(int id) {
		List<DiscussesEntity> discussesEntity = this.discussesRepository.findDiscussInfo(id);
		List<Discusses> discusses = discussesEntity.stream().map(entity ->{
			Discusses discuss = new Discusses();
			BeanUtils.copyProperties(entity,discuss);	
			return discuss;
					
		}).collect(Collectors.toList());
		return discusses;
	}
	
	//查询我的评论
	public List<Discusses> queryMycomment(int id) {
		List<DiscussesEntity> discussesEntity = this.discussesRepository.findMycomment(id);
		List<Discusses> discusses = discussesEntity.stream().map(entity ->{
			Discusses discuss = new Discusses();
			BeanUtils.copyProperties(entity,discuss);	
			return discuss;
						
		}).collect(Collectors.toList());
		return discusses;
	}
		
		
	//查询我发布的商品的评论
	public List<Discusses> queryComment(int id) {
		List<DiscussesEntity> discussesEntity = this.discussesRepository.findComment(id);
		List<Discusses> discusses = discussesEntity.stream().map(entity ->{
			Discusses discuss = new Discusses();
			BeanUtils.copyProperties(entity,discuss);	
			return discuss;		
		}).collect(Collectors.toList());
		return discusses;
	}
	
	//更新用户的积分
	public boolean updateUserIntegral(int id) {
		int resultupdate = this.usersRepository.updateUserIntegral(id);
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
	
	//用户搜索的内容
	public List<ReleaseComm> UserSearch(String name) {
		List<ReleaseCommEntity> releaseCommEntity = this.releaseCommRepository.userSearch(name);
		List<ReleaseComm> releaseComms = releaseCommEntity.stream().map(entity ->{
			ReleaseComm releaseComm = new ReleaseComm();
			BeanUtils.copyProperties(entity,releaseComm);	
			return releaseComm;
					
		}).collect(Collectors.toList());
		System.out.println("service中的搜索结果：：："+releaseComms);
		return releaseComms;
	}
	
	//
	public boolean editUserInfo(int id,int user_id,String user_name,String user_pwd,char sex,String user_address,String user_tele,String user_pedigree) {
		int resultupdate = this.usersRepository.updateUserInfo(id,user_id,user_name,user_pwd,sex,user_address,user_tele,user_pedigree);
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
	
	
	//用户的购买记录
	public List<Purchases> goumaiRecode(int id) {
		List<PurchasesEntity> purchasesEntity = this.purchasesRepository.goumaiRecode(id);
		List<Purchases> purchase = purchasesEntity.stream().map(entity ->{
			Purchases purchases = new Purchases();
			BeanUtils.copyProperties(entity,purchases);	
			return purchases;
					
		}).collect(Collectors.toList());
		System.out.println("service中的搜索结果：：："+purchase);
		return purchase;
	}
	/*Mapping mybatis
	@Override
	public boolean checkUserPwdById(int id,String pwd) {
		boolean result;//=false;
		logger.info("hello_service");
		String user_pwd = usersMapper.findPwdById(id);
		
		logger.info("usersService中的密码："+user_pwd);
		
		if(pwd.equals(user_pwd)) {
			result=true;
		}
		else {
			result=false;
		}
		return result;
		
	}
	
	public boolean insertRegiste(int id,String name,String pwd,char sex) {
		boolean result=usersMapper.insertRegiste(id, name, pwd, sex);
		if(result) {
			logger.info("insertRegiste success!");
			result=true;
		}
		else {
			logger.info("insertRegiste error!");
			result=false;
		}
		return result;
	}
   */
}
