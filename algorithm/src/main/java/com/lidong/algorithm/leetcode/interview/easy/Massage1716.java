package com.lidong.algorithm.leetcode.interview.easy;

/**
 * 按摩师（简单--面试题17.16）
 * 题目描述：
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例 1：
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * <p>
 * 示例 2：
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * <p>
 * 示例 3：
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 *
 * @author ls J
 * @date 2020/3/24 13:31
 */
public class Massage1716 {

    private int len;

    private int result;

    private int[] nums;

    /**
     * 存储遍历到当前元素时已取的最大值
     */
    private int[] curMax;

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        this.nums = nums;
        this.len = nums.length;
        this.curMax = new int[this.len];

        result = 0;
        recurse(0, 0);
        return result;
    }

    /**
     * 典型的回溯问题，针对每一个当前的元素，都考虑放和不放两种情况
     * 为了提高执行效率，，这里使用一个临时的数组来存放遍历到当前元素的时候（包含当前元素），已经产生的最大值，之后如果遍历到当前元素的时候比这个最大值小，那么直接退出递归
     *
     * @param index  遍历到的当前的元素的索引
     * @param curRes 遍历到当前元素的时候的总和
     */
    private void recurse(int index, int curRes) {
        if (index > len - 1) {
            if (curRes > result) {
                result = curRes;
            }
            return;
        }
        // 不取当前元素，直接遍历下一个元素
        recurse(index + 1, curRes);
        curRes += nums[index];
        // 利用制表法减少无效的递归
        if (curRes <= curMax[index]) {
            return;
        }
        curMax[index] = curRes;
        // 取当前元素，则需要遍历下下一个元素
        recurse(index + 2, curRes);

    }

    public static void main(String[] args) {
        Massage1716 mag = new Massage1716();
        System.out.println(mag.massage(new int[]{114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240}));
    }
}
