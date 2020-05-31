package org.course.commodity.Service;

import java.util.Date;
import java.util.List;

import org.course.commodity.domain.Discusses;
import org.course.commodity.domain.Purchases;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;
import org.course.commodity.mapper.UsersMapper;

public interface UsersSerive {
	public Users findUserById(int id);
	public boolean checkUserPwdById(int id,String pwd);
	public boolean insertUserRegiste(int id,String name,String pwd,char sex);
	public boolean insertReleaseComm(String number,String name,String kind,String describe,String picture,double price,Integer user,int shenhe,Integer manager,Date date);
	public List<ReleaseComm> queryMyReleaseComm(int id);
	public List<Users> findUserAllInfo(int id);
	public List<ReleaseComm> queryXieziComm(String kind);
	public List<ReleaseComm> queryFuzhuangComm(String kind);
	public List<ReleaseComm> queryXuexiComm(String kind);
	public List<ReleaseComm> queryShenghuoComm(String kind);
	public List<ReleaseComm> queryOthersComm(String kind);
	public boolean insertPurchaseInfo(int purchase_releaseuser,Date purchase_date,double purchase_price,int purchase_recommid, int purchase_user);
	public boolean insertDiscussInfo(String content,Date date,int recommid,int releaseuser,int user);
	public List<Discusses> queryDiscussInfo(int id);
	public boolean updateUserIntegral(int id);
	public List<ReleaseComm> UserSearch(String kind);
	public boolean editUserInfo(int id,int user_id,String user_name,String user_pwd,char sex,String user_address,String user_tele,String user_pedigree);
	public List<Purchases> goumaiRecode(int id);
}
