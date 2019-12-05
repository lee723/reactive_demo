package com.cmit.lee.reactive;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 发布者，消息发布者<br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 12:57
 */
public class Publisher<T> implements PublishSource<T> {
    Supplier<T> datasoure;

    Publisher() {}

    private Publisher(Supplier<T> supplier) {
        this.datasoure = supplier;
    }

    public static <T> Publisher<T> create(Supplier<T> supplier) {
        return new Publisher<>(supplier);
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        try {
            T value = datasoure.get();// 达到延时操作的目的
            subscriber.onNext(value);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    /**
     * map（中间操作）实现的关键在于需要串联上游和下游。<br/>
     * 角色上，当map作为上一个操作的下游时，其本质上是一个{@link Subscriber} <br/>
     * 当map作为下一个操作的上游时，其本质上是一个{@link PublishSource}
     * @param mapper
     * @param <R>
     * @return
     */
    public <R> Publisher<R> map(Function<T, R> mapper) {
        // 构建一个PublishSource，供下游订阅，this为上游的PublishSource实例
        PublishMap publishMap = new PublishMap<>(this, mapper);
        return publishMap;
    }
}
