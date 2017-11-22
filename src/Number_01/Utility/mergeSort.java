package Number_01.Utility;

public class mergeSort {

    /**
     归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     1.首先考虑下如何将将二个有序数列合并。这个非常简单，只要从比较二个数列的第一个数，谁小就先取谁，取了后就在对应数列中删除这个数。然后再进行比较，如果有数列为空，那直接将另一个数列的数据依次取出即可。
     可以看出合并有序数列的效率是比较高的，可以达到O(n)。
     2.解决了上面的合并有序数列问题，再来看归并排序，其的基本思路就是将数组分成二组A，B，如果这二组组内的数据都是有序的，那么就可以很方便的将这二组数据进行排序。如何让这二组组内数据有序了？
     可以将A，B组各自再分成二组。依次类推，当分出来的小组只有一个数据时，可以认为这个小组组内已经达到了有序，然后再合并相邻的二个小组就可以了。这样通过先递归的分解数列，再合并数列就完成了归并排序。
     */

    /**
     * 归并排序：1.将两个有序表合并成一个新的有序表
     * @param array
     * @param tempArr
     * @param first
     * @param middle
     * @param last
     */
    private static void mergeArray(int[] array, int[] tempArr, int first, int middle, int last) {
        int i = first, j = middle;
        int m = middle + 1, n = last;
        int k = 0;

        while (i <= j && m <= n) {
            if (array[i] < array[m]) {
                tempArr[k++] = array[i++];
            } else {
                tempArr[k++] = array[m++];
            }
        }

        while (i <= j) {
            tempArr[k++] = array[i++];
        }

        while (m <= n) {
            tempArr[k++] = array[m++];
        }

        for (int k2 = 0; k2 < k; k2++) {
            array[k2 + first] = tempArr[k2];
        }
    }

    /**
     * 归并排序：2.将无序数组进行排序
     * @param array
     * @param tempArr
     * @param first
     * @param last
     * @return
     */
    private static int[] mergeSort(int[] array, int[] tempArr, int first, int last) {
        int middle = (first + last) / 2;
        if (first < last) {
            mergeSort(array, tempArr, first, middle); // 左边
            mergeSort(array, tempArr, middle + 1, last); // 右边
            mergeArray(array, tempArr, first, middle, last);
        }
        return array;
    }

    /**
     * 归并排序：3.数组排序
     * @param array
     * @return
     */
    public static int[] mergeSortArray(int[] array) {
        int length = array.length;
        int[] arrSort = array.clone();
        int[] tempArr = new int[length];
        if (array != null && length > 0) {
            int last = length - 1;
            arrSort = mergeSort(arrSort, tempArr, 0, last);
        } else {
            System.out.println("传入数组为空");
        }
        return arrSort;
    }
}
