package javacore.senior.practice;

/**
 * @description 冒泡排序
 * @author hsk Email:hushunkangwy@126.com
 * @version 1.0
 * @date 2020年1月19日上午11:58:04
 *
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{13,-12,43,88,34,89,50,-68,13,-68};

        //冒泡排序
        //需要进行n-1大轮（n表示数组中元素的个数），因此需要循环n-1次[0,arr.length-2]
        for (int i = 0; i < arr.length - 1; i++) {
            //每一大轮下，需要比较数组中相邻两个元素的大小，需要比较的次数为n-当前进行的轮数[arr.length-(i+1)]
            for(int j=0;j < arr.length - i -1; j++){
                //临时变量法实现元素换位置
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        //输出结果
        for (int value : arr) {
            System.out.println("数组由小到大排序为：" + value);
        }

    }

}
