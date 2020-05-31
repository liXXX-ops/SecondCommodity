package org.course.commodity.Service;

import java.util.Date;
import java.util.List;

import org.course.commodity.domain.Managers;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;

public interface ManagersService {
	public Managers findManagerById(int id);
	public boolean checkManagerPwdById(int id,String pwd);
	public boolean insertManagerRegiste(int id,String name,String pwd);
	public List<Managers> findManagerAllInfo(int id);
	public List<ReleaseComm> queryMyShenheComm(int id);
	public List<ReleaseComm> queryShenheComm();
	public boolean updateShenheComm(int id,int shenhe,int manager,Date date);
	public boolean editManagerInfo(int id,int user_id,String user_name,String user_pwd,char sex);
	public List<ReleaseComm> ManagerSearch(int id);
}
