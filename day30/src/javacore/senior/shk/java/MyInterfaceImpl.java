package javacore.senior.shk.java;

/**
 * @author shkstart
 * @create 2019 下午 2:29
 */
public class MyInterfaceImpl implements MyInterface {

    @Override
    public void methodAbstract() {
        System.out.println("实现类实现了接口中的抽象方法");
    }

    //静态方法不可以被重写
//    @Override//Method does not override method from its superclass
//    public static void methodStatic(){
//
//    }

    @Override
    public void methodDefault() {
        System.out.println("实现类重写了接口中的默认方法");
    }

    public static void main(String[] args) {
        //接口中的静态方法只能由接口自己调用
        MyInterface.methodStatic();

        //接口的实现类不能调用接口的静态方法
//        MyInterfaceImpl.methodStatic();

        MyInterface impl = new MyInterfaceImpl();
        impl.methodDefault();

        //接口的私有方法，不能在接口外部调用
//        impl.methodPrivate();
    }

}
