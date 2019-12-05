package com.cmit.lee.reactor;

import java.util.List;
import java.util.function.Supplier;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 14:33
 */
public class DefaultPublisher<T> extends AbstractPublisher<T> {


    Supplier<T> supplier;

    private DefaultPublisher(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Publicsher<T> create(Supplier<T> supplier) {
        return new DefaultPublisher<>(supplier);
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void publish() {
        try {
            T value = this.supplier.get();
            this.subscriber.onNext(value);
            this.subscriber.onCompleted();
        } catch (Throwable e) {
            this.subscriber.onError(e);
        }
    }
}
