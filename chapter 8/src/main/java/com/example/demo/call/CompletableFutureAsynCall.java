package com.example.demo.call;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 描述：CompletableFuture
 *
 * @author ay
 * @date 2019-05-01
 */
public class CompletableFutureAsynCall {


    public static void main(String[] args) throws Exception{
        RpcService1 rpcService = new RpcService1();
        RpcService2 rpcService2 = new RpcService2();
        RpcService3 rpcService3 = new RpcService3();

        CompletableFuture<Object> future1 = rpcService.getRpcResult();
        CompletableFuture<Object> future2 = rpcService2.getRpcResult();
        CompletableFuture<Object> future3 = rpcService3.getRpcResult();

        CompletableFuture<List> list = CompletableFuture.allOf(future1, future2, future3)
               .thenApplyAsync((Void) ->{
                   List result = new ArrayList();
                   try{
                       result.add(future1.get());
                       result.add(future2.get());
                       result.add(future3.get());
                       return result;
                   }catch (Exception e){
                       return Collections.EMPTY_LIST;
                   }
               }).exceptionally(e ->{
                   //处理异常
                    return null;
               });
    }
}

/**
 * RPC服务1
 */
class RpcService1{

    public CompletableFuture<Object> getRpcResult() throws Exception{
        //调用远程方法，耗时20ms，使用sleep模拟
        //Thread.sleep(20);
        CompletableFuture<Object> completableFuture = null;
        return completableFuture;
    }
}


/**
 * RPC服务2
 */
class RpcService2{

    public CompletableFuture<Object> getRpcResult() throws Exception{
        //调用远程方法，耗时20ms，使用sleep模拟
        //Thread.sleep(20);
        CompletableFuture<Object> completableFuture = null;
        return completableFuture;
    }
}

/**
 * RPC服务3
 */
class RpcService3{

    public CompletableFuture<Object> getRpcResult() throws Exception{
        //调用远程方法，耗时20ms，使用sleep模拟
        //Thread.sleep(20);
        CompletableFuture<Object> completableFuture = null;
        return completableFuture;
    }
}



