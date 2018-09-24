package com.bigdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})// 阻止spring boot自动注入dataSource bean
//@EnableTransactionManagement
//@EnableTransactionManagement(proxyTargetClass = true)// 是否使用CGLib代理，默认false
@ComponentScan(basePackages="com.bigdata")//扫描组件
@ServletComponentScan(basePackages="com.bigdata")//扫描拦截器，过滤器
//@EnableAspectJAutoProxy(exposeProxy = true) // 表示通过aop框架暴露该代理对象，aopContext能够访问,默认false
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//	    return new RestTemplate();
//	}
	
	
}
