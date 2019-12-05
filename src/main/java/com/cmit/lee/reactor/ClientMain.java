package com.cmit.lee.reactor;

/**
 * <br/>
 *
 * @author liyuanchang
 * @version V1.0
 * @email liyuanchang@chinamobile.com
 * @date 2019/12/5 15:30
 */
public class ClientMain {
    public static void main(String[] args) {
        Publicsher<String> publicsher = DefaultPublisher.create(() -> HttpManager.getData("world"));
        DefaultMiddler<String, String> processStep = DefaultMiddler.create(AccountHelper::process);
        DefaultMiddler<String, Integer> insertStep = DefaultMiddler.create(DBHelper::insert);

        // 构建链式关系
        publicsher.subscribe(processStep);
        processStep.subscribe(insertStep);
        insertStep.subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer value) {
                if (value == 1) {
                    System.out.println("数据插入成功");
                } else {
                    System.out.println("数据插入失败");
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("任务完成");
            }

            @Override
            public void onError(Throwable e) {

            }
        });

        // 触发数据流动
        publicsher.publish();
    }
}
