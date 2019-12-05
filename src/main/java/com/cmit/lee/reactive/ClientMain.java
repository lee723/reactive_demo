package com.cmit.lee.reactive;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 14:38
 */
public class ClientMain {
    public static void main(String[] args) {
        Publisher.create(() -> {
           return HttpManager.getData("world");
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String value) {
                DBHelper.insert(value);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
