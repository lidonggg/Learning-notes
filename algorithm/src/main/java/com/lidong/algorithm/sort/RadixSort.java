package com.lidong.algorithm.sort;

/**
 * @author Ls J
 * @version 2019/4/30 10:43
 * 基数排序，使用线性排序的方法的话则时间复杂度为O(n)；需要代码控制稳定性，否则没有意义
 * 基数排序需要数组可以分割出独立的位来进行排序，而且位之间有递进关系，如果a的高位比b的高位大，则低位就不用比较了
 * 比如用于字典的排序等
 */
public class RadixSort implements SortFactory<String[]> {

    @Override
    public void sort(String[] strArr) {
        radixSort(strArr, 3);
    }

    /**
     * 基数排序
     *
     * @param strArr 待排序数组
     * @param index  当前供排序的索引，要从最后一位往前
     */
    private static void radixSort(String[] strArr, int index) {
        while (index >= 0) {
            countingSort(strArr, index);
            index--;
        }
    }

    /**
     * 通过计数排序来完成基数排序的按位比较
     *
     * @param strArr   待排序数组
     * @param curIndex 当前位索引
     */
    private static void countingSort(String[] strArr, int curIndex) {
        int len = strArr.length;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = strArr[i].charAt(curIndex);
        }

        // 查找数组中的最大值
        int max = arr[0];
        for (int i = 1; i < len; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 计数数组，counts[i] = m，表示元素组中值为 i 的元素共有 m 个
        int[] counts = new int[max + 1];
        // 每一项初始化为0
        for (int i = 0; i <= max; i++) {
            counts[i] = 0;
        }

        // 计算每个元素出现的次数
        for (int anArr : arr) {
            counts[anArr]++;
        }

        // 依次累加
        // 累加完之后，counts[i]的值代表小于等于 i 的元素个数
        // 也可以只使counts[i]的值代表小于 i 的元素个数，这样插入操作就可以从前往后便利，每次在counts[arr[i]]的位置插入
        for (int i = 1; i <= max; i++) {
            counts[i] += counts[i - 1];
        }

        // 申请一个临时数组
        String[] strTmp = new String[len];
        // 从后往前遍历，能够保证计数排序是稳定的
        // 因为插入操作是从后往前进行的，这样能保证相同值的元素的相对位置保持不变
        for (int i = len - 1; i >= 0; i--) {
            // 下标从0开始，所以这里要-1
            int index = counts[strArr[i].charAt(curIndex)] - 1;
            strTmp[index] = strArr[i];
            // 每次插入之后，剩余数组中小于等于arr[i]的元素个数-1，也就是说它之前的插槽少了一个，依此类推
            counts[arr[i]]--;
        }

        // 结果拷贝给原数组
        System.arraycopy(strTmp, 0, strArr, 0, len);
    }

    public static void main(String[] args) {
        // 假设每个字符串最大长度为4
        int totalLen = 4;
        // 为了方便，我们保证了每一个字符串的长度相同，否则要在末位补0使长度相同
        // 因为根据ASCII值，所有字母的值都大于0，所以在末位加0对数组排序没有影响
        String[] strArr = new String[]{"abcd", "haaa", "cccc", "dddd", "cdav", "dabv", "gbba", "gaae"};

        radixSort(strArr, totalLen - 1);

        for (String s : strArr) {
            System.out.print(s + "  ");
        }

    }
}