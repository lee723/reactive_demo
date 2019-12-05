package com.cmit.lee.reactor;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 14:29
 */
public interface Subscriber<T> {
    void onNext(T value);
    void onCompleted();
    void onError(Throwable e);
}
