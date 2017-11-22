package Number_01.Utility;

import java.io.*;
import java.util.ArrayList;

public class IntArrayUtil {

    // 生成一个由随机整数组成的无序数组
    public static int[] genRandomIntArray(int size, int range) {
        int[] randomIntArray = new int[size];
        for (int i = 0; i < size; i++) {
            double randomDouble = Math.random();
            int randomInt = (int) Math.round(randomDouble * range);
            randomInt = randomInt == 0 ? range : randomInt;
            randomIntArray[i] = randomInt;
        }
        return randomIntArray;
    }

    /**
     * 生成一个大小为size的无序数组并输出到文件，数组中的元素为1-range之间的随机整数（可重复）
     * 数组元素之间使用空格分隔
     * @param pathname
     * @param randomIntArray
     * @return
     */
    public static boolean createRandomIntArrayFile1(String pathname, int[] randomIntArray) {
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
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 生成一个大小为size的无序数组并输出到文件，数组中的元素为1-range之间的随机整数（可重复）
     * 数组元素之间使用空格分隔
     * @param pathname
     * @param randomIntArray
     * @return
     */
    public static boolean createRandomIntArrayFile2(String pathname, int[] randomIntArray) {
        File file = new File(pathname);
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
            for (int i = 0; i < randomIntArray.length; i++) {
                buff.write((randomIntArray[i] + " ").getBytes());
            }
            buff.flush();
            buff.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 生成一个大小为size的无序数组并输出到文件，数组中的元素为1-range之间的随机整数（可重复）
     * 数组元素之间使用空格分隔
     * @param pathname
     * @param randomIntArray
     * @return
     */
    public static boolean createRandomIntArrayFile3(String pathname, int[] randomIntArray) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(pathname);
            for (int i = 0; i < randomIntArray.length; i++) {
                fw.write(randomIntArray[i] + " ");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 从txt文件中读取数组
     * @param pathname
     * @return
     */
    public static int[] readArrayFromTxt1(String pathname) {
        File file = new File(pathname);
        BufferedReader reader = null;
        String txtString = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                txtString = txtString + tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arrString = txtString.split(" ");
        int[] arrInt = new int[arrString.length];
        for (int i = 0; i < arrString.length; i++) {
            arrInt[i] = Integer.valueOf(arrString[i]);
        }

        return arrInt;
    }

    /**
     * 从txt文件中读取数组
     * @param pathname
     * @return
     */
    public static int[] readArrayFromTxt2(String pathname) {
        File file = new File(pathname);
        BufferedReader reader = null;
        StringBuilder txtString = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                txtString.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arrString = txtString.toString().split(" ");
        int[] arrInt = new int[arrString.length];
        for (int i = 0; i < arrString.length; i++) {
            arrInt[i] = Integer.valueOf(arrString[i]);
        }

        return arrInt;
    }

    /**
     * 找出数组中的最大值、所在位置
     * @param array
     * @param maxNumPosition
     * @return
     */
    public static int getMaxNum(int[] array, ArrayList<Integer> maxNumPosition) {
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
}
