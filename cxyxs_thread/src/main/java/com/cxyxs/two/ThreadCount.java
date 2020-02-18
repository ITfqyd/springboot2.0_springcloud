package com.cxyxs.two;

import java.util.Arrays;

/**
 * Description：转发请注明来源  程序猿学社 - https://ithub.blog.csdn.net/
 * Author: 程序猿学社
 * Date:  2020/2/17 22:58
 * Modified By:
 */
public class ThreadCount {
    public static void main(String[] args) {
        ThreadGroup group =
                Thread.currentThread().getThreadGroup();
        int count = group.activeCount();
        group.list();
    }
}
