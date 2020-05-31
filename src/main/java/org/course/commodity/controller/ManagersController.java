package org.course.commodity.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.course.commodity.Service.Impl.ManagersServiceImpl;
import org.course.commodity.dao.repo.ManagersRepository;
import org.course.commodity.domain.Managers;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller   
@RequestMapping("/managers")
public class ManagersController {
	@Autowired
	private ManagersServiceImpl managersServiceImpl;
	//@Autowired
	private ManagersRepository managersRepo;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public ModelAndView toInfo(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String url="";
		Integer id = (Integer) session.getAttribute("managerId");
		List<Managers> manager = this.managersServiceImpl.findManagerAllInfo(id);
		System.out.println("MMMMM"+manager);
		modelAndView.addObject("manager", manager);
		url="dynamic/manager_info";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editInfo",method = RequestMethod.GET)
	public ModelAndView toEditInfo(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String url="";
		Integer id = (Integer) session.getAttribute("managerId");
		List<Managers> manager = this.managersServiceImpl.findManagerAllInfo(id);
		System.out.println("MMMMM"+manager);
		modelAndView.addObject("manager", manager);
		url="dynamic/manager_editInfo";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveeditInfo",method = RequestMethod.POST)
	public String toSaveEditInfo(HttpServletRequest request,HttpSession session) {
		Integer id = (Integer) session.getAttribute("managerId");
		Integer manager_id=Integer.valueOf(request.getParameter("manager_id").trim());
		String manager_name=request.getParameter("manager_name");
		String manager_pwd=request.getParameter("manager_pwd");
		String sex0=request.getParameter("manager_sex");
		char sex;
		if(sex0.equals("M")) {
			sex='M';
		}
		else {
			sex='F';
		}
		this.managersServiceImpl.editManagerInfo(id,manager_id,manager_name,manager_pwd,sex);
		String url="dynamic/manager_info";
		return url;
	}
	
	//我已经审核的商品
	@RequestMapping(value = "/querymyshenhe",method = RequestMethod.GET)
	public String getMyShenhe(HttpServletRequest request,Model model,HttpSession session) {
		Integer id = (Integer) session.getAttribute("managerId");
		List<ReleaseComm> releaseComm = this.managersServiceImpl.queryMyShenheComm(id);
		releaseComm.remove(null);
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
	
		model.addAttribute("shenheComm",releaseComm);    //model定义成全局变量回出现空指针异常
		//System.out.print("CCCCC"+data);
		return "dynamic/manager_querymyshenhe";//data;
	}
		
	//查询可以审核的商品（还没有审核的商品）
	@RequestMapping(value = "/queryshenhecomm",method = RequestMethod.GET)
	public String getShenheComm(HttpServletRequest request,Model model) {	
		List<ReleaseComm> releaseComm = this.managersServiceImpl.queryShenheComm();
		releaseComm.remove(null);
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("shenheComm",releaseComm);    //model定义成全局变量回出现空指针异常
		//System.out.print("CCCCC"+data);
		return "dynamic/manager_shenhe";//data;
	}
	
	//管理员审核的处理界面，将审核结果写入到数据库中
	@RequestMapping(value = "/modifyshenhe",method = RequestMethod.PUT)
	public String modifyShenheComm(HttpServletRequest request,HttpSession session) {	
		Integer recomm_id = Integer.valueOf(request.getParameter("recomm_id"));
		int recomm_shenhe = Integer.valueOf(request.getParameter("recomm_shenhe"));
		if(recomm_shenhe == 0)
			recomm_shenhe=1;
		else 
			recomm_shenhe=0;
		Integer manager = (Integer) session.getAttribute("managerId");
		Date date = new Date();
		boolean result = this.managersServiceImpl.updateShenheComm(recomm_id, recomm_shenhe, manager, date);
		System.out.println("更新的商品信息为："+recomm_id+recomm_shenhe+manager+date);
		return "redirect:/managers/queryshenhecomm";//data;
	}
	
	//用户搜索想要的商品信息
	@RequestMapping(value = "/managerSearch",method = RequestMethod.POST)
	public String ManagerSearching(HttpServletRequest request,Model model,HttpSession session) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("namemmmm");
		Date date = null;
		
		try {
			
			date =  new SimpleDateFormat("y-M-d").parse(name);
			//date = date.g
		}catch(ParseException e) {
			e.printStackTrace();
			System.out.println("字符串转为date类型发生异常");
		}
		System.out.println("搜索名称"+date);
		Integer id = (Integer) session.getAttribute("managerId");
		List<ReleaseComm> releaseComm = this.managersServiceImpl.ManagerSearch(id);  //date
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("shenheComm",releaseComm);
		return "dynamic/manager_querymyshenhe";   //redirect:/users/xiezi
	}
		
}

















































