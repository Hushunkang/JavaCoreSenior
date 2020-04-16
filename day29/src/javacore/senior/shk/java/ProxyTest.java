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

//用于测试AOP（面向切面编程），常用于向被代理类的方法中动态注入***代码
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

        //以下描述只是一个大概描述，更精准的情况要对jvm等相关知识研究的很深
        //Proxy类的作用：在程序运行的过程中（jvm对字节码文件解释执行的过程中），动态创建一个代理类的字节码文件
        //Proxy类中newProxyInstance方法的作用：得到动态创建的代理类的对象实例
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }

}

//说明：如果一个类实现了InvocationHandler，那么表明这个类就是一个jdk动态代理类
class MyInvocationHandler implements InvocationHandler {

    private Object obj;//最终需要使用被代理类对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类对象调用方法a时，就会自动调用如下invoke方法
    //将被代理类要执行的方法a的功能就声明在invoke方法中
    /**
     * @param proxy 表示调用该方法的代理实例。
     * @param method 所述方法对应于调用代理实例上的接口方法的实例。方法对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。
     * @param args 包含的方法调用传递代理实例的参数值的对象的阵列，或null如果接口方法没有参数。原始类型的参数包含在适当的原始包装器类的实例中，例如java.lang.Integer或java.lang.Boolean。
     * @return 从代理实例上的方法调用返回的值。如果接口方法的声明返回类型是原始类型，则此方法返回的值必须是对应的基本包装类的实例；否则，它必须是可声明返回类型的类型。如果此方法返回的值是null和接口方法的返回类型是基本类型，那么NullPointerException将由代理实例的方法调用抛出。如上所述，如果此方法返回的值，否则不会与接口方法的声明的返回类型兼容，一个ClassCastException将代理实例的方法调用将抛出。
     * @throws Throwable
     */
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
        //proxyInstance:动态生成的代理类对象实例//说明：此处强转不可强转成SuperMan类型的，若可以，代理类和被代理类不就是同一个了么，因此不可以
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);//jdk动态代理动态生成的代理类默认的格式为：com.sun.proxy.$ProxyXXXX
        //当通过代理类对象调用方法时，会自动调用被代理类中同名方法
        proxyInstance.getBelief();
        proxyInstance.eat("四川麻辣烫");


        System.out.println("*****************************");


        ClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }

}
