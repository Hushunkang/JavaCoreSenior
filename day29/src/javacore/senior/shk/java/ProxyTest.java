package javacore.senior.shk.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理的举例
 *
 * @author shkstart
 * @create 2019 上午 10:18
 */
//中介
interface Human {

    void getBelief();//信仰

    void eat(String food);//吃

}

//被代理类（实际项目中被代理类一般是一个业务逻辑控制器）
class SuperMan implements Human {

    @Override
    public void getBelief() {
        System.out.println("I believe I can fly!");
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }

}

//用于测试AOP（面向切面编程），常用于向被代理类的方法动态注入代码
class HumanUtils {

    public void method1() {
        System.out.println("====================通用方法一====================");
    }

    public void method2() {
        System.out.println("====================通用方法二====================");
    }

}

/**
 * 要想实现动态代理，需要解决的问题？
 * 问题一：如何根据加载到jvm内存中的被代理类，动态的创建一个代理类及其对象
 * 问题二：当通过代理类对象调用方法a时，如何动态的去调用被代理类中的同名方法a
 */
class ProxyFactory {

    //根据被代理类的一些信息，调用此方法，可以动态的返回一个代理类对象，解决了问题一
    public static Object getProxyInstance(Object obj) {//obj:被代理类对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }

}

class MyInvocationHandler implements InvocationHandler {

    private Object obj;//最终需要使用被代理类对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类对象调用方法a时，就会自动调用如下invoke方法
    //将被代理类要执行的方法a的功能就声明在invoke方法中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtils utils = new HumanUtils();
        utils.method1();

        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类对象
        Object returnValue = method.invoke(obj, args);

        utils.method2();
        //上述方法的返回值就作为当前类中的invoke方法的返回值
        return returnValue;
    }

}

public class ProxyTest {

    public static void main(String[] args) {
        //动态代理：一个代理类可以搞定所有被代理类
        //被代理类对象
        Human superMan = new SuperMan();
        //proxyInstance:代理类对象//说明：此处强转不可强转成SuperMan类型的，若可以，代理类和被代理类不就是同一个了么，因此不可以
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动调用被代理类中同名方法
        proxyInstance.getBelief();
        proxyInstance.eat("四川麻辣烫");


        System.out.println("*****************************");


        ClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }

}
