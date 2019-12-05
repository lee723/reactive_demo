package com.cmit.lee.reactor;

import java.util.function.Function;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 15:09
 */
public class DefaultMiddler<T, R> extends AbstractPublisher<R> implements Subscriber<T> {
    Function<T, R> mapper;

    private DefaultMiddler(Function<T, R> mapper) {
        this.mapper = mapper;
    }

    public static <T, R> DefaultMiddler<T, R> create(Function<T, R> mapper) {
        return new DefaultMiddler<T, R>(mapper);
    }

    @Override
    public void subscribe(Subscriber<R> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onNext(T value) {
        try {
            R r = this.mapper.apply(value);
            this.subscriber.onNext(r);
        } catch (Throwable e) {
            this.onError(e);
        }
    }

    @Override
    public void onCompleted() {
        this.subscriber.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        this.subscriber.onError(e);
    }
}
