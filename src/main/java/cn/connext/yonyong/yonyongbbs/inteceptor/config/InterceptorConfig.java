package cn.connext.yonyong.yonyongbbs.inteceptor.config;

import cn.connext.yonyong.yonyongbbs.inteceptor.PermissionInteceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(permissionInteceptor()).addPathPatterns("/**");
    super.addInterceptors(registry);
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    //        静态资源映射
    registry.addResourceHandler("/").addResourceLocations("/**");
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }

  @Bean
  public PermissionInteceptor permissionInteceptor() {
    return new PermissionInteceptor();
  }
    }