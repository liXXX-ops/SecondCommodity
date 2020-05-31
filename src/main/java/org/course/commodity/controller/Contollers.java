package org.course.commodity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.course.commodity.Service.Impl.ManagersServiceImpl;
import org.course.commodity.Service.Impl.UsersServiceImpl;
import org.course.commodity.dao.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Contollers {

	@Autowired
	private UsersServiceImpl usersServiceImpl;
	@Autowired
	private ManagersServiceImpl managersServiceImpl;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*登录界面的跳转
	 * */
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String getLogin() {
		return "dynamic/login";
	}
	
	@RequestMapping(value = "/cancellation",method = RequestMethod.GET)
	public String getCancel() {
		return "dynamic/login";
	}
	
	//登录完成跳转界面的确定：用户/管理员
	//@ResponseBody
	@RequestMapping(value = "/judge",method = RequestMethod.POST)
	public String LoginSuccess(HttpServletRequest request,HttpSession session) {
		Integer role=Integer.valueOf(request.getParameter("role").trim());
		Integer id=Integer.valueOf(request.getParameter("user_id").trim());  //
		String pwd=request.getParameter("user_pwd");
		String url="";
		
		if(role==1) {
			logger.info("登录匹配用户名和密码："+id+pwd);
			boolean result=false;
			result = usersServiceImpl.checkUserPwdById(id, pwd);
			session.setAttribute("userId", id);
			logger.info("session+++"+session.getAttribute("userId"));
			if(result) {
				url="dynamic/user_success";
			}
			else {
				url="dynamic/login";
			}
		}
		else if(role==2){	
			logger.info("登录匹配管理员账号和密码："+id+pwd);
			boolean result=false;
			result = managersServiceImpl.checkManagerPwdById(id, pwd);
			session.setAttribute("managerId", id);
			if(result) {
				url="dynamic/manager_success";
			}
			else {
				url="dynamic/login";
			}
		}	
		return url;
	}
	
	/*注册界面的跳转
	 */
	@RequestMapping(value = "/registe",method = RequestMethod.GET)
	public String getRegiste(HttpServletRequest request) {
		
		//String url = request.getParameter("dynamic/registe");
		return "dynamic/registe";
	}
	/*保存注册信息，注册成功后跳转到登录界面
	 */
	@RequestMapping(value = "/save-registe",method = RequestMethod.POST)   //get有问题
	public String toSaveRegiste(HttpServletRequest request) {
		String url = "";
		Integer id=Integer.valueOf(request.getParameter("user_id").trim());
		String name=request.getParameter("user_name");
		String pwd=request.getParameter("user_pwd");
		String sex0=request.getParameter("sex");
		Integer role=Integer.valueOf(request.getParameter("role").trim());
		char sex;
		if(sex0.equals("M")) {
			sex='M';
		}
		else {
			sex='F';
		}
		if(role==1) {
			logger.info("用户注册信息为："+id+name+pwd+sex);
			boolean result=usersServiceImpl.insertUserRegiste(id, name, pwd, sex);
			if(result) {
				url="dynamic/login";
			}
		}
		else if(role==2){
			logger.info("管理员注册信息为："+id+name+pwd+sex);
			boolean result=managersServiceImpl.insertManagerRegiste(id, name, pwd, sex);
			if(result) {
				url="dynamic/login";
			}
		}
		return url;
	}
}
