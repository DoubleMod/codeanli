package com.code.anli.constant;

/**
 * 固定参数名、固定redis的key名等
 * @author mayn
 *
 */
public class KeyPrefixConstants {

	/**
	 * 请求header中的token的key名
	 */
	public static final String TOKEN_NAME_IN_HEADER = "token";

	/**
	 * redis中，web管理端用户登录后token的key名的前缀，后接用户id
	 */
	public static final String REDIS_WEB_TOKEN_KEY_PREFIX = "web:token:";

	/**
	 * redis中，小程序端用户登录后token的key名的前缀
	 */
	public static final String REDIS_APPLET_TOKEN_KEY_PREFIX = "applet:token:";
	/**
	 * redis中，app端的接口缓存的key名的前缀
	 */
	public static final String REDIS_APP_TOKEN_KEY_PREFIX = "app:token:";
	/**
	 * redis中，Web短信验证码前缀
	 */
	public static final String REDIS_WEB_SMS_VERIFY_KEY_PREFIX = "web:sms:verify:";
	/**
	 * redis中，app短信验证码前缀
	 */
	public static final String REDIS_APP_SMS_VERIFY_KEY_PREFIX = "app:sms:verify:";
	/**
	 * redis中，applet短信验证码前缀
	 */
	public static final String REDIS_APPLET_REGISTER_VERIFY_KEY_PREFIX = "applet:register:verify:";
	/**
	 * redis中，短信限制前缀
	 */
	public static final String REDIS_SMS_LIMIT_KEY_PREFIX = "applet:sms:limit:";
	/**
	 * redis中，门禁修改密码
	 */
	public static final String REDIS_SMS_MODIFY_DOOR_PASSWORD_KEY_PREFIX = "applet:sms:door:password:";
	/**
	 * redis中，门禁邀请
	 */
	public static final String REDIS_SMS_DOOR_INVATE_KEY_PREFIX = "applet:sms:door:invate:";
	/**
	 * redis中，applet修改手机号
	 */
	public static final String REDIS_APPLET_MODIFYPHONE_VERIFY_KEY_PREFIX = "applet:modifyphone:verify:";
	/**
	 * redis中，搜索记录缓存
	 */
	public static final String REDIS_APPLET_SEARCHHISTORY_KEY_PREFIX = "applet:history:";

}
