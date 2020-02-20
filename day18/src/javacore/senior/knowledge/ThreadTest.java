package javacore.senior.knowledge;

/**
 * @author hskbeginner
 * @create 2020/2/11-22:06
 */
public class ThreadTest{

    public static void main(String[] args) {
        Thread thread = new MyThread();
//        thread.run();//这并不是开启一个线程
        thread.start();//start a thread
        //再开启一个线程
        //不可以让已经开启的线程再去start，否则会报java.lang.IllegalThreadStateException
//        thread.start();
        Thread thread1 = new MyThread();
        thread1.start();//这样才是创建一个新的线程并开启

        for (int i = 0; i < 100; i++) {
            if(i%2 != 0){
                System.out.println("***This is main thread.***");
            }
        }
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }

}
