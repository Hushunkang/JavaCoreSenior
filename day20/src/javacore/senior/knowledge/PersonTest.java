package javacore.senior.knowledge;

/**
 * @author hskbeginner
 * @create 2020/2/15
 */
public class PersonTest {

    public static void main(String[] args) {
        //明确：final修饰基本数据类型和引用数据类型的变量后
        //     表明这个变量为一个常量，具有不可变性
        //测试final修饰引用数据类型的变量，这个变量也表示为一个常量
        final Person person = new Person("hskVirtuoso",14);
//        person = new Person();//编译时异常
        person.setAge(18);
        System.out.println(person.getAge());

//        int[] ints;
//        System.out.println(ints);//Variable 'ints' might not have been initialized

        int[] ints = new int[3];//Contents of array 'ints' are read, but never written to
        for (int anInt : ints) {
            System.out.println(anInt);//0 0 0
        }

//        final char[] value = new char[3];
//        final char[] value = new char[3]{'a','b','c'};
        final char[] value = {'a','b','c'};//类型推断
//        value = new char[]{};//编译时异常
        value[2] = 'd';
        System.out.println("***" + value[2] + "***");
        //总结：final用来修饰引用数据的变量时，表示这个变量为一个常量，但此时这个常量存在着“整体不可变，局部可变”的特点。
    }

}

class Person{

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
