package com.cmit.lee.reactive;

public class AccountHelper {

    public static String process(String data){
        System.out.println("AccountHelper#process");
        return data.toUpperCase();
    }
}