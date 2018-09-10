package com.info.uploaderService.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.info.uploaderService.util.FileUtils;





@SpringBootApplication
/*@EnableEurekaClient*/
@RestController
public class UploaderController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploaderController.class);
	
	/**
	 * desc:文件分片上传 
	 */
	@RequestMapping("/uploadDataBlock")
	public void uploadDataBlock(RequestContext requestContext, HttpServletResponse response){
		
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload sfu = new ServletFileUpload(factory);
	     sfu.setHeaderEncoding("utf-8");
	     String savePath = "d:/";//request.getRealPath("/");
	     savePath = new File(savePath) + "/upload/";
	     
	     String fileMd5 = null;// 小于10m的文件FileItem.getFieldName()是得不到chunk的，所以此处不能是空字符串，否则创建文件时会出错
	     String chunk = null;
	     String fileName = null;
	     
	     try {
	    	 
	    	 List<FileItem> items =sfu.parseRequest(requestContext);
	    	 for (FileItem item : items) {
	    		 fileName = item.getName();
	    		 
	    		 if (!item.isFormField()) {
	    			 //判断表单域
	    			  String fieldName = item.getFieldName();
	                  if (fieldName.equals("fileMd5")) {
	                	  fileMd5 = item.getString("utf-8");
	                  }
	                  if (fieldName.equals("chunk")) {
	                	  chunk = item.getString("utf-8");
	                  }
	    			 FileUtils.copyInputStreamToFile(item.getInputStream(), fileName,savePath);
	    		 }
	    	 }
	    	 
	    	 
	     }catch(Exception e){
	    	 logger.info("file upload failuer");
	    	 logger.error("file upload failure:", e);
	     }

	}
	
	
	/**
	 * 文件块合并 
	 */
	
	

}
