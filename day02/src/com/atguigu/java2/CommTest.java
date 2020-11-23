package com.atguigu.java2;

/**
 * 线程交替打印
 */
public class CommTest {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread1);
        t1.start();
        t2.start();
    }


}


/**
 * 线程1 进来   释放锁
 *
 * 线程2 进来  唤醒线程1  但是线程2锁住  线程2 释放锁
 *
 * 线程1 进来   唤醒
 */
class MyThread implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (number <= 100) {
                notify();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + number);
                number++;
                try {
                   wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}