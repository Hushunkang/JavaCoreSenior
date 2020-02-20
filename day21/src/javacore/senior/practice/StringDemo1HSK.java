package javacore.senior.practice;

/**
 *
 * 获取一个字符串在另一个字符串中出现的次数。
 * 比如：获取“ab”在 “abkkcadkabkebfkaabkskab” 中出现的次数
 *
 * @author hskbeginner
 * @create 2020/2/15-22:40
 */
public class StringDemo1HSK {

    /**
     * 获取subStr在mainStr中出现的次数
     * @param mainStr
     * @param subStr
     * @return
     */
    public int getCount(String mainStr,String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int offset = 0;//起始的条目索引
        if(mainLength > subLength){
////            offset = mainStr.indexOf(subStr);
//            while((offset = mainStr.indexOf(subStr)) != -1){
//                count++;
////                offset = mainStr.indexOf(subStr);
//                mainStr = mainStr.substring(mainStr.indexOf(subStr) + 1);//这个地方会重新造很多String对象，效率低
//            }
            //优化后的算法 事实证明有bug
//            while((offset = mainStr.indexOf(subStr,offset)) != -1){
//                count++;
//                offset += subLength;
//            }
        }
        return count;
    }

    public static void main(String[] args) {
        StringDemo1HSK stringDemo1HSK = new StringDemo1HSK();
//        String str = "abkkcadkabkebfkaabkskab";
        String str = "abababababab";
//        String str2 = str.substring(0,4);
//        str = str.substring(0,4);//abcd
//        System.out.println(str);
//        System.out.println(str);
//        System.out.println(str2);
//        System.out.println(str.indexOf("abc"));//0
//        System.out.println(str.substring(0+1));//bcd 具体到抽象的思维过程 总结出公式
        int count = stringDemo1HSK.getCount(str,"abab");
        System.out.println(count);
    }

}
