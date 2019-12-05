package com.cmit.lee.reactive;

/**
 * 发布者，消息发布者<br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 14:11
 */
public interface Subscriber<T> {
    void onNext(T value);
    void onCompleted();
    void onError(Throwable e);
}
