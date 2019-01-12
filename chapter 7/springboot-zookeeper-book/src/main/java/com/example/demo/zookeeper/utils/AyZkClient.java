package com.example.demo.zookeeper.utils;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class AyZkClient {

    public static void main(String[] args) throws Exception{

        //服务地址和端口
        String zkServers = "127.0.0.1:2181";
        //连接超时时间
        int connectionTimeout = 3000;
        //创建ZkClient对象
        ZkClient zkClient = new ZkClient(zkServers, connectionTimeout);
        //定义节点名称
        String path = "/ay";
        //若节点已经存在,则删除
        if (zkClient.exists(path)) {
            zkClient.delete(path);
        }
        //创建持久节点
        zkClient.createPersistent(path);
        //节点写入数据
        zkClient.writeData(path, "hello world");
        //节点读取数据
        String data = zkClient.readData(path, true);
        System.out.println(data);
        //注册监听器,监听数据变化
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("handleDataChange,dataPath:" + dataPath + " data:" + data);
            }
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handleDataDeleted,dataPath:" + dataPath);
            }
        });
        //修改数据
        zkClient.writeData(path, "hello ay");
        //删除节点
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
