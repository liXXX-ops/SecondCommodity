package org.course.commodity.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Result;
import org.course.commodity.Service.Impl.UsersServiceImpl;
import org.course.commodity.dao.entity.PurchasesEntity;
import org.course.commodity.dao.entity.UsersEntity;
import org.course.commodity.dao.repo.UsersRepository;
import org.course.commodity.domain.Discusses;
import org.course.commodity.domain.Purchases;
import org.course.commodity.domain.ReleaseComm;
import org.course.commodity.domain.Users;
import org.course.commodity.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import groovy.util.logging.Log;
import lombok.Value;


@Controller   //@RestController时不能访问html文件
/*
 * RestController = Controller + ResponseBody.加上RestController,
 * 返回的内容是你return中的内容，如果是return "Hello World"，
 * 页面显示的就是Hello World。加上Controller，返回的是return中对应的页面,
 * 比如return “hello”,页面的名称是hello。
 */
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersServiceImpl usersServiceImpl;
	//@Autowired
	private UsersRepository usersRepo;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, Object> data = new HashMap<>();
	//private Model model = new Model();
	//发布商品界面
	//private final ResourceLoader resourceLoader;
	//首页
	@RequestMapping(value = "/shouye",method = RequestMethod.GET)
	public String toShouye(HttpServletRequest request) {
		
		return "dynamic/user_success";
	}
	//鞋子
	@RequestMapping(value = "/xiezi",method = RequestMethod.GET)
	public String toXiezi(HttpServletRequest request,Model model) {
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryXieziComm("鞋子");
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);    //model定义成全局变量回出现空指针异常
		return "dynamic/user_xiezi";
	}
	//服装
	@RequestMapping(value = "/fuzhuang",method = RequestMethod.GET)
	public String toFuzhuang(HttpServletRequest request,Model model) {
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryXieziComm("服装");
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);
		
		return "dynamic/user_fuzhuang";
	}
	//学习
	@RequestMapping(value = "/xuexi",method = RequestMethod.GET)
	public String toXuexi(HttpServletRequest request,Model model) {
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryXieziComm("学习用品");
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);
		
		return "dynamic/user_xuexi";
	}
	//生活
	@RequestMapping(value = "/shenghuo",method = RequestMethod.GET)
	public String toShenghuo(HttpServletRequest request,Model model) {
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryXieziComm("生活用品");
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);
		return "dynamic/user_shenghuo";
	}
	//其他
	@RequestMapping(value = "/others",method = RequestMethod.GET)
	public String toOthers(HttpServletRequest request,Model model) {
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryXieziComm("其他");
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);
		return "dynamic/user_others";
	}
	
	//获取商品的信息
	@RequestMapping(value = "/purchase",method = RequestMethod.PUT)
	public String toPurchase(HttpServletRequest request,HttpSession session) {
		Integer recomm_id = Integer.valueOf(request.getParameter("recomm_id"));
		Integer recomm_user = Integer.valueOf(request.getParameter("recomm_user"));
		double recomm_price = Double.parseDouble(request.getParameter("recomm_price"));
		session.setAttribute("recommId", recomm_id);
		session.setAttribute("recommUser", recomm_user);
		session.setAttribute("recommPrice", recomm_price);
		return "redirect:/users/payment";
	}
	
	//保存购买信息
	@RequestMapping(value = "/purchasecomm",method = RequestMethod.PUT)
	public String toPurchaseComm(HttpServletRequest request,HttpSession session) {
		Integer user_id = (Integer) session.getAttribute("userId");
		Integer recomm_id = (Integer) session.getAttribute("recommId");
		Integer recomm_user = (Integer) session.getAttribute("recommUser");
		double recomm_price = (double) session.getAttribute("recommPrice");
		usersServiceImpl.insertPurchaseInfo(recomm_user, new Date(), recomm_price, recomm_id, user_id);
		usersServiceImpl.updateUserIntegral(user_id);
		return "redirect:/users/payment";
	}
	
	//保存发表的评论
	@RequestMapping(value = "/saydiscuss",method = RequestMethod.PUT)
	public String toDiscussComm(HttpServletRequest request,HttpSession session) {
		String content = request.getParameter("discuss_content");
		Date date = new Date();
		Integer recomm_id = Integer.valueOf(request.getParameter("recomm_id"));
		Integer recomm_user = Integer.valueOf(request.getParameter("recomm_user"));
		Integer user = (Integer) session.getAttribute("userId");
		session.setAttribute("recommId", recomm_id);
		session.setAttribute("recommUser", recomm_user);
		usersServiceImpl.insertDiscussInfo(content, date, recomm_id, recomm_user, user);
		return "redirect:/users/xiezi";
	}
	
	//显示评论信息
	@RequestMapping(value = "/showdiscuss",method = RequestMethod.PUT)
	public String toShowDiscuss(HttpServletRequest request,HttpSession session,Model model) {
		String content = request.getParameter("discuss_content");
		Date date = new Date();
		Integer recomm_id = Integer.valueOf(request.getParameter("recomm_id"));
		Integer recomm_user = Integer.valueOf(request.getParameter("recomm_user"));
		Integer user = (Integer) session.getAttribute("userId");
		session.setAttribute("recommId", recomm_id);
		session.setAttribute("recommUser", recomm_user);
		List<Discusses> discusses = this.usersServiceImpl.queryDiscussInfo(recomm_id);
		/*
		for(int i=0;i<discusses.size();i++) {
			int id=discusses.get(i).getDiscuss_user();
			String str = String.valueOf(id);
			str=str.substring(0, 4)+"***";    //隐藏评论用户的id
			((Object) discusses.get(i)).setDiscusses_user(str);
		}*/
		model.addAttribute("discuss", discusses);
		return "dynamic/user_discuss";
	}
	
	//显示我评论过的内容
	@RequestMapping(value = "/mycomment",method = RequestMethod.GET)
	public String toMycomment(HttpServletRequest request,HttpSession session,Model model) {			
		Integer user = (Integer) session.getAttribute("userId");
		List<Discusses> discusses = this.usersServiceImpl.queryMycomment(user);
		model.addAttribute("discuss", discusses);
		return "dynamic/user_comment";
	}
	
	//显示我发布的商品其他人的评价
	@RequestMapping(value = "/comment",method = RequestMethod.GET)
	public String toComment(HttpServletRequest request,HttpSession session,Model model) {
		Integer user = (Integer) session.getAttribute("userId");
		List<Discusses> discusses = this.usersServiceImpl.queryComment(user);  //数据库中评论表的发布者
		model.addAttribute("discuss", discusses);
		return "dynamic/user_comment";
	}
	
	//收货前获取收货人的信息等
	@RequestMapping(value = "/payment",method = RequestMethod.GET)
	public ModelAndView toPayment(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		String url="";
		Integer id = (Integer) session.getAttribute("userId");
		List<Users> user = this.usersServiceImpl.findUserAllInfo(id);
		System.out.println("UUUUU"+user);
		modelAndView.addObject("user", user);
		url="dynamic/user_payment";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public ModelAndView toInfo(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String url="";
		Integer id = (Integer) session.getAttribute("userId");
		List<Users> user = this.usersServiceImpl.findUserAllInfo(id);
		System.out.println("UUUUU"+user);
		modelAndView.addObject("user", user);
		url="dynamic/user_info";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editInfo",method = RequestMethod.GET)
	public ModelAndView toEditInfo(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String url="";
		Integer id = (Integer) session.getAttribute("userId");
		List<Users> user = this.usersServiceImpl.findUserAllInfo(id);
		System.out.println("UUUUU"+user);
		modelAndView.addObject("user", user);
		url="dynamic/user_editInfo";
		modelAndView.setViewName(url);
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveeditInfo",method = RequestMethod.POST)
	public String toSaveEditInfo(HttpServletRequest request,HttpSession session) {
		Integer id = (Integer) session.getAttribute("userId");
		Integer user_id=Integer.valueOf(request.getParameter("user_id").trim());
		String user_name=request.getParameter("user_name");
		String user_pwd=request.getParameter("user_pwd");
		String sex0=request.getParameter("sex");
		String user_address=request.getParameter("user_address");
		String user_tele=request.getParameter("user_tele");
		String user_pedigree=request.getParameter("user_pedigree");
		char sex;
		if(sex0.equals("M")) {
			sex='M';
		}
		else {
			sex='F';
		}
		this.usersServiceImpl.editUserInfo(id,user_id,user_name,user_pwd,sex,user_address,user_tele,user_pedigree);
		String url="dynamic/user_xiezi";
		return url;
	}
	
	
	@RequestMapping(value = "/release",method = RequestMethod.GET)
	public String getRelease(HttpServletRequest request) {
		//String url = request.getParameter("dynamic/registe");
		return "dynamic/release";
	}
	
	//我已经发布的商品
	@RequestMapping(value = "/querymyrelease",method = RequestMethod.GET)
	public String getMyRelease(HttpServletRequest request,Model model,HttpSession session) {
		Integer user = (Integer) session.getAttribute("userId");
		//String url = request.getParameter("dynamic/registe");
		List<ReleaseComm> releaseComm = this.usersServiceImpl.queryMyReleaseComm(user);
		//return "dynamic/querymyrelease";
		//data.put("releaseComm", releaseComm);
		//logger.info("XXXXX",releaseComms.toString());
		
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);    //model定义成全局变量回出现空指针异常
		//System.out.print("CCCCC"+data);
		return "dynamic/querymyrelease";//data;
	}
	
	//将发布的商品信息存储到数据库中
	@RequestMapping("/releaseCommInfo")
	@ResponseBody
    public String releaseCommInfo(@RequestParam("uploadFiles") MultipartFile[] files,@RequestParam("comm_number") String number1,
    		@RequestParam("comm_name") String name1,@RequestParam("comm_kind") String kind1,@RequestParam("comm_describe") String describe1,
    		@RequestParam("comm_price") String price1,HttpServletRequest request,HttpSession session) throws Exception{
		//releaseConnInfo保持着一致，在requestMapping和String中
		logger.info("上传的图片数："+files.length);
		//logger.info("价格价格："+des);
        List<Map<String,Object>> root=new ArrayList<Map<String,Object>>();
        String number = number1;
        String name = name1;//request.getParameter("comm_name");
        String kind = kind1;
        String describe = describe1;
 
        double price = Double.parseDouble(price1);
        String picture="";
        int shenhe=0;//未审核
        Integer manager=0000000;
        Date date = new Date();
        
        logger.info("getSession+++"+session.getAttribute("userId"));  //成功
        Integer user = (Integer) session.getAttribute("userId");//Integer.valueOf((String) session.getAttribute("userId"));
        logger.info("getSession+++"+user);  //成功
        logger.info("DDD"+price); 
        //Date now = new Date(); 
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
        //date = dateFormat.parse(now); 
        logger.info("kkkind"+kind);
        for (MultipartFile file : files) {    //循环保存文件
            Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
            String result_msg="";//上传结果信息
            if (file.getSize() / 1000 > 100){
                result_msg="图片大小不能超过100KB";
            }
            else{
                //判断上传文件格式
                String fileType = file.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    final String localPath="F:\\STSProjects\\SecondCommodity\\src\\main\\resources\\static\\pictures\\"; // 要上传的目标文件存放的绝对路径
                    String fileName = file.getOriginalFilename();//上传后保存的文件名(需要防止图片重名导致的文件覆盖)//获取文件名
                    String suffixName = fileName.substring(fileName.lastIndexOf(".")); //获取文件后缀名
                    fileName = UUID.randomUUID()+suffixName;//重新生成文件名
                    if (FileUtils.upload(file, localPath, fileName)) {
                        String absolutePath=localPath+fileName;//文件存放的路径(一般存放在数据库用于img标签的src)
                        picture=picture+absolutePath+";";
                        result.put("absolutePath",absolutePath); //前端根据是否存在该字段来判断上传是否成功   relative要修改
                        result_msg="图片上传成功";
                    }
                    else{
                        result_msg="图片上传失败";
                    }
                }
                else{
                    result_msg="图片格式不正确";
                }
            }
            result.put("result_msg",result_msg);
            root.add(result);
        }
        logger.info("填入的商品信息为："+number+kind+describe);
        boolean result = usersServiceImpl.insertReleaseComm(number, name, kind, describe, picture, price, user, shenhe,manager,date);
        //String root_json=JSON.toJSONString(root);
        //System.out.println(root_json);
        logger.info("OOOKKK");
        return "dynamic/release";
	}
	@RequestMapping("/getImage")
    @ResponseBody
    public void getImagesId(HttpServletResponse rp) {
        String filePath = "F:\\STSProjects\\SecondCommodity\\comm_picture\\笔1.png";
        File imageFile = new File(filePath);
        if (imageFile.exists()) {
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(imageFile);
                os = rp.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 8];
                while ((count = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                    os.flush();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

      }

	 @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
	 public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{

	    byte[] imageContent ;
	    String path = "F:\\\\STSProjects\\\\SecondCommodity\\\\comm_picture\\\\笔1.png";
	    imageContent = fileToByte(new File(path));

	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	  }

	    public static byte[] fileToByte(File img) throws Exception {
	        byte[] bytes = null;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {
	            BufferedImage bi;
	            bi = ImageIO.read(img);
	            ImageIO.write(bi, "png", baos);
	            bytes = baos.toByteArray();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            baos.close();
	        }
	        return bytes;
	    }
	    
	    
	    
	//获得积分等信息
	@RequestMapping(value = "/userIntegral",method = RequestMethod.GET)
	public ModelAndView getIntegral(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String url="";
		Integer id = (Integer) session.getAttribute("userId");
		List<Users> user = this.usersServiceImpl.findUserAllInfo(id);
		System.out.println("UUUUU"+user);
		modelAndView.addObject("user", user);
		url="dynamic/user_info";
		modelAndView.setViewName(url);
		return modelAndView;
	}
    
	    
	//用户搜索想要的商品信息
	@RequestMapping(value = "/userSearch",method = RequestMethod.POST)
	public String UserSearching(HttpServletRequest request,Model model) {
		String name = request.getParameter("namevvvv");
		System.out.println("搜索名称"+name);
		List<ReleaseComm> releaseComm = this.usersServiceImpl.UserSearch(name);
		for(int i=0;i<releaseComm.size();i++) {
			String str=releaseComm.get(i).getRecomm_picture();
			str=str.substring(66, 106);    //截图出图片的名称
			str="../pictures/"+str;
			releaseComm.get(i).setRecomm_picture(str);
			System.out.println("66666"+str);
		}
		System.out.println("CCCCC"+releaseComm);
		model.addAttribute("releaseComm",releaseComm);
		return "dynamic/user_search";   //redirect:/users/xiezi
	}
	//用户购买的记录
	@RequestMapping(value="/goumai",method=RequestMethod.GET)
	public String GoumaiRecode(HttpServletRequest request,Model model,HttpSession session){
		Integer id = (Integer) session.getAttribute("userId");
		List<Purchases> purchases = this.usersServiceImpl.goumaiRecode(id);
		System.out.println("UUUUU"+purchases);
		model.addAttribute("purchases", purchases);
		
		return "dynamic/user_goumai";
	}
	/*
	@RequestMapping("uploadfiles")
    public String uploadfiles(@RequestParam("uploadfiles") MultipartFile[] uploadfiles,HttpServletRequest request) throws Exception{
        List<String> list = new ArrayList<>();
        for(int i=0;i<uploadfiles.length;i++){
            String prefix = uploadfiles[i].getOriginalFilename().split("\\.")[0];
            String suffix = uploadfiles[i].getOriginalFilename().split("\\.")[1];
            String newfilename = prefix+System.currentTimeMillis()+"."+suffix;
            File newFile = new File(myFilePath+"uploaded"+File.separator+newfilename);
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            uploadfiles[i].transferTo(newFile);
            list.add("uploaded/"+newfilename);
        }
        .addAttribute("list",list);
        return "index";
    }
	@RequestMapping(value = "/release",method = RequestMethod.POST)
	public String getPictures(HttpServletRequest request) {
		String name = request.getParameter("comm_name");
		String kind = request.getParameter("comm_kind");
		String describe = request.getParameter("comm_describe");
		//MultipartFile  这个类一般是用来接受前台传过来的文件
		File picture = request.getInputStream();
		String price = request.getParameter("comm_price");
		File file = new File(null);
		
		
		return "dynamic/release";
	}
	
	@RequestMapping(value = "/release",method = RequestMethod.POST)
	public Result getPicturesss(@RequestParam(value="") MultipartFile multipartFile,HttpServletRequest request) {
		if(multipartFile.isEmpty()) {
			return RestResultGenerator.getErrorResult("");
		}
		BufferedReader bufferedReader = null;
		List<String> list = new ArrayList<>();
		String fileName = UUID.randomUUID().toString().replace("-", "");
		File file = new File("");
		File tmpFile = new File("");
		try {
			
		}
		return null;
	}
	*/
	@GetMapping("/usersList")
	public List<UsersEntity> findAll(){
		return usersRepo.findAll();
	}
	
	@PostMapping("/usersSave")
	public Boolean save(@RequestBody UsersEntity users) {
		usersRepo.save(users);
		return Boolean.TRUE;
	}
	
	@PutMapping("/usersUpdate")
	public Boolean update(@RequestBody UsersEntity users) {
		usersRepo.save(users);
		return Boolean.TRUE;
	}
	
	@DeleteMapping("/usersDelete/{id}")
	public Boolean delete(@PathVariable Integer id) {
		usersRepo.deleteById(id);
		return Boolean.TRUE;
	}
}
