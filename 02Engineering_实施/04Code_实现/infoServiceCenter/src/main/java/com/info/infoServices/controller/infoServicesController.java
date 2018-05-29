package com.info.infoServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.infoServices.service.InfoServices;

@RestController
public class infoServicesController {
	@Autowired
	InfoServices infoServices;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        
    	return infoServices.sayHiFromClientOne(name);
    }
    @RequestMapping(value = "/h2",method = RequestMethod.GET)
    public String sayHH(@RequestParam String name){
    	return "heheh";
    }
}
