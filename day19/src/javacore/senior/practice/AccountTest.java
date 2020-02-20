package javacore.senior.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hskbeginner
 * @create 2020/2/13-21:02
 */
public class AccountTest {

    public static void main(String[] args) {
        Account acct = new Account(0);
        
        Customer cust1 = new Customer(acct);
        Customer cust2 = new Customer(acct);
        
        cust1.setName("甲");
        cust2.setName("乙");
        
        cust1.start();
        cust2.start();
    }

}

class Account{

    private double balance;

    private Lock lock = new ReentrantLock();//必须保证多个线程共用一个lock对象
    // 此时lock对象充当了同步监视器

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱---方式一
//    public synchronized void deposit(double amt){
//        if(amt > 0){
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            this.balance += amt;//此例子中this代指的是acct
//            balance += amt;
//            System.out.println(Thread.currentThread().getName() + "：存钱成功，余额为：" + balance);
//        }
//    }

    //存钱---方式二
    public void deposit(double amt){
        lock.lock();
        if(amt > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            this.balance += amt;//此例子中this代指的是acct
            balance += amt;
            System.out.println(Thread.currentThread().getName() + "：存钱成功，余额为：" + balance);
        }
        lock.unlock();
    }

}

class Customer extends Thread{

    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }

}


