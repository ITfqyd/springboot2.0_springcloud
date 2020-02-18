package com.cxyxs.two;

import java.util.Date;

/**
 * Description：第一种：通过继承的方式,实现多线程
 *  转发请注明来源  程序猿学社 - https://ithub.blog.csdn.net/
 * Author: 程序猿学社
 * Date:  2020/2/17 21:37
 * Modified By:
 */
public class MyThreadExtend extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("程序猿学社：社长在开发中,通过继承的方式实现:" + new Date());
        }
    }
}
