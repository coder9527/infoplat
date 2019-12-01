package com.info.baseService.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;

public class HDFSUtil {

	private static FileSystem hdfs;
	
	private static final String fdfsURI="hdfs://192.168.198.137:9000/";
	
	
	/**
	 * @return 得到hdfs的连接 FileSystem类
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static FileSystem getFileSystem() throws URISyntaxException, IOException, InterruptedException {
		// 获取FileSystem类的方法有很多种，这里只写一种
		Configuration config = new Configuration();
		URI uri = new URI(fdfsURI);
		hdfs= FileSystem.get(uri, config, "root");
		return hdfs;
		
	}
	
	
	
	/**
	 * 读取本地文件到HDFS系统, 保证文件格式是utf-8
	 * 
	 * @param localFilename
	 * @param hdfsPath
	 * @return
	 */
	public boolean uploadToHDFS(String fileName, String hdfsPath,InputStream is) {
		try {
			// 如果路径不存在就创建文件夹
			mkdir(hdfsPath);
			//File file = new File(localFilename);
			//FileInputStream is = new FileInputStream(file);
 
			// 如果hdfs上已经存在文件，那么先删除该文件
			if (this.checkFileExist(hdfsPath + "/" + fileName)) {
				delete(hdfsPath + "/" + fileName);
			}
 
			Path f = new Path(hdfsPath + "/" + fileName);
 
			FSDataOutputStream os = hdfs.create(f, true);
			byte[] buffer = new byte[10240000];
			int nCount = 0;
 
			while (true) {
				int bytesRead = is.read(buffer);
				if (bytesRead <= 0) {
					break;
				}
 
				os.write(buffer, 0, bytesRead);
				nCount++;
				if (nCount % (100) == 0)
					System.out.println((new Date()).toLocaleString() + ": Have move " + nCount + " blocks");
			}
 
			is.close();
			os.close();
			System.out.println((new Date()).toLocaleString() + ": Write content of file " + fileName
					+ " to hdfs file " + f.getName() + " success");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 从hdfs下载到本地
	 * 
	 * @param hdfsFilename
	 * @param localPath
	 * @return
	 */
	public boolean downloadFromHdfs(String hdfsFilename, String localPath) {
		try {
			Path f = new Path(hdfsFilename);
 
			FSDataInputStream dis = hdfs.open(f);
			File file = new File(localPath + "/" + f.getName());
			FileOutputStream os = new FileOutputStream(file);
 
			byte[] buffer = new byte[1024000];
			int length = 0;
			while ((length = dis.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
 
			os.close();
			dis.close();
 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	


	
	/**
	 * 创建文件夹
	 * 
	 * @param dirName
	 * @return
	 */
	public boolean mkdir(String dirName) {
		if (checkFileExist(dirName))
			return true;
		try {
			Path f = new Path(dirName);
			System.out.println("Create and Write :" + f.getName() + " to hdfs");
			return hdfs.mkdirs(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return false;
	}
	
	
	/**
	 * 检查文件或者文件夹是否存在
	 * 
	 * @param filename
	 * @return
	 */
	public boolean checkFileExist(String filename) {
		try {
			Path f = new Path(filename);
			return hdfs.exists(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 删除文件或者文件夹
	 * 
	 * @param src
	 * @throws Exception
	 */
	public void delete(String src) throws Exception {
		Path p1 = new Path(src);
		if (hdfs.isDirectory(p1)) {
			hdfs.delete(p1, true);
			System.out.println("删除文件夹成功: " + src);
		} else if (hdfs.isFile(p1)) {
			hdfs.delete(p1, false);
			System.out.println("删除文件成功: " + src);
		}
	}


	
	
		
		

}
	
	
	
	

