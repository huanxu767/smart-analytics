package com.sncfc.analytic.controller.common;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 
 * 项目通用的Controller基类，留作扩展
 * 
 */
public class BaseController {

	  /** 
	 * 从request中获得参数Map，并返回可读的Map 
	 * 重复的不可读
	 * @param request 
	 * @return 
	 */  
	@SuppressWarnings("unchecked")  
	public  Map<String,String> getParameterMap(HttpServletRequest request) {
	    // 参数Map  
	    Map properties = request.getParameterMap();  
	    // 返回值Map  
	    Map returnMap = new HashMap();  
	    Iterator entries = properties.entrySet().iterator();  
	    Map.Entry entry;  
	    String name = "";  
	    String value = "";  
	    while (entries.hasNext()) {  
	        entry = (Map.Entry) entries.next();  
	        name = (String) entry.getKey();  
	        Object valueObj = entry.getValue();  
	        if(null == valueObj){  
	            value = "";  
	        }else if(valueObj instanceof String[]){  
	            String[] values = (String[])valueObj;  
	            for(int i=0;i<values.length;i++){  
	                value = values[i] + ",";  
	            }  
	            value = value.substring(0, value.length()-1);  
	        }else{  
	            value = valueObj.toString();  
	        }  
	        returnMap.put(name, value);  
	    }  
	    return returnMap;  
	}  
}
