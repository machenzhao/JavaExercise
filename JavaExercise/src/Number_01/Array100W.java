package Number_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Array100W {
    /*
    1：上面第八题的数组大小改为100W，并输出到一个txt文件中

    2：从上面的TXT文件中读取数组，并找出其中重复的元素和对应的个数；

    3：从上面的TXT文件中读取数组，并找出最大值以及所在位置；

    4：从上面的TXT文件中读取数组，并进行从小到大的排序；
    */

    private static final int _size = (int) Math.pow(10, 6); // 数组大小
    private static final int _range = (int) Math.pow(10, 6); // 随机数取值范围

    // 生成一个由随机整数组成的无序数组
    private static int[] genRandomIntArray(int size, int range) {
        int[] randomIntArray = new int[size];
        for (int i = 0; i < size; i++) {
            double randomDouble = Math.random();
            int randomInt = (int) Math.round(randomDouble * range);
            randomInt = randomInt == 0 ? range : randomInt;
            randomIntArray[i] = randomInt;
        }
        return randomIntArray;
    }

    // 生成一个大小为100W的无序数组并输出到文件，数组中的元素为1-100W之间的随机整数（可重复）
    private static boolean genRandomIntArray100W(String pathname) {
        int[] randomIntArray = genRandomIntArray(_size, _range);
        File file = new File(pathname);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            for (int i = 0; i < randomIntArray.length; i++) {
                out.write((randomIntArray[i] + " ").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
    1.首先考虑下如何将将二个有序数列合并。这个非常简单，只要从比较二个数列的第一个数，谁小就先取谁，取了后就在对应数列中删除这个数。然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
      可以看出合并有序数列的效率是比较高的，可以达到O(n)。
    2.解决了上面的合并有序数列问题，再来看归并排序，其的基本思路就是将数组分成二组A，B，如果这二组组内的数据都是有序的，那么就可以很方便的将这二组数据进行排序。如何让这二组组内数据有序了？
      可以将A，B组各自再分成二组。依次类推，当分出来的小组只有一个数据时，可以认为这个小组组内已经达到了有序，然后再合并相邻的二个小组就可以了。这样通过先递归的分解数列，再合并数列就完成了归并排序。
     */

    // 归并排序：1.将两个有序表合并成一个新的有序表
    private static void mergeArray(int[] array, int first, int middle, int last) {
        int i = first, j = middle;
        int m = middle + 1, n = last;
        int[] temp = new int[last - first + 1];
        int k = 0;

        while (i <= j && m <= n) {
            if (array[i] < array[m]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[m++];
            }
        }

        while (i <= j) {
            temp[k++] = array[i++];
        }

        while (m <= n) {
            temp[k++] = array[m++];
        }

        for (int k2 = 0; k2 < temp.length; k2++) {
            array[k2 + first] = temp[k2];
        }
    }

    // 归并排序：2.将无序数组进行排序
    private static int[] mergeSort(int[] array, int first, int last) {
        int middle = (first + last) / 2;
        if (first < last) {
            mergeSort(array, first, middle); // 左边
            mergeSort(array, middle + 1, last); // 右边
            mergeArray(array, first, middle, last);
        }
        return array;
    }

    // 数组排序
    private static int[] sortArray100(int[] array) {
        if (array != null && array.length > 0) {
            int last = array.length - 1;
            array = mergeSort(array, 0, last);
        } else {
            System.out.println("传入数组为空");
        }
        return array;
    }

    // 找出数组中的最大值、所在位置
    private static int getMaxNum(int[] array, ArrayList<Integer> maxNumPosition) {
        int max = 0;

        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (max < array[i]) {
                    max = array[i];
                    maxNumPosition.clear();
                    maxNumPosition.add(i);
                } else if (max == array[i]) {
                    maxNumPosition.add(i);
                }
            }
        }
        return max;
    }

    // 找出数组中的重复元素、重复个数（排序法）
    private static void getRepeatNum1(int[] array) {
        // 先排序
        int[] orderArray = sortArray100(array);
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

    // 找出数组中的重复元素、重复个数（木桶法）
    // 要求：成员隶属于固定(有限的)的区间，放到100W时使用
    private static void getRepeatNum2(int[] array) {
        // 先造1个空木桶
        int length = array.length;
        int[] bucketNum = new int[length];
        int count;
        // 循环数组，尝试放入木桶的空位，如果位子非0，则代表重复
        for (int i = 0; i < length; i++) {
            if (bucketNum[array[i] - 1] == 0) {
                bucketNum[array[i] - 1] = array[i];
            } else {
                bucketNum[array[i] - 1]++;
            }
        }
        // 循环Map，打印需要的数据
        for (int i = 0; i < bucketNum.length; i++) {
            if (bucketNum[i] - i > 1)
                System.out.println("重复数字：" + (i + 1) + "，重复个数：" + (bucketNum[i] - i));
        }
    }


    public static void main(String[] args) {
        long beginTime;
        long endTime;
        String pathname = "array100w.txt";

        // 1：上面第八题的数组大小改为100W，并输出到一个txt文件中
        {
            System.out.println("1");
            beginTime = System.currentTimeMillis();
            boolean execBoolean = genRandomIntArray100W(pathname);
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
            System.out.println("pathname: " + pathname);
        }

//        // 8：拷贝上面无序数组，做从小到大的排序，并打印原数组和新数组；如果打印的原数组与新数组一样，是什么原因？如果不一样，又是什么原因？
//        {
//            System.out.println("8");
//            int[] array8 = randomIntArray.clone();
//
//            beginTime = System.currentTimeMillis();
//            array8 = sortArray100(array8);
//            endTime = System.currentTimeMillis();
//            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
//
//            System.out.println("打印原数组");
//            for (int i = 0; i < randomIntArray.length; i++) {
//                System.out.print(randomIntArray[i] + " ");
//            }
//            System.out.println("\n打印新数组");
//            for (int i = 0; i < array8.length; i++) {
//                System.out.print(array8[i] + " ");
//            }
//            System.out.println("\n结束");
//        }
//
//        // 9：找出上面无序数组的最大值以及所在元素的位置；并打印
//        {
//            System.out.println("9");
//            int[] array9 = randomIntArray.clone();
//            ArrayList<Integer> maxNumPosition = new ArrayList<>();
//
//            beginTime = System.currentTimeMillis();
//            int maxNum = getMaxNum(array9, maxNumPosition);
//            endTime = System.currentTimeMillis();
//            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
//
//            System.out.println("最大值：" + maxNum);
//            System.out.println("最大值所在位置：" + maxNumPosition.toString());
//            System.out.println("结束");
//        }
//
//        // 10：找出上面无序数组的重复元素以及重复的个数；并打印
//        {
//            System.out.println("10");
//            int[] array10 = randomIntArray.clone();
//
//            System.out.println("使用归并排序法");
//            beginTime = System.currentTimeMillis();
//            getRepeatNum1(array10);
//            endTime = System.currentTimeMillis();
//            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
//        }
    }
}