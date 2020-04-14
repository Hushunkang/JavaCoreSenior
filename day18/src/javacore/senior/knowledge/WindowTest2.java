package javacore.senior.knowledge;

/**
 *
 * 开启三个线程模拟三个窗口售票，实现Runnable接口的方式来实现多线程
 *
 * @author hskbeginner
 * @create 2020/2/12
 */
public class WindowTest2 {

    public static void main(String[] args) {
        Runnable window2 = new Window2();
        //创建三个线程
        Thread t1 = new Thread(window2);
        Thread t2 = new Thread(window2);
        Thread t3 = new Thread(window2);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        //分别启动上面创建的三个线程，这三个线程做的同一件事情，因为是同一个run方法
        t1.start();
        t2.start();
        t3.start();
    }

}

class Window2 implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket--);
            }else{
                break;
            }
        }
    }

}
