package com.code.anli.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.bson.Document;

/**
 * @ClassName MongoDBAppender @Description MongoDB适配文档输出内容定义 @Author Administrator @Date 2019/12/27
 * 15:38 @Version 1.0
 */
public class MongoDBAppender extends MongoDBAppenderBase<ILoggingEvent> {

    public MongoDBAppender() {
        super("loggingEvents");
    }

    @Override
    protected Document toMongoDocument(ILoggingEvent eventObject) {
        final Document doc = new Document();
        long timeStamp = eventObject.getTimeStamp();
        DateTime date = DateUtil.date(timeStamp);
        doc.append("date", DateUtil.formatDateTime(date));
        doc.append("source", source);
        StackTraceElementProxy[] stackTraceElementProxyArray =
                eventObject.getThrowableProxy().getStackTraceElementProxyArray();
        StringBuilder builder = new StringBuilder();
        for (StackTraceElementProxy stackTraceElementProxy : stackTraceElementProxyArray) {
            System.out.println("打印: " + stackTraceElementProxy.getSTEAsString());
            builder.append(stackTraceElementProxy.getSTEAsString()).append("    \n  ");
        }
        doc.append("detail", builder.toString());
        doc.append("level", eventObject.getLevel().toString());
        doc.append("logger", eventObject.getLoggerName());
        doc.append("thread", eventObject.getThreadName());
        doc.append("message", eventObject.getFormattedMessage());
        if (eventObject.getMDCPropertyMap() != null && !eventObject.getMDCPropertyMap().isEmpty()) {
            doc.append("mdc", eventObject.getMDCPropertyMap());
        }
        // ...

        return doc;
    }
}
