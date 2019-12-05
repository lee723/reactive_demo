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
        }).map(v -> {
            return AccountHelper.process(v);
        }).map(v -> {
            return DBHelper.insert(v);
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer value) {
                if (value == 1) {
                    System.out.println("插入数据库成功");
                } else {
                    System.out.println("插入数据库失败");
                }
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
