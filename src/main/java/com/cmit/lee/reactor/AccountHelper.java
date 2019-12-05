package com.cmit.lee.reactor;

public class AccountHelper {

    public static String process(String data){
        System.out.println("AccountHelper#process");
        return data.toUpperCase();
    }
}