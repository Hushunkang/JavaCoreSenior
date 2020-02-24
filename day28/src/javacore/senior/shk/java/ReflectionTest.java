package javacore.senior.shk.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author shkstart
 * @create 2019 上午 10:38
 */
public class ReflectionTest {


    //反射之前，对于Person的操作
    @Test
    public void test1() {

        //1.创建Person类的对象
        Person p1 = new Person("Tom", 12);

        //2.通过对象，调用其内部的属性、方法
//        p1.name = "Tom";//'name' has private access in 'javacore.senior.shk.java.Person'
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();
//        p1.showNation("中国");//'showNation(java.lang.String)' has private access in 'javacore.senior.shk.java.Person'

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如：name、showNation()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception{
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("*******************************");

        //通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1,"中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);


    }

    //疑问1：通过直接new的方式或反射的方式都可以造对象进而调用有关类的结构，开发中到底用那个？
    //建议：直接new的方式。

    //疑问2：什么时候会使用反射的方式？
    //反射的特征：
    //动态性---如果程序在编译的时候确定不下来要造哪个类的对象，这个时候就可以考虑在程序运行的过程中通过反射来动态造某个类的对象。
    //典型案例：通过反射来具体造某个Servlet类的对象。

    //疑问3：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    //不矛盾。
    //理解：封装性说的是建议你不要在当前存在私有结构的类以外的类调用我这个私有的结构
    //     反射说的是我有能力在当前存在私有结构的类以外的类调用我这个私有的结构，因此封装性和反射并不矛盾。


    /*

    关于java.lang.Class类的理解
    1.类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。//这个过程是编译过程，其实不是类的加载过程的一部分。
    接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到JVM内存中。此过程就称为类的加载。
    加载到JVM内存中的类，我们就称为运行时类，此运行时类，就作为Class类的一个实例。
    相当于任意的类、数组、接口等也是对象，是Class类的一个对象。
    理解很奇特哈，类其实也是一个对象，也体现了万事万物皆对象。
    2.换句话说，Class类的实例就对应着一个运行时类。
    3.加载到内存中的运行时类，会缓存（缓存区）一定的时间。在此时间之内，我们可以通过不同的方式来获取此运行时类。

     */

    //获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        //看看p1这个对象是由哪个类造的
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("javacore.senior.shk.java.Person");//使用的频率更多一些
        //javacore.senior.shk.java.Person表示Person这个类的全类名
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);//true
        System.out.println(clazz1 == clazz3);//true
        //说明：因为Class类的实例与运行时类存在一一对应的关系，因此在一个运行时类的生命周期中，Class类的实例有且仅有一份
        //方式四：使用类的加载器：ClassLoader  (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atguigu.java.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);//true

    }


    //万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作


    //Class实例可以是哪些结构的说明：
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;//没有返回值类型也是一种数据类型，类似于Cn0，不选择也是一种选择
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        //只要数组的元素类型与维度一样，就是同一个Class类的实例
        System.out.println(c10 == c11);//Condition 'c10 == c11' is always 'true'

    }
}
