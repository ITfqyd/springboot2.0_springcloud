package com.cxyxs.two;

import java.util.Date;

/**
 * Description：转发请注明来源  程序猿学社 - https://ithub.blog.csdn.net/
 * Author: 程序猿学社
 * Date:  2020/2/17 21:43
 * Modified By:
 */
public class MyThreadRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("小二在开发中,通过实现Runnable接口方式实现：" + new Date());
        }
    }
}
