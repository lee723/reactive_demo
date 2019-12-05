package com.cmit.lee.reactor;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 14:26
 */
public interface Publicsher<T> {

    /**
     * 构建订阅关系
     * @param subscriber
     */
    void subscribe(Subscriber<T> subscriber);


    /**
     * 取消订阅关系
     * @param subscriber
     */
    // void unSubscribe(Subscriber<T> subscriber);

    /**
     * 发布消息
     */
    void publish();
}
