package com.code.anli.controller;

import com.code.anli.constant.Const;
import com.code.anli.constant.KeyPrefixConstants;
import com.fmc.applet.constants.ResponseState;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * 通用返回
 */
public class BaseController {

	/**
	 * 用于无数据返回的“成功”，如删除
	 * @return
	 */
	protected Map success(){
        Map<String,Object> resultMap = new HashMap<>(Const.TWO);
    	resultMap.put("state", ResponseState.SUCCESS.getState());
        resultMap.put("msg", ResponseState.SUCCESS.getMsg());
        return resultMap;
    }

	/**
	 * 用于有数据返回的“成功”，如列表查询、需要id的新增
	 * @param data
	 * @return
	 */
	protected Map success(Object data){
		Map<String,Object> resultMap = new HashMap<>(Const.THREE);
    	resultMap.put("state", ResponseState.SUCCESS.getState());
        resultMap.put("msg", ResponseState.SUCCESS.getMsg());
        resultMap.put("data", data);
        return resultMap;
    }

	/**
	 * 用于报错类的“失败”
	 * @return
	 */
	protected Map fail() {
		Map<String,Object> resultMap = new HashMap<>(Const.TWO);
        resultMap.put("state", ResponseState.ERROR.getState());
        resultMap.put("msg", ResponseState.ERROR.getMsg());
        return resultMap;
    }

	/**
	 * 用于自定义提示语的“失败”
	 * @param msg
	 * @return
	 */
	protected Map fail(String msg) {
		Map<String,Object> resultMap = new HashMap<>(Const.TWO);
        resultMap.put("state", ResponseState.ERROR.getState());
        resultMap.put("msg", msg);
        return resultMap;
    }

	/**
	 * 用于有规范错误码的“失败”
	 * @param state
	 * @return
	 */
	protected Map fail(ResponseState state) {
		Map<String,Object> resultMap = new HashMap<String,Object>(Const.TWO);
        resultMap.put("state", state.getState());
        resultMap.put("msg", state.getMsg());
        return resultMap;
    }
	
	/**
	 * 从请求头中获取前端传来的token
	 * @param request
	 * @return
	 */
	protected String getRequestToken(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            //token是否存在
            if(KeyPrefixConstants.TOKEN_NAME_IN_HEADER.equals(key)) {
				return request.getHeader(key);
            }
        }
        return null;
	}
}
