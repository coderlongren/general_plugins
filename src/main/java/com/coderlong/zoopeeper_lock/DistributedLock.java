package com.coderlong.zoopeeper_lock;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *  coderlong
 */
public class DistributedLock implements Watcher, Lock {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLock.class);
    private ZooKeeper zk;
    private String root = "/locks";
    /**
     *  需要竞争的资源
     */
    private String lockName;
    /**
     *  等待前一个锁
     */
    private String waitNode;
    /**
     *  当前的锁
     */
    private String currNode;

    /**
     * 计数器
     */
    private CountDownLatch latch;
    /**
     * 连接zk可允许的最大超时时间
     */
    private int sessionTimeOut = 30000;
    private List<Exception> exception = new ArrayList<Exception>();

    /**
     *  创建分布式锁
     *
     * @param config zk IP + port
     * @param lockName 竞争资源名字
     */
    public DistributedLock(String config, String lockName) {
        lockName = lockName;

        try {
            zk = new ZooKeeper(config, sessionTimeOut, this);

        } catch (IOException e) {
            LOGGER.error("create DistributedLock Error: {}", e);
        }
    }

    public void lock() {

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {

    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent watchedEvent) {

    }
}
