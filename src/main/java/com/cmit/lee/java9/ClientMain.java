package com.cmit.lee.java9;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.LongStream;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 16:16
 */
public class ClientMain {
    public static void main(String[] args) {
        // 创建发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        // 创建中间过程
        AccountHelperProcessor accountHelperProcessor = new AccountHelperProcessor();
        DBHelperProcessor dbHelperProcessor = new DBHelperProcessor();
        // 构建订阅关系
        publisher.subscribe(accountHelperProcessor);
        accountHelperProcessor.subscribe(dbHelperProcessor);
        dbHelperProcessor.subscribe(new Flow.Subscriber<Integer>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                subscription.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer item) {
                if (item == 1) {
                    Utils.println("数据库插入成功");
                } else {
                    Utils.println("数据库插入失败");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                Utils.println("complete");
            }
        });

        // 发布消息
        publisher.submit(HttpManager.getData("world"));


        Utils.read();
    }


}
