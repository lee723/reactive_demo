package com.cmit.lee.reactive;

/**
 * 发布者，消息发布者<br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/4 15:16
 */
public interface PublishSource<T> {
    /**
     * 通过该方法像订阅者传递数据
     * @param subscriber
     */
    void subscribe(Subscriber<T> subscriber);
}
