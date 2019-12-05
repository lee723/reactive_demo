package com.cmit.lee.java9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 17:00
 */
public class DBHelperProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<String, Integer> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
        Integer result = DBHelper.insert(item);
        this.submit(result);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
