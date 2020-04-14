package javacore.senior.knowledge;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * 实现多线程的方式三：实现java.util.concurrent.Callable接口---jdk1.5新增
 *
 * @author hskbeginner
 * @create 2020/2/13
 */
public class ThreadDemoCallable {

    public static void main(String[] args) {
        Callable myThread = new MyThread();

        Future futureTask = new FutureTask(myThread);

        //创建一个线程并启动这个线程
        new Thread((FutureTask) futureTask).start();

        try {
            //get方法的返回值即为FutureTask构造器参数Callable实现类重写的call方法的返回值
            Object sum = futureTask.get();
            System.out.println("sum：" + sum);
            //|表示非短路或运算符
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}

class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        //求和算法
        //|---递归方式
        //|---迭代方式
        //求和算法之迭代方式 》》》与之相对的就是递归方式 数学在算法中应用 递推式 汉诺塔问题 quicksort算法
        for (int i = 1; i <= 100; i++) {
            sum += i;//sum = sum + i;
        }
        return sum;
    }
    //数学在算法中的应用，汉诺塔问题（递推式）、quick sort算法，这种问题往往要用递归来解决
    //递归其实是一种隐式的循环

}
