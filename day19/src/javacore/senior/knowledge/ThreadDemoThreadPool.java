package javacore.senior.knowledge;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * 实现多线程的方式四：线程池的方式
 *
 * @author hskbeginner
 * @create 2020/2/14-0:25
 */
public class ThreadDemoThreadPool {

    public static void main(String[] args) {
        //创建固定大小的线程池，线程池（也是一个对象）中线程个数为10（10个线程对象）
        ExecutorService service = Executors.newFixedThreadPool(10);//多态的方式

        //获取service对象是哪个类造的
        System.out.println(service.getClass());

        service.execute(new NumberThread1());//适合使用于Runnable接口
        service.execute(new NumberThread2());//适合使用于Runnable接口

//        service.submit();//适合使用于Callable接口

        //管理线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) service;//向下转型
        threadPool.setCorePoolSize(15);
//        threadPool.setKeepAliveTime();

        //关闭线程池
        service.shutdown();
    }

}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }

}

class NumberThread2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }

}
