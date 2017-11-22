package Number_01;

import Number_01.Utility.IntArrayUtil;
import Number_01.Utility.mergeSort;

import java.util.ArrayList;

public class Array100 {
    /*
    7：生成一个大小为100的无序数组并打印，数组中的元素为1-100W之间的随机整数（可重复）；

    8：拷贝上面无序数组，做从小到大的排序，并打印原数组和新数组；如果打印的原数组与新数组一样，是什么原因？如果不一样，又是什么原因？

    9：找出上面无序数组的最大值以及所在元素的位置；并打印

    10：找出上面无序数组的重复元素以及重复的个数；并打印
    */

    private static final int _size = 100; // 数组大小
    private static final int _range = (int) Math.pow(10, 6); // 随机数取值范围

    // 找出数组中的重复元素、重复个数（排序法）
    private static void getRepeatNum1(int[] array) {
        // 先排序
        int[] orderArray = mergeSort.mergeSortArray(array);
        ArrayList<Integer> repeatNum = new ArrayList<>();
        ArrayList<Integer> repeatCount = new ArrayList<>();
        int rNum = 0;
        int rCount = 1;
        for (int i = 0; i < orderArray.length; i++) {
            if (rNum != orderArray[i]) {
                if (rCount > 1) {
                    repeatNum.add(rNum);
                    repeatCount.add(rCount);
                    rCount = 1;
                }
                rNum = orderArray[i];
            } else {
                rCount++;
            }
        }
        // 循环打印重复元素
        for (int i = 0; i < repeatNum.size(); i++) {
            System.out.println("重复数字：" + repeatNum.get(i) + "，重复个数：" + repeatCount.get(i));
        }
    }

    public static void main(String[] args) {
        long beginTime;
        long endTime;
        int[] randomIntArray;

        // 7：生成一个大小为100的无序数组并打印，数组中的元素为1-100W之间的随机整数（可重复）；
        {
            System.out.println("7：生成一个大小为100的无序数组并打印，数组中的元素为1-100W之间的随机整数（可重复）；");
            randomIntArray = IntArrayUtil.genRandomIntArray(_size, _range);

            System.out.println("循环开始");
            for (int i = 0; i < randomIntArray.length; i++) {
                System.out.print(randomIntArray[i] + " ");
            }
            System.out.println();
            System.out.println("循环结束");
            System.out.println();
        }

        // 8：拷贝上面无序数组，做从小到大的排序，并打印原数组和新数组；如果打印的原数组与新数组一样，是什么原因？如果不一样，又是什么原因？
        {
            System.out.println("8：拷贝上面无序数组，做从小到大的排序，并打印原数组和新数组；如果打印的原数组与新数组一样，是什么原因？如果不一样，又是什么原因？");
            int[] array8 = randomIntArray.clone();

            beginTime = System.currentTimeMillis();
            array8 = mergeSort.mergeSortArray(array8);
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));

            System.out.println("打印原数组");
            for (int i = 0; i < randomIntArray.length; i++) {
                System.out.print(randomIntArray[i] + " ");
            }
            System.out.println("\n打印新数组");
            for (int i = 0; i < array8.length; i++) {
                System.out.print(array8[i] + " ");
            }
            System.out.println("\n结束");
            System.out.println();
        }

        // 9：找出上面无序数组的最大值以及所在元素的位置；并打印
        {
            System.out.println("9：找出上面无序数组的最大值以及所在元素的位置；并打印");
            int[] array9 = randomIntArray.clone();
            ArrayList<Integer> maxNumPosition = new ArrayList<>();

            beginTime = System.currentTimeMillis();
            int maxNum = IntArrayUtil.getMaxNum(array9, maxNumPosition);
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));

            System.out.println("最大值：" + maxNum);
            System.out.println("最大值所在位置：" + maxNumPosition.toString());
            System.out.println("结束");
            System.out.println();
        }

        // 10：找出上面无序数组的重复元素以及重复的个数；并打印
        {
            System.out.println("10：找出上面无序数组的重复元素以及重复的个数；并打印");
            int[] array10 = randomIntArray.clone();

            System.out.println("使用归并排序法");
            beginTime = System.currentTimeMillis();
            mergeSort.mergeSortArray(array10);
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
            System.out.println();
        }
    }
}