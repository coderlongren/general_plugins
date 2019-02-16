package com.coderlong.zoopeeper_lock;
import org.apache.zookeeper.ZooKeeper;

/**
 *  coderlong
 */
public class DistributedLock {
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


}
