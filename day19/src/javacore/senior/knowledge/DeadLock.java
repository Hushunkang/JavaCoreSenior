package javacore.senior.knowledge;

/**
 *
 * 演示线程死锁问题
 *
 * @author hskbeginner
 * @create 2020/2/13-2:22
 */
public class DeadLock {

    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        //继承Thread类方式创建多线程---第一个线程
        new Thread(){//继承自Thread类的匿名的局部内部类
            @Override
            public void run() {
                synchronized (s1){//同步监视器1/锁1
                    s1.append("a");
                    s2.append(1);

                    //原本这样的代码就有概率发生死锁问题，下面的代码只是给当前线程阻塞以增大发生死锁问题的概率
                    try {
//                        Thread.currentThread().sleep(100);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2){//同步监视器2/锁2
                        s1.append("b");
                        s2.append(2);

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        //实现Runnable接口方式创建多线程---第二个线程
        new Thread(new Runnable() {//实现了Runnable接口的匿名的局部内部类
            @Override
            public void run() {
                synchronized (s2){
                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }

}
