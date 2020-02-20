package javacore.senior.practice;

/**
 *
 * 实现多线程的方式二：实现java.lang.Runnable接口
 *
 * @author hskbeginner
 * @create 2020/2/12-16:34
 */
public class ThreadDemoRunnable {

    public static void main(String[] args) {
        Runnable mineThread = new MineThread();
        //创建一个线程
        Thread thread1 = new Thread(mineThread);
        thread1.setName("线程一");
        //启动上面创建的线程
        thread1.start();


        //再创建并启动一个线程
        Thread thread2 = new Thread(mineThread);
        thread2.setName("线程二");
        thread2.start();
    }

}

class MineThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

}
