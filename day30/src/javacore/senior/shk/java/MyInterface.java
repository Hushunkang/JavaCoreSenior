package javacore.senior.shk.java;

/**
 * @author shkstart
 * @create 2019 下午 2:25
 */
public interface MyInterface {

    //如下的三个方法的权限修饰符都是public
    void methodAbstract();

    //静态方法不可以被重写
    static void methodStatic(){
        System.out.println("我是接口中的静态方法");
    }

    //细节：default只是一个关键字，不是说这个方法的访问权限修饰符为默认
    //这个方法的访问权限修饰符为public，只是缺省了哈
    //一般地（接口中的抽象方法除外），如果要设置一个方法的访问权限修饰符为默认的，那么不带上修饰符即可
    default void methodDefault(){
        System.out.println("我是接口中的默认方法");

//        methodPrivate();
    }

    //java/jdk 9中允许接口中定义私有的方法
    private void methodPrivate(){
        System.out.println("我是接口中的私有方法");
    }

}
