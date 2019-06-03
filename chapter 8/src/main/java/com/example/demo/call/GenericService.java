package com.example.demo.call;

/**
 * 描述：泛化接口
 *
 * @author ay
 * @date 2019-05-01
 */
public interface GenericService {

    Object invoke(Object instance, String methodName,
                  Object... arguments) throws Exception;



    public static void main(String[] args) throws Exception{

        GenericService productService = (GenericService) applicationContext.getBean("productService");
        Object result = productService.invoke(productService, "sayHello", new String[] { "java.lang.String" });

    }
}

/**
 * 商品实现类
 */
class ProductService implements GenericService{

    @Override
    public Object invoke(Object instance, String methodName,
                         Object... arguments) throws Exception {
        //methodName，调用ProductService类中的对应方法

        return null;
    }
}


