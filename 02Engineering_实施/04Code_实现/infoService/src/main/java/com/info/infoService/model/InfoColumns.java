package com.info.infoService.model;

import java.io.Serializable;

public class InfoColumns implements Serializable{
	
	private  String id;
    
	
    private  String columnName;
   
	
    private  String deleteFlag;
   
	 //是否是固定栏目，0是，1不是
    private  String columnType;
   
	
    private  String orderNum;
   
    
    public  String  getId(){
    		return  this.id;
    };
    public  void  setId(String id){
    	this.id=id;
    }  
   
    public  String  getColumnName(){
    		return  this.columnName;
    };
    public  void  setColumnName(String columnName){
    	this.columnName=columnName;
    }  
   
    public  String  getDeleteFlag(){
    		return  this.deleteFlag;
    };
    public  void  setDeleteFlag(String deleteFlag){
    	this.deleteFlag=deleteFlag;
    }  
   
    public  String  getColumnType(){
    		return  this.columnType;
    };
    public  void  setColumnType(String columnType){
    	this.columnType=columnType;
    }  
   
    public  String  getOrderNum(){
    		return  this.orderNum;
    };
    public  void  setOrderNum(String orderNum){
    	this.orderNum=orderNum;
    }  

}
