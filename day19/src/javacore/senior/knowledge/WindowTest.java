package javacore.senior.knowledge;

/**
 *
 * 1.问题：卖票过程中，出现了重票和错票的情况，即线程安全问题
 * 2.原因：当某一个线程操作车票过程中，尚未操作完成，其它线程参与进来，也操作车票这个共享数据
 * 3.如何解决：当一个线程a操作ticket的时候，其它线程不能参与进来。直到a操作完ticket时，其它线程
 * 才可以开始操作ticket
 * 4.在Java中，我们通过同步机制，来解决线程安全问题
 *
 * 方式一：同步代码块
 * synchronized(同步监视器){
 *     需要被同步的代码
 * }
 * 说明：①.操作共享数据的代码，即为需要被同步的代码---不能包含代码多了，也不能包含代码少了
 *      ②.共享数据：多个线程共同操作的变量，本例中ticket就是共享数据
 *      ③.同步监视器：俗称“锁”。任何一个类的对象，都可以充当锁
 *        要求：多个线程必须共用同一把锁才可以实现多个线程操作共享数据是安全的
 *
 *        补充：在使用实现Runnable接口创建多线程的方式中，可以考虑（具体问题具体分析）使用this充当同步监视器
 *             在使用继承Thread类创建多线程的方式中，慎用this（具体问题具体分析）充当同步监视器，可以考虑使用当前类充当同步监视器
 *
 * 方式二：同步方法
 * 抽取需要被同步的代码为一个方法，Be careful！！！
 * 总结：
 * ①.同步方法仍然涉及到同步监视器，只是不需要我们显示的声明
 * ②.非静态的同步方法，同步监视器是：this
 * ③.静态的同步方法，同步监视器是：当前类本身，即类名.class---clazz表示当前类的对象，一个类有且仅有一份
 *
 * 5.同步机制，可以解决线程安全的问题---同步的好处
 *   操作被同步的代码时，只能有一个线程参与，其它线程都在等待，相当于是单线程的过程，效率低---同步的弊端
 *
 * @author hskbeginner
 * @create 2020/2/12-20:53
 */
public class WindowTest {

    public static void main(String[] args) {
        Runnable window = new Window();

        //创建三个线程来模拟三个售票窗口
        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        //分别启动上面三个线程（这三个线程用同一个run方法，相当于三个线程做同一件事）
        t1.start();
        t2.start();
        t3.start();
    }

}

class Window implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            saleTicket();
            if(ticket == 0){
                break;
            }
        }
    }

    private synchronized void saleTicket(){
        if(ticket > 0){
            try {
//                Thread.currentThread().sleep(100);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);

            //增大发生重票现象的概率
            try {
//                Thread.currentThread().sleep(100);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ticket--;
        }
    }

}
