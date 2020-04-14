package javacore.senior.practice;

/**
 *
 * 实现多线程的方式一：继承java.lang.Thread类
 * 练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 *
 * @author hskbeginner
 * @create 2020/2/11
 */
public class ThreadDemoThread {

    public static void main(String[] args) {
        //开启了三个线程，每个线程做的事情都不一样，三个run方法
        Thread thread1 = new MyThread1();
        Thread thread2 = new MyThread2();
        thread1.start();
        thread2.start();

        //创建了一个继承自Thread类的匿名类的匿名对象---匿名的***使用起来方便，多用于一次性使用
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 200; i++) {
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                }
//            }
//        }.start();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }).start();
    }

}

class MyThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}

class MyThread2 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}
