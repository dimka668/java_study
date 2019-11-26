package com.websystique.spring;

import com.websystique.spring.service.FileServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.websystique.spring.configuration.AppConfig;
import com.websystique.spring.service.FileService;

public class AppMain {
	
	public static void main(String args[]){
		AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
		FileService service = (FileService) context.getBean("fileService");
		//FileService service = new FileServiceImpl();
		
		service.readValues();
		context.close();
	}
	
}
