package com.info.infoService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


import com.info.infoService.service.InfoColumnsService;

@SpringBootApplication
/*@EnableEurekaClient*/
@RestController
public class InfoColumnsController {
	
	@Autowired
	private InfoColumnsService infosColumnsService;
	
	
	

}
