package javacore.senior.practice;

/**
 *
 * 线程通信 使用两个线程打印1-100之间（包含1和100）的N并且需要交替打印
 *
 * @author hskbeginner
 * @create 2020/2/13
 */
public class CommunicationTest {

    public static void main(String[] args) {
        Runnable number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程一");
        t2.setName("线程二");

        t1.start();
        t2.start();
    }

}

class Number implements Runnable{

    private int number = 1;

    @Override
    public void run() {
        while(true){
            synchronized (this) {//this此处代指number
                if(number <= 100){

                    //notify和notifyAll需要搭配wait方法来使用
                    this.notify();//涉及到两个线程，相互唤醒
//                    notifyAll();//涉及到两个以上线程，一个线程唤醒其它所有线程

                    //增大发生线程安全问题的possible
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "打印了：" + number);
                    number++;

                    try {
                        this.wait();//使得调用当前wait方法的线程进入阻塞状态
                        //并且经过测试发现wait方法会让当前线程释放锁
                        //与之相对的是sleep方法，也会使得调用当前sleep方法的线程进入阻塞状态
                        //但sleep方法不会让当前线程释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else{
                    break;
                }
            }
        }
    }

}
