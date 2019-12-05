package com.cmit.lee.reactor;

public class HttpManager {

    public static String getData(String param) {
        /*网络操作*/
        System.out.println("HttpManager#getData");
        return "hello " + param;//简化操作，模拟服务器返回的结果
    }
}