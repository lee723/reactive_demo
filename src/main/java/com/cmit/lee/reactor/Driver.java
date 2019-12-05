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

    public Driver addLast(Subscriber subscriber) {
        this.tail.subscribe(subscriber);
        // 当订阅者是中间操作时，需要将尾部后移
        if (subscriber instanceof DefaultMiddler) {
            this.tail = (Publicsher) subscriber;
        }
        return this;
    }

    public void publish() {
        this.publisher.publish();
    }
}
