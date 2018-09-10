package com.info.uploaderService.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	
	
	/**
	 *desc 输入流保存文件
	 *@param fileName 输出文件名
	 *@param filePath 输出文件路径
	 *@param inputStream  文件的输入流
	 */
	
	public static void copyInputStreamToFile(InputStream inputStream,String fileName,String filePath)throws Exception{
		OutputStream outputStream=null;
		
			File fileDirectory = new File(filePath);
			if (!fileDirectory.exists()) {
				fileDirectory.mkdir();
	        }
		try{	
			outputStream = new FileOutputStream(filePath + fileName);
			byte[] buffer = new byte[1024];
			int len = 0;
			
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
			}
			
		}catch(Exception e){
			throw e;
		}finally{
			if(null!=inputStream){
				inputStream.close();
			}
			if(null!=outputStream){
				outputStream.close();
			}
			
		}
		
		
		
	}

}
