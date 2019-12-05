package com.cmit.lee.reactive;

import java.util.function.Function;

/**
 * 为{@link Publisher#map(Function)}的下游操作提供消息发布的途径，保证数据（消息）能传递到下游操作<br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 15:11
 */
public class PublishMap<T, U> implements PublishSource<U> {
    /** 上游发布者 */
    PublishSource<T> upstream;
    Function<T, U> mapper;

    public PublishMap(PublishSource<T> upstream, Function<T, U> mapper) {
        this.upstream = upstream;
        this.mapper = mapper;
    }

    @Override
    public void subscribe(Subscriber<U> subscriber) {
        // 利用MapSubscriber将下游订阅者封装起来形成一个新的Subscriber
        MapSubscriber<T, U> mapSubscriber = new MapSubscriber<T, U>(subscriber, this.mapper);
        // 下游订阅者订阅我时，我也去订阅我的上游PublishSource
        // 所以上游调用我的onNext等方法时，将会进入到MapSubscriber#onNext等方法
        this.upstream.subscribe(mapSubscriber);
    }

    /**
     *
     * @param <T>
     * @param <U>
     */
    static class MapSubscriber<T, U> implements Subscriber<T> {
        /** 下游订阅者 */
        Subscriber<U> downstream;
        Function<T, U> mapper;

        public MapSubscriber(Subscriber<U> actualSubscriber, Function<T, U> mapper) {
            this.downstream = actualSubscriber;
            this.mapper = mapper;
        }

        @Override
        public void onNext(T value) {
            // 当上游发布者调用onNext方法时，我将先执行mapper方法，将数据进行一次转换
            U apply = mapper.apply(value);
            // 然后我继续调用下游订阅者的onNext方法，将数据传像下游
            this.downstream.onNext(apply);
        }

        @Override
        public void onCompleted() {
            this.downstream.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            this.downstream.onError(e);
        }
    }
}
