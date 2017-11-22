package Number_01;

import Number_01.Utility.IntArrayUtil;
import Number_01.Utility.mergeSort;

import java.io.*;
import java.util.ArrayList;

public class Array100W {
    /*
    1：上面第7题的数组大小改为100W，并输出到一个txt文件中

    2：从上面的TXT文件中读取数组，并找出其中重复的元素和对应的个数；

    3：从上面的TXT文件中读取数组，并找出最大值以及所在位置；

    4：从上面的TXT文件中读取数组，并进行从小到大的排序；
    */

    private static final int _size = (int) Math.pow(10, 6); // 数组大小
    private static final int _range = (int) Math.pow(10, 6); // 随机数取值范围

    // 找出数组中的重复元素、重复个数（排序法）
    private static void getRepeatNum1(int[] array, String outputFilePath) {
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

        // 日志打印到文件
        File file = new File(outputFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream out = null;
        BufferedOutputStream buff = null;
        try {
            out = new FileOutputStream(file);
            buff = new BufferedOutputStream(out);
            // 循环Map，打印需要的数据
            for (int i = 0; i < repeatNum.size(); i++) {
                buff.write(("重复数字：" + repeatNum.get(i) + "，重复个数：" + repeatCount.get(i) + ";\n").getBytes());
            }
            buff.flush();
            out.flush();
            buff.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 找出数组中的重复元素、重复个数（木桶法）
    // 要求：成员隶属于固定(有限的)的区间，放到100W时使用
    private static void getRepeatNum2(int[] array, String outputFilePath) {
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

        // 日志打印到文件
        File file = new File(outputFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream out = null;
        BufferedOutputStream buff = null;
        try {
            out = new FileOutputStream(file);
            buff = new BufferedOutputStream(out);
            // 循环Map，打印需要的数据
            for (int i = 0; i < bucketNum.length; i++) {
                if (bucketNum[i] - i > 1)
                    buff.write(("重复数字：" + (i + 1) + "，重复个数：" + (bucketNum[i] - i) + ";\n").getBytes());
            }
            buff.flush();
            out.flush();
            buff.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        long beginTime;
        long endTime;
        String pathname = "Result_01/array100w.txt";
        int[] arrTxt;

        // 1：上面第7题的数组大小改为100W，并输出到一个txt文件中
        {
            System.out.println("1：上面第7题的数组大小改为100W，并输出到一个txt文件中");
            beginTime = System.currentTimeMillis();
            arrTxt = IntArrayUtil.genRandomIntArray(_size, _range);
            boolean execBoolean = IntArrayUtil.createRandomIntArrayFile2(pathname, arrTxt);
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
        }

        // 装箱，共用数组
        {
            beginTime = System.currentTimeMillis();
            arrTxt = IntArrayUtil.readArrayFromTxt2(pathname);
            endTime = System.currentTimeMillis();
            System.out.println("装箱耗时（毫秒）：" + (endTime - beginTime));
            System.out.println("数组大小：" + arrTxt.length);
            System.out.println();
        }

        // 2：从上面的TXT文件中读取数组，并找出其中重复的元素和对应的个数；
        {
            System.out.println("2：从上面的TXT文件中读取数组，并找出其中重复的元素和对应的个数；");

            String filePathBucket = "Result_01/repeatByBucket.txt";
            beginTime = System.currentTimeMillis();
            getRepeatNum2(arrTxt.clone(), filePathBucket);
            endTime = System.currentTimeMillis();
            System.out.println("结果输出文件位置：" + filePathBucket);
            System.out.println("木桶法 耗时（毫秒）：" + (endTime - beginTime));

            String filePathMerge = "Result_01/repeatByMerge.txt";
            beginTime = System.currentTimeMillis();
            getRepeatNum1(arrTxt.clone(), filePathMerge);
            endTime = System.currentTimeMillis();
            System.out.println("结果输出文件位置：" + filePathMerge);
            System.out.println("归并排序法 耗时（毫秒）：" + (endTime - beginTime));

            System.out.println();
        }

        // 3：从上面的TXT文件中读取数组，并找出最大值以及所在位置；
        {
            System.out.println("3：从上面的TXT文件中读取数组，并找出最大值以及所在位置；");
            ArrayList<Integer> maxNumPosition = new ArrayList<>();
            beginTime = System.currentTimeMillis();
            int maxNum = IntArrayUtil.getMaxNum(arrTxt.clone(), maxNumPosition);
            endTime = System.currentTimeMillis();

            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
            System.out.println("最大值：" + maxNum);
            System.out.println("最大值所在位置：" + maxNumPosition.toString());
            System.out.println("结束");

            System.out.println();
        }

        // 4：从上面的TXT文件中读取数组，并进行从小到大的排序；
        {
            System.out.println("4：从上面的TXT文件中读取数组，并进行从小到大的排序；");
            System.out.println("使用归并排序法");
            beginTime = System.currentTimeMillis();
            mergeSort.mergeSortArray(arrTxt.clone());
            endTime = System.currentTimeMillis();
            System.out.println("耗时（毫秒）：" + (endTime - beginTime));
            System.out.println("结束");

            System.out.println();
        }
    }
}