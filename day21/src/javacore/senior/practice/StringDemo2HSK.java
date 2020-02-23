package javacore.senior.practice;

/**
 *
 * 学习任何知识，都要追究其本质，原理，思想、方法，提高认知能力，这样就能够以不变应万变，学习能力会大幅度提升
 * 获取两个字符串中最大相同子串。比如：
 * str1 = "abcwerthelloyuiodefabcdef";str2 = "cvhellobnm"
 * 提示：将短的那个串进行长度依次递减的子串与较长的串比较。
 * 有的类似求两个数的最大公约数/最大公因数 以小的为基准，因为最后的结果不可能超过最小的
 *
 * 四位的二进制数等价于一位的十六进制数，因此16位的二进制数等价于4位的16进制数
 *
 * @author hskbeginner
 * @create 2020/2/15-23:55
 */
public class StringDemo2HSK {

    private String[] strs;

    private char cc1;//默认初始值为'反斜杠u0000'，转换为十进制数：0
    private Character cc2;//默认初始值为null

    private String[] strs2 = new String[3];//String数组对象strs2不为null，但String数组中任意一个数据元素都为null

    public String getMaxSameSubString(String str1,String str2){
        return null;
    }

    public static void main(String[] args) {

        //Unicode编码方式可包含65536个字符，ASCII编码方式只包含128个字符，实际上是Unicode编码方式的一个子集
        //Unicode编码方式通常用十六进制编码方案表示，范围在'\u0000'到'\uFFFF'之间
        //\u0000到\u00FF表示ASCII编码方式的字符集合
        //反斜杠u相当于Unicode编码值的一个前缀，用来表示这个值是一个Unicode编码值

        //char数据类型表数（十进制数）范围为0~65535，四位十六进制数可以表示16^4=65536种状态
        char c1 = '\t';//转义字符 制表符 本质也是一个字符
        char c2 = '\n';//转义字符 换行符 本质也是一个字符

        char c3 = '\u0000';
        //反斜杠u0000的解释：反斜杠u表示这个字符采用Unicode编码方式，后面四位字符表示四位的十六进制数
        //反斜杠u0000它表示字符为NUL含义为空格符，在控制台打印出来为空格
        //转换为十进制数为0，是char数据类型的表数范围的最小值

        char c9 = '\u0040';//表示@这一个字符
        System.out.println(c9);//@
        System.out.println((int) c9);//64
        //0040十六进制数转换为64十进制数0*16^3+0*16^2+4*16^1+0*16^0=64

        char c4 = 1234;
        //要看成一个整体1234这个十进制数就表示一个字符
        //求出这个字符的Unicode编码值，然后去查Unicode编码一览表就知道真实表示什么字符
        //十进制数1234，转换为四位十六进制数为04d2，故这个字符的Unicode编码值为反斜杠u04d2，查表知，表示的字符为Ӓ
        char c5 = 'n';//所对应的ASCII码为110
        char c6 = 'u';//所对应的ASCII码为117
        char c7 = 'l';//所对应的ASCII码为108
        char c8 = 'l';//所对应的ASCII码为108

        System.out.println(c1);//制表符，一个字符
        System.out.println(c2);//换行符，一个字符
        System.out.println(c3);//反斜杠u0000它表示字符为NUL含义为空格符，在控制台打印出来为空格
        System.out.println((int) c1);//9
        System.out.println((int) c2);//10
        System.out.println((int) c3);//0
        System.out.println(c4);//Ӓ
        System.out.println(c5);//n
        System.out.println(c6);//u
        System.out.println(c7);//l
        System.out.println(c8);//l
        System.out.println((int) c4);//1234
        System.out.println((int) c5);//110
        System.out.println((int) c6);//117
        System.out.println((int) c7);//108
        System.out.println((int) c8);//108

        StringDemo2HSK stringDemo2HSK = new StringDemo2HSK();
        System.out.println(stringDemo2HSK.cc1);//反斜杠u0000它表示字符为NUL含义为空格符，在控制台打印出来为空格
        System.out.println(stringDemo2HSK.cc2);//null
        System.out.println((int) stringDemo2HSK.cc1);//0

        char cccc = 0;
//        char cccc = -1//编译时异常，0已经是char数据类型的最小值了
        System.out.println(cccc);
        //0对应着的Unicode编码值为反斜杠u0000，而每一种编码方式的一个具体编码值又对应着唯一的字符
        //查询Unicode编码对照表知，0表示的字符为NUL含义为空格符，因此在控制台的输出为空格
        System.out.println((int) cccc);//0

        //深入理解char表示一个字符
        //char c4 = 1234;
        //不要被表面现象所迷惑，这里1234就是一个整体，就是表示一个字符，而且查表知道了，实际表示的是Ӓ这个字符
        //先得把十进制数1234转换为四位的十六进制数04d2，然后求得这个字符的Unicode编码值为反斜杠u04d2
        //最后查表，知道了，c4表示的字符为Ӓ这个字符，而且控制台输出的也是一个字符，即Ӓ（再深入一点，这其实是一个西里尔字母）


        System.out.println(stringDemo2HSK.strs);//Implicit call to 'toString()' on array 'stringDemo2HSK.strs'
        System.out.println(stringDemo2HSK.strs2);//Implicit call to 'toString()' on array 'stringDemo2HSK.strs'
//        System.out.println(stringDemo2HSK.strs[0]);//java.lang.NullPointerException
        System.out.println(stringDemo2HSK.strs2[0]);
//      String[] strs3;
//      //局部变量没有默认的初始化值，如果不对它进行初始化直接使用它，编译时异常//Variable 'str3' might not have been initialized
        String[] str3 = new String[3];//String数组对象strs3不为null，但String数组中任意一个数据元素都为null
        System.out.println(str3[0]);

        String str = "abcdefha";
        String str2 = str.replace("a","m");
        System.out.println(str);
        System.out.println(str2);



        System.out.println("***关于String的算法题***");

    }

}
