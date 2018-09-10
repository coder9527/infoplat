package com.info.infoService.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.info.infoService.model.Info;
import com.info.infoService.service.InfoService;


@SpringBootApplication
/*@EnableEurekaClient*/
@RestController
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	/*
	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}
	*/
	
	@RequestMapping("/getInfosList")
	@ResponseBody
	public List<Info> getInfosList(){
		return infoService.getInfosList();
	}
	
	@RequestMapping("/getAuthor")
	public void getAuthor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		//return infoService.getInfosList();
		
		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("收到微信验证请求");
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        System.out.println("signature=" + signature);
        System.out.println("timestamp=" + timestamp);
        System.out.println("nonce=" + nonce);
        System.out.println("echostr=" + echostr);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            // if (SignUtil.checkSignature(signature,timestamp, nonce)) {
            out.print(echostr);
            // }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
	}
	
	//getAuthor
	/**
	 * param {id:"",title:""} 
	 */
	@RequestMapping("/saveInfo")
	public void saveInfo(@RequestBody Info info){
		//
		infoService.saveInfo(info);
	}
	
}
