package com.info.infoService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.infoService.model.Info;
import com.info.infoService.service.InfoService;


@SpringBootApplication
@EnableEurekaClient
@RestController
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	
	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}
	
	
	@RequestMapping("/getInfosList")
	@ResponseBody
	public List<Info> getInfosList(){
		return infoService.getInfosList();
	}
	
	/**
	 * param {id:"",title:""} 
	 */
	@RequestMapping("/saveInfo")
	public void saveInfo(@RequestBody Info info){
		//
		infoService.saveInfo(info);
	}
	
}
