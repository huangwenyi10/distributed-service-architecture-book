//package com.example.demo.call;
//import io.netty.util.concurrent.Future;
//import io.netty.util.concurrent.FutureListener;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 描述：Future-Listener模式
// * @author ay
// * @date 2019-05-01
// */
//public class FutureListenerAsynCall {
//
//    final static ExecutorService executor = Executors.newFixedThreadPool(2);
//
//    public static void main(String[] args){
//
//        RpcService rpcService = new RpcService();
//        Future<Object> future = null;
//        try{
//            future = executor.submit(() ->{
//                rpcService.getRpcResult();
//            });
//            MyFutureListener myFutureListener = new MyFutureListener();
//            future.addListener(myFutureListener);
//        }catch (Exception e){
//            if(future != null){
//                future.cancel(Boolean.TRUE);
//            }
//        }
//    }
//}
//
///**
// * 自定义FutureListener实现类
// */
//class MyFutureListener implements FutureListener{
//
//    @Override
//    public void operationComplete(Future future) throws Exception {
//        Object obj = future.get();
//        //继续执行相关的业务逻辑
//    }
//}
//
//
///**
// * RPC服务
// */
//class RpcService{
//
//    public Object getRpcResult() throws Exception{
//        //调用远程方法，耗时20ms，使用sleep模拟
//        //Thread.sleep(20);
//        return new Object();
//    }
//}
