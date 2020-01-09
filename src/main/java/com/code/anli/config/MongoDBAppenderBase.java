package com.code.anli.config;

/**
 * @ClassName MongoDBAppenderBase @Description
 * 15:37 @Version 1.0
 */
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.Collections;

/**
 * MongoDBAppender适配类
 */
public abstract class MongoDBAppenderBase<E> extends UnsynchronizedAppenderBase<E> {

    protected String source;
    private MongoClient mongo;
    private MongoCollection<Document> eventsCollection;
    private String host = "106.54.34.142"; // 地址
    private int port = 27017; // 端口号
    private String dbName = "practice"; // 库名
    private String collectionName = "logdb"; // 集合名
    private String username; // 用户名
    private String password; // 密码
    private int connectionsPerHost = 10; // 空闲线程池中最大链接数
    private int threadsAllowedToBlockForConnectionMultiplier = 5; // 一个线程等待链接可用的最大等待毫秒数
    private int maxWaitTime = 1000 * 60 * 2; // 最长等待时间
    private int connectTimeout;
    private int socketTimeout;
    private int wtimeout;

    MongoDBAppenderBase(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public void start() {
        try {
            connectToMongoDB();
            super.start();
        } catch (UnknownHostException e) {
            addError("Error connecting to MongoDB server: " + host + ":" + port, e);
        }
    }

    private void connectToMongoDB() throws UnknownHostException {
        // 用户名 数据库 密码
        if (username != null && password != null) {
            MongoCredential credential =
                    MongoCredential.createCredential(username, dbName, password.toCharArray());
            mongo =
                    new MongoClient(
                            new ServerAddress(host, port), Collections.singletonList(credential), buildOptions());
        } else {
            mongo = new MongoClient(new ServerAddress(host, port), buildOptions());
        }

        MongoDatabase db = mongo.getDatabase(dbName);
        eventsCollection = db.getCollection(collectionName);
    }

    private MongoClientOptions buildOptions() {
        final MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(connectionsPerHost);
        options.threadsAllowedToBlockForConnectionMultiplier(
                threadsAllowedToBlockForConnectionMultiplier);
        options.maxWaitTime(maxWaitTime);
        options.connectTimeout(connectTimeout);
        options.socketTimeout(socketTimeout);
        options.maxWaitTime(wtimeout);
        return options.build();
    }

    protected abstract Document toMongoDocument(E event);

    @Override
    protected void append(E eventObject) {
        eventsCollection.insertOne(toMongoDocument(eventObject));
    }

    @Override
    public void stop() {
        if (mongo != null) {
            mongo.close();
        }
        super.stop();
    }

    // ... 以下是变量get set 方法 此处省略

    public MongoClient getMongo() {
        return mongo;
    }

    public void setMongo(MongoClient mongo) {
        this.mongo = mongo;
    }

    public MongoCollection<Document> getEventsCollection() {
        return eventsCollection;
    }

    public void setEventsCollection(MongoCollection<Document> eventsCollection) {
        this.eventsCollection = eventsCollection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier =
                threadsAllowedToBlockForConnectionMultiplier;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getWtimeout() {
        return wtimeout;
    }

    public void setWtimeout(int wtimeout) {
        this.wtimeout = wtimeout;
    }
}
