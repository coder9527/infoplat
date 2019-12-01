package com.info.uploaderService.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.info.baseService.util.HDFSUtil;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;

@SpringBootApplication
/*@EnableEurekaClient*/
@RestController
@ResponseBody
public class HFDFSUploaderController {
	
	private static final Logger logger = LoggerFactory.getLogger(HFDFSUploaderController.class);
	
	
	
	@RequestMapping(value="/uploadFDFS",headers = "Content-Type= multipart/form-data")
	public String uploadDataBlock(HttpServletRequest request){
		System.out.println("uploadFDFS----->>>");
		
		
		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request); 
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)    
                .getFiles("file"); 
		
		
		MultipartFile file=files.get(0);
		
		String fileName=file.getOriginalFilename();
		String filePath="/demo";
		
		HDFSUtil hdfsUtil=new HDFSUtil();
		
		try {
			hdfsUtil.getFileSystem();
			hdfsUtil.uploadToHDFS(fileName, filePath,file.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("uploadFDFS success");
		
		
		/*
		String uri="hdfs://192.168.198.137:9000/";
		// 创建Configuration对象
       
       // 创建	FileSystem对象

       FileSystem fs;
       FileStatus[] status = null;
       Path path=new Path(uri);
       
       
       
		try {
			Configuration conf=new Configuration();
			
			
			fs = path.getFileSystem(conf);
			System.out.println("uploadFDFS----->>>"+fs.toString());
			
			status = fs.listStatus(path);//获取文件目录
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (FileStatus file : status){
			System.out.println("---->>>>>>>:"+file.getPath());
		}
		
	
		
		return "";*/
		return filePath+"/"+file.getOriginalFilename();
	}
	
	
	@RequestMapping("/downloadFDFS")
	public String downloadFDFS(HttpServletRequest request, HttpServletResponse response,@RequestParam String filePath) throws IOException, IllegalArgumentException, URISyntaxException, InterruptedException{
		System.out.println("download from FDFS----->>>");
		
		HDFSUtil hdfsUtil=new HDFSUtil();
		
		Path path=new Path(filePath);
		FSDataInputStream dis=hdfsUtil.getFileSystem().open(path);
		
		
		response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(path.getName(), "UTF-8"));
		
		
		OutputStream outStream=response.getOutputStream();
	
		byte[] buffer = new byte[1024000];
		int length = 0;
		while ((length = dis.read(buffer)) > 0) {
			//os.write(buffer, 0, length);
			outStream.write(buffer, 0, length);
		}

		outStream.close();
		dis.close();
		
		//Path f = new Path(hdfsFilename);
	    return "";
	}
		 
	
}
