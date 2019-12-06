package com.cmit.lee.reactor;

import java.util.function.Supplier;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 15:46
 */
public class Driver {
    Publicsher publisher;
    Publicsher tail;// 指向尾部订阅者

    public Driver createPublisher(Supplier supplier) {
        this.publisher = DefaultPublisher.create(supplier);
        this.tail = publisher;
        return this;
    }

    public Driver addMiddleOperation(DefaultMiddler middler) {
        this.tail.subscribe(middler);
        this.tail = (Publicsher) middler;
        return this;
    }

    public void addSubscriberAndPublish(Subscriber subscriber) {
        this.tail.subscribe(subscriber);
        this.publish();
    }

    private void publish() {
        this.publisher.publish();
    }
}
