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
   * @return 返回数据
   */
  protected Map<String, Object> success() {
    Map<String, Object> resultMap = new HashMap<>(Const.TWO);
    resultMap.put("state", ResponseState.SUCCESS.getState());
    resultMap.put("msg", ResponseState.SUCCESS.getMsg());
    return resultMap;
  }

  /**
   * 用于有数据返回的“成功”，如列表查询、需要id的新增
   *
   * @param data 参数
   * @return 返回数据
   */
  protected Map<String, Object> success(Object data) {
    Map<String, Object> resultMap = new HashMap<>(Const.THREE);
    resultMap.put("state", ResponseState.SUCCESS.getState());
    resultMap.put("msg", ResponseState.SUCCESS.getMsg());
    resultMap.put("data", data);
    return resultMap;
  }

  /**
   * 用于报错类的“失败”
   *
   * @return 返回数据
   */
  protected Map<String, Object> fail() {
    Map<String, Object> resultMap = new HashMap<>(Const.TWO);
    resultMap.put("state", ResponseState.ERROR.getState());
    resultMap.put("msg", ResponseState.ERROR.getMsg());
    return resultMap;
  }

  /**
   * 用于自定义提示语的“失败”
   *
   * @param msg 返回消息
   * @return 返回数据
   */
  protected Map<String, Object> fail(String msg) {
    Map<String, Object> resultMap = new HashMap<>(Const.TWO);
    resultMap.put("state", ResponseState.ERROR.getState());
    resultMap.put("msg", msg);
    return resultMap;
  }

  /**
   * 用于有规范错误码的“失败”
   *
   * @param state 返回状态
   * @return 返回数据
   */
  protected Map<String, Object> fail(ResponseState state) {
    Map<String, Object> resultMap = new HashMap<>(Const.TWO);
    resultMap.put("state", state.getState());
    resultMap.put("msg", state.getMsg());
    return resultMap;
  }

  /**
   * 从请求头中获取前端传来的token
   *
   * @param request 请求
   * @return 返回数据
   */
  protected String getRequestToken(HttpServletRequest request) {
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      // token是否存在
      if (KeyPrefixConstants.TOKEN_NAME_IN_HEADER.equals(key)) {
        return request.getHeader(key);
      }
    }
    return null;
  }
}
