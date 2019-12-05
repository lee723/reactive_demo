package com.cmit.lee.reactive;

import java.util.function.Supplier;

/**
 * 发布者，消息发布者<br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 12:57
 */
public class Publisher<T> {
    Supplier<T> datasoure;

    private Publisher(Supplier<T> supplier) {
        this.datasoure = supplier;
    }

    public static <T> Publisher<T> create(Supplier<T> supplier) {
        return new Publisher<>(supplier);
    }

    public void subscribe(Subscriber<T> subscriber) {
        try {
            T value = datasoure.get();// 达到延时操作的目的
            subscriber.onNext(value);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }
}
