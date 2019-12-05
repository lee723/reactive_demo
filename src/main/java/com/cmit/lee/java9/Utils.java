package com.cmit.lee.java9;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 17:18
 */
public class Utils {
    public static <T> void println(T msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss");
        String time = LocalDateTime.now().format(formatter);
        String threadName = Thread.currentThread().getName();
        System.out.println(String.format("%s - %s - %s", time, threadName, msg));
    }

    public static void read() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
