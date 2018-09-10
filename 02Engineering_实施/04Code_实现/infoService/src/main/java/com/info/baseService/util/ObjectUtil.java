package com.info.baseService.util;

import java.util.Map;
import com.alibaba.fastjson.JSON;

/**
 * Created by liuruijie on 2017/1/17.
 * 对象工具
 */
public class ObjectUtil {
    /**
     * 对象转字典
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object obj){
        return (Map<String, Object>) JSON.toJSON(obj);
    }

    /**
     * 字典转对象
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> T){
        return (T) JSON.parseObject(JSON.toJSONString(map), T);
    }
}
