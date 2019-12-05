package com.cmit.lee.reactor;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 15:14
 */
public abstract class AbstractPublisher<T> implements Publicsher<T> {
    Subscriber<T> subscriber;

    @Override
    public void publish() {

    }
}
