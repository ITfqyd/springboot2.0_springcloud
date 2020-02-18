package com.cxyxs.two;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Description：转发请注明来源  程序猿学社 - https://ithub.blog.csdn.net/
 * Author: 程序猿学社
 * Date:  2020/2/17 21:47
 * Modified By:
 */
public class MyThreadCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum+=i;

            System.out.println("小王通过实现Callable接口的方式实现:" + new Date());
        }
        return sum;

    }
}
