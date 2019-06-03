//package com.example.demo.call;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// * 描述：基于Future异步调用伪代码
// * @author ay
// * @date 2019-05-01
// */
//public class FutureSynCall {
//
//
//    final static ExecutorService executor = Executors.newFixedThreadPool(2);
//
//    public static void main(String[] args){
//
//        RpcService rpcService = new RpcService();
//        HttpService httpService = new HttpService();
//        Future<Object> future1 = null;
//        Future<Object> future2 = null;
//        try{
//            future1 = executor.submit(() ->{
//
//                rpcService.getRpcResult();
//            });
//
//            future2 = executor.submit(() ->{
//
//                Object httpResult = httpService.getHttpResult();
//            });
//
//            //耗时20ms
//            Object result1 = future1.get(3000);
//            //耗时30ms
//            Object result2 = future2.get(3000);
//
//            //总耗时30ms
//        }catch (Exception e){
//            if(future1 != null){
//                future1.cancel(Boolean.TRUE);
//            }
//            if(future2 != null){
//                future2.cancel(Boolean.TRUE);
//            }
//        }
//
//    }
//}
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
//
///**
// * Http服务
// */
//class HttpService{
//
//    public Object getHttpResult() throws Exception{
//        //调用远程方法，耗时30ms，使用sleep模拟
//        //Thread.sleep(30);
//        return new Object();
//    }
//}
