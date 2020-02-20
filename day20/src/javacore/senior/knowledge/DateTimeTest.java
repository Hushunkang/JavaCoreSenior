package javacore.senior.knowledge;

import org.junit.Test;

import java.util.Date;

/**
 *
 * JDK8之前日期和时间的API测试
 *
 */
public class DateTimeTest {

    /*
    java.util.Date类
           |---java.sql.Date类
    1.两个构造器的使用
        >构造器一：Date():创建一个对应当前时间的Date对象
        >构造器二：创建指定毫秒数的Date对象
    2.两个方法的使用
        >toString():显示当前的年、月、日、时、分、秒
        >getTime():获取当前Date对象对应的毫秒数。（时间戳）
    3. java.sql.Date对应着数据库中的日期类型的变量
        >如何实例化
        >如何将java.util.Date对象转换为java.sql.Date对象
     */
    @Test
    public void test2(){
        //构造器一：Date():创建一个对应当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1.toString());//Fri Feb 21 04:00:22 CST 2020
        //说明：GMT+08:00表示时区为东八区
        System.out.println(date1.getTime());//1582228822182
        //构造器二：创建指定毫秒数的Date对象
        Date date2 = new Date(1582228822182L);
        System.out.println(date2);//Fri Feb 21 04:00:22 CST 2020
        //创建java.sql.Date对象
        java.sql.Date date3 = new java.sql.Date(1582228822182L);
        System.out.println(date3);//2020-02-21
        //如何将java.util.Date对象转换为java.sql.Date对象
        //情况一：
//        Date date4 = new java.sql.Date(2343243242323L);//多态的形式
//        java.sql.Date date5 = (java.sql.Date) date4;//向下转型
        //说明：这种情况是多态自动类型提升，上去，然后向下转型，强转，下来，这个过程编译和运行都没问题
        //情况二：
        Date date6 = new Date();
//        java.sql.Date date7 = (java.sql.Date) date6;//编译没问题，运行时异常java.lang.ClassCastException
        //说明：这个很细节！
        //类似这样的场景：Person是Student类的父类
        //Person p = new Person();
        //Student s = (Student) p;
        // 这种情况肯定是错误的，编译时没问题，运行时异常
        java.sql.Date date7 = new java.sql.Date(date6.getTime());
    }

    //System类中的currentTimeMillis()
    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        //返回当前时间与1970年1月1日0时0分0秒之间以毫秒为单位的时间差。
        //称为时间戳
        System.out.println(time);
        //时间戳是一个相对时间的概念
        //应用场景：可以用于生成随机的唯一的XXX序列号
        //注意：如果并发特别高的情况下，可能出现重复的XXX序列号，不能满足需求
        //解决办法：这个时候可以让时间戳再搭配一些随机数或者其它结构，实际情况就几乎不可能出现重复的XXX序列号了
    }

}
