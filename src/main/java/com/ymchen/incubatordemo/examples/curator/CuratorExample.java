package com.ymchen.incubatordemo.examples.curator;


import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CuratorExample {

    //会话超时时间
    private final static int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private final static int CONNECTION_TIMEOUT = 3 * 1000;
    //ZooKeeper服务地址
    private static final String SERVER = "localhost:2181";

    //lock锁节点
    private final static String ZK_PATH = "/zk_lock";

    //创建连接实例
    private static CuratorFramework client = null;
    /**
     * baseSleepTimeMs：初始的重试等待时间
     * maxRetries：最多重试次数
     */
    final static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    public static void main(String[] args) throws Exception {
        //创建 CuratorFrameworkImpl实例
        client = CuratorFrameworkFactory.newClient(SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);
        //启动
        client.start();

        //locktest
        //testDistributorLock();

        //leadertest
        //leaderTest();

        //创建永久节点
       //client.create().forPath("/test");
      client.create().forPath("/test/a","a".getBytes());

        //获取某个节点的所有子节点
        System.out.println(client.getChildren().forPath("/test"));
    }

    private static void leaderTest() throws Exception {
        LeaderSelectorListener listener = new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                log.info("thread {} get leader .................", Thread.currentThread().getName());
                Thread.sleep(3000L);
                log.info("thread {} relinquish  leadership ...........", Thread.currentThread().getName());
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                log.info("aaaaaaaaaaaa");
            }
        };

        new Thread(() -> registerSelector(listener), "thread-aa").start();
        new Thread(() -> registerSelector(listener), "thread-bb").start();


        Thread.sleep(20000L);
        log.info("new client ...............................");
        new Thread(() -> registerSelector(listener), "thread-cc").start();

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void registerSelector(LeaderSelectorListener listener) {

        // Ensure path
        try {
            new EnsurePath(ZK_PATH).ensure(client.getZookeeperClient());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LeaderSelector selector = new LeaderSelector(client, ZK_PATH, listener);
        selector.autoRequeue();
        selector.start();
    }


    private static void testDistributorLock() {
        new Thread(() -> lockTest(), "thread-1").start();
        new Thread(() -> lockTest(), "thread-2").start();
    }

    private static void lockTest() {
        InterProcessMutex lock = new InterProcessMutex(client, ZK_PATH);
        try {
            if (lock.acquire(5, TimeUnit.SECONDS)) {
                log.info("Thread {} get lock", Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            log.error("lock error:{}", e);
        } finally {
            log.info("Thread {} release lock", Thread.currentThread().getName());
            try {
                lock.release();
            } catch (Exception e) {
                log.info("release error:{}", e);
            }
        }
    }
}
