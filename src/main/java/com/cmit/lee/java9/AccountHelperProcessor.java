package com.cmit.lee.java9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 16:57
 */
public class AccountHelperProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Integer.MAX_VALUE);
    }

    @Override
    public void onNext(String item) {
        String s = AccountHelper.process(item);
        this.submit(s);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
