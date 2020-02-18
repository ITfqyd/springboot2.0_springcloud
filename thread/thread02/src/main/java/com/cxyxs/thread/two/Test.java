package com.cxyxs.two;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试类
 * Description：转发请注明来源  程序猿学社 - https://ithub.blog.csdn.net/
 * Author: 程序猿学社
 * Date:  2020/2/17 21:54
 * Modified By:
 */
public class Test {
    public static void main(String[] args) {
        //第一种方式
        MyThreadExtend threadExtend = new MyThreadExtend();
        threadExtend.start();

        //第二种方式
        MyThreadRunnable runnable = new MyThreadRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        //第三种方式
        //匿名内部类-第一种
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过匿名内部类的方式第一种实现!");
            }
        }).start();

        //匿名内部类-第二种
        new Thread() {
            @Override
            public void run() {
                System.out.println("通过匿名内部类的方式第二种实现!");
            }
        }.start();

        //第四种 通过Callable实现多线程
        MyThreadCallable callable = new MyThreadCallable();
        FutureTask<Integer> result = new FutureTask<Integer>(callable);
        new Thread(result).start();
        try {
            Integer sum =  result.get();
            System.out.println("计算结果:"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
