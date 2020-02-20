package javacore.senior.knowledge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * java集合框架
 *
 * @author hskbeginner
 * @create 2020/2/17-21:08
 */
public class CollectionTestHSK {

    @Test
    public void test1() /*throws Throwable*/{
        //add(Object e)：将元素e添加到集合coll中去
        Collection coll = new ArrayList();
        coll.add(123);//自动装箱
        coll.add(123);
        coll.add("AA");
        coll.add("BB");
        coll.add(new Date());

        //size()：获取添加的元素的个数
        System.out.println(coll.size());

        //addAll(Collection coll1):将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add("cc");
        coll1.add("dd");
        coll.addAll(coll1);
        System.out.println(coll1.size());
        System.out.println(coll1);
        //clear():清空集合元素
        coll.clear();

        //isEmpty():看源码可知，是判断集合里面是否有元素，不是判断集合是否为空
        System.out.println(coll.isEmpty());//Result of 'coll.isEmpty()' is always 'true'

        int num = 10;

        //在idea中添加try/catch的快捷键ctrl+alt+t选中想被try/catch包围的语句
        if(num == 10){
//            throw new RuntimeException("你这个数不能为10.");
            try{
                throw new RuntimeException("你这个数不能为10.");
            }catch(RuntimeException e){
                e.printStackTrace();
            }
        }
//throw表示手动抛出异常对象，throw没有经过try-catch处理，方法执行到throw的位置就结束了
//throws虽然被称为是一种异常处理的方式，但是并没有真正处理了异常，只有try-catch才是真正处理了异常
//因此，throws也不行，没有try-catch，运行时异常，抛出异常对象（自动或手动）的位置，后面代码不再执行
//不管是自动还是手动抛出异常对象，没有在任何相关的地方进行try-catch处理，代码执行到抛出异常对象的地方，Java进程就会终止
    }

    @Test
    public void test4(){
        test3();
        System.out.println("我会输出吗？");//see condition1 and condition2
    }

    private void test3(){
//        this.test2();
        test2();
    }

    private void  test2(){
        throw new RuntimeException("运行时异常...");//condition1 不会输出
//        try {//condition2 会输出
//            throw new RuntimeException("运行时异常...");
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }
    }

}
