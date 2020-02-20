package javacore.senior.practice;

/**
 *
 * 线程通信的应用---经典例题---生产者/消费者问题
 * 多线程之线程异步
 *  多个线程多个run，同时做多件事情，并行
 *  多个线程一个run，同时做一件事情，并发
 *
 * @author hskbeginner
 * @create 2020/2/13-22:25
 */
public class ProductTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Thread p1 = new Producer(clerk);
        p1.setName("生产者1");
        p1.start();

        Thread c1 = new Consumer(clerk);
        c1.setName("消费者1");
        c1.start();
    }

}

class Clerk{//店员

    private int productCount = 0;

    public synchronized void produceProduct() {
        if(productCount < 20){
            notify();
            ++productCount;
            System.out.println(Thread.currentThread().getName() + "：开始生产第" + (productCount) +"个产品");
        }else{
            try {
                wait();//非静态方法被调用了，前面默认省略了this.，静态方法被调用了，前面默认省略了类名.
                //this.wait()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if(productCount > 0){
            notify();
            System.out.println(Thread.currentThread().getName() + "：开始消费第" + (productCount) +"个产品");
            productCount--;
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Producer extends Thread{//生产者

    private Clerk clerk;

    public Producer(Clerk clerk) {
//        super();
        this.clerk = clerk;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + "开始生产产品...");
        System.out.println(getName() + "开始生产产品...");
        while(true){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }

}

class Consumer extends Thread{//消费者

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始消费产品...");
        while(true){
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }

}