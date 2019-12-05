package com.cmit.lee.java9;

public class AccountHelper {

    public static String process(String data){
        Utils.println("AccountHelper#process");
        return data.toUpperCase();
    }
}