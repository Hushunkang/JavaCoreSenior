package javacore.senior.knowledge;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * java.util.concurrent.locks.Lock锁的方式实现线程同步以解决线程安全问题
 *
 * @author hskbeginner
 * @create 2020/2/13-19:12
 */
public class ThreadDemoLock {

    public static void main(String[] args) {
        Window2 window2 = new Window2();
        Thread w1 = new Thread(window2);
        Thread w2 = new Thread(window2);
        Thread w3 = new Thread(window2);

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();
    }

}

class Window2 implements Runnable{

    private int ticket = 100;

    //1.创建ReentrantLock类的对象
    private Lock lock = new ReentrantLock();//必须保证多个线程共用一个lock对象，共用同一把锁

    @Override
    public void run() {
        while(true){
            try{
                //2.调用锁定方法
                lock.lock();
                if(ticket > 0){
                    //增大发生重票问题的概率
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                    //增大发生错票问题的概率
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                    ticket--;
                }else{
                    break;
                }
            }finally{
                //3.调用解锁方法
                lock.unlock();
            }
        }
    }

}