package org.course.commodity.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	      @Override
	      public void addResourceHandlers(ResourceHandlerRegistry registry) {   
	    /**
	   * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
	   *这是图片的物理路径  "file:/+本地图片的地址"
	   * @Date： Create in 14:08 2017/12/20
	   */     registry.addResourceHandler("/Path/64b3b19a-cfa5-46a1-a2cd-4bebf552e0c7.png").addResourceLocations("file:F:\\STSProjects\\SecondCommodity\\pictures");
	          super.addResourceHandlers(registry);
	      }
}
