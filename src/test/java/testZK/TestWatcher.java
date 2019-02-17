package testZK;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TestWatcher implements Watcher {
//    private static Logger LOGGER = LoggerFactory.getLogger(TestWatcher.class);
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk;
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/zk-book";
        zk = new ZooKeeper("localhost:2181", 5000, new TestWatcher());
        System.out.println("start connecting.......");
        countDownLatch.await();
        zk.exists(path, true);
        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.setData(path, "abc".getBytes(), -1);
        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.delete(path + "/c1", -1);
        zk.delete(path, -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        try {
//            LOGGER.info("start zookeeper");
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    System.out.println("Node: " + event.getPath() + " Created");
                    zk.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    System.out.println("Node : " + event.getPath() + " DataChanged");
                    zk.exists(event.getPath(), true);

                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    System.out.println("Node : " + event.getPath() + " Deleted");
                    zk.exists(event.getPath(), true);

                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
