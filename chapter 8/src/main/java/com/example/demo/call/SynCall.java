//package com.example.demo.call;
//
//
///**
// * 描述：同步调用伪代码
// * @author ay
// * @date 2019-05-01
// */
//public class SynCall {
//
//
//    public static void main(String[] args) throws Exception{
//
//        RpcService rpcService = new RpcService();
//        //耗时20ms
//        Object rpcResult = rpcService.getRpcResult();
//
//        HttpService httpService = new HttpService();
//        //耗时30ms
//        Object httpResult = httpService.getHttpResult();
//
//        //总耗时50ms
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
//        Thread.sleep(20);
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
//        Thread.sleep(30);
//        return new Object();
//    }
//}
