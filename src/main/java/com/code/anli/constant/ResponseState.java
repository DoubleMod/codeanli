package com.fmc.applet.constants;


/**
 * 前端通用状态码
 *
 */
public enum ResponseState {
    //基本
    SUCCESS("成功", 200),
    SUCCESS_EMPTY("空空如也",201),
    ERROR("系统错误", 9999),
    ERROR_PARAM("参数异常", 9101),
    ERROR_INFO("获取信息失败",9402),
    ERROR_NETWORK_INFO("网络繁忙，请稍后",9502),
    ERROR_TOKEN_EXPIRED("登录失效",100),
    ERROR_SQL_SYNTAX("sql语法错误",110),
    ERROR_CONTENT_SYNTAX("请输入正确的链接或者淘口令",210),


    //注册、登录、个人设置
    LOGIN_WRONG_USERNAME("无效的账号", 2001),
    LOGIN_WRONG_PASSWORD("密码错误", 2002),
    LOGIN_WRONG_VALICODE("验证码已失效", 2003),
    LOGIN_WRONG_USER_PASSWORD("账号或密码错误", 2004),
    LOGIN_WRONG_USER_PHONE("手机号与账号不匹配", 2005),
    REGISTER_WRONG_EXIST_USER("该手机号已被注册", 2015),
    REGISTER_WX_USERINFO_FAIL("获取微信信息失败", 2101),
    REGISTER_OPENID_NOT_EXIST("未找到用户信息", 2102),

    //短信验证相关
    SMS_VERIFY_ERROR("短信验证码发送失败",5001),
    SMS_VERIFY_SUCCESS("短信验证码发送成功",5000),
    VERIFY_CODE_SUCCESS("验证码正确",5010),
    VERIFY_CODE_ERROR("验证码错误",5011),
    VERIFY_CODE_VALICODE("验证码已失效",5012),
    VERIFY_CODE_LIMIT("短信验证码已上限",5015),


    //其他模块。。。
    WX_AUTHORIZATION_RETRY("微信授权异常,请重新登录", 6001),
    STATUS_OPERATE_DISCREPANCY("操作状态异常,请稍后再试", 7001),
    OPERATE_PERMISSION_ILLEGALITY("操作权限非法", 7001),
    PAYMENT_FAILED("服务繁忙，请稍后再试", 9001),
    PAYMENT_INVALID_AMOUNT("退款订单无法进行支付,请联系管家处理", 9100),
    INTELLIGENT_DOOR_ERRPR("操作失败，请稍后再试。", 8000),
    INTELLIGENT_DOOR_SCREAT_ERRPR("未配置门禁秘钥，请联系管家处理。", 8001),
    ALREADY_COLLECT_REPEAT("请勿重复收房", 9200),
    NOTIFY_PAY_CALLBACK_ERROR("核心回调执行失败", 9300),

	
	;
	
	
    private String msg;
    private int state;

    private ResponseState(String msg, int state) {
        this.msg = msg;
        this.state = state;
    }

    // 根据state获取msg
    public static String getMsg(int state) {
        for (ResponseState c : ResponseState.values()) {
	        if (c.getState() == state) {
	            return c.msg;
	        }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
