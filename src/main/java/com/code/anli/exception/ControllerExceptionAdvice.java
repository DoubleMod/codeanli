package com.code.anli.exception;


import com.code.anli.controller.BaseController;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * controller 增强器
 *
 * @author wth
 * @since 2019/7/16
 */

@ControllerAdvice
public class ControllerExceptionAdvice extends BaseController {
    private static final Logger logger = Logger.getLogger(ControllerExceptionAdvice.class.getName());



    /**
     * 全局异常捕捉处理 捕获所有异常
     * @param e e
     * @return return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return fail(com.fmc.applet.constants.ResponseState.ERROR_NETWORK_INFO);
    }




    /**
     * 拦截捕捉sql 语法错误 MySQLSyntaxErrorException
     *
     * @param e e
     * @return return
     */
    @ResponseBody
    @ExceptionHandler(value = MySQLSyntaxErrorException.class)
    public Map mySqlSyntaxErrorException(MySQLSyntaxErrorException e) {
        logger.error(e.getMessage());
        e.printStackTrace();
        return fail(com.fmc.applet.constants.ResponseState.ERROR_SQL_SYNTAX);
    }

}
