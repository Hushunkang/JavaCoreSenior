package javacore.senior.practice;

/**
 *
 * 将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
 *
 * @author hskbeginner
 * @create 2020/2/15-21:39
 */
public class StringDemoHSK {

    /**
     * 方法一：转换成char[]
     * @param str
     * @param startIndex
     * @param endIndex
     * @return
     */
    public String reverse(String str,int startIndex,int endIndex){
        if(str != null){
            char[] value = str.toCharArray();
            for(int i = startIndex,j = endIndex;i < j;i++,j--){
                //调换数组中两个数据元素的位置
                char temp = value[i];
                value[i] = value[j];
                value[j] = temp;
            }
//            str = value.toString();
            str = new String(value);
        }
        return str;
    }

    /**
     * 方法二：拼接字符串
     * @param str
     * @param startIndex
     * @param endIndex
     * @return
     */
    public String reverse2(String str,int startIndex,int endIndex){
        String reverseStr = null;
        if(str != null){
            //第一部分
            reverseStr = str.substring(0,startIndex);
            //第二部分
            for(int i = endIndex;i >= startIndex;i--){
                reverseStr += str.charAt(i);
            }
            //第三部分
            reverseStr += str.substring(endIndex + 1);
        }
        return reverseStr;
    }

    //优化：使用StringBuffer/StringBuilder替换String
    public String reverse3(String str,int startIndex,int endIndex){
        StringBuilder builder = new StringBuilder(str.length());
        if(str != null){
            //第一部分
            builder.append(str.substring(0,startIndex));
            //第二部分
            for(int i = endIndex;i >= startIndex;i--){
                builder.append(str.charAt(i));
            }
            //第三部分
            builder.append(str.substring(endIndex + 1));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        StringDemoHSK stringDemoHSK = new StringDemoHSK();
        //左移位运算符
        int i = 2 << 3;
        System.out.println(i);
//        String reverseStr = stringDemoHSK.reverse(null,0,0);
//        String reverseStr = stringDemoHSK.reverse("abcdefgh",0,10);
//        String reverseStr = stringDemoHSK.reverse("abcdefgh",0,4);
//        String reverseStr = stringDemoHSK.reverse("abcdefgh",0,4);
        String reverseStr = stringDemoHSK.reverse2("abcdefgh",0,4);
        System.out.println(reverseStr);
    }

}
