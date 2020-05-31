package org.course.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;
@SpringBootApplication
public class SecondCommodityApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecondCommodityApplication.class, args);
	}
}

/*
 *JPA对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。
 *有很多注解，可以利用postman来增加数据，查询能力也特别强；
 * 
 * maven项目管理工具，基于项目对象模型，用来管理项目依赖，编译，文档等信息
 * 
 * thymeleaf保存数据到作用范围域，用于测试Thymeleaf的循环获取数据
 * 它可以让美工在浏览器查看页面的静态效果，也可以让程序员在服务器查看带数据的动态页面效果。这是由于它支持 html 原型，
 * 然后在 html 标签里增加额外的属性来达到模板+数据的展示方式。
 * 浏览器解释 html 时会忽略未定义的标签属性，所以 thymeleaf 的模板可以静态地运行；当有数据返回到页面时，
 * Thymeleaf 标签会动态地替换掉静态内容，使页面动态显示。
 * 可以快速的实现表单绑定、属性编辑器、国际化等功能。
 * 
 */

/* 用户：
 * 1.购买以后评论，购买之前可以私信
 * 2.用户点击发布以后，跳转到发布界面填写要发布商品的信息
 * 3.用户点击信息设置，可以去完善电话等信息
 * 
 * 管理员：
 * 1.点击审核，可以去审核新发布的商品
 */
/*
 * 用户点击购买后跳转到地址界面，确定地址后显示购买成功，将该订单放到订单的数据库中
 * 
 */


