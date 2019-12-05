package com.cmit.lee.reactor;

import javax.sound.midi.Soundbank;

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
        Driver driver = new Driver();

        driver.createPublisher(() -> HttpManager.getData("world"))
                .addLast(DefaultMiddler.create(AccountHelper::process))
                .addLast(DefaultMiddler.create(DBHelper::insert))
                .addLast(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                        if (value == 1) {
                            System.out.println("数据插入成功！");
                        } else {
                            System.out.println("数据插入失败!");
                        }
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("任务成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("任务失败，失败原因: " + e.getMessage());
                    }
                });

        // 发布消息
        driver.publish();
    }
}
