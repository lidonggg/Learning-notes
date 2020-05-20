package com.lidong.algorithm.leetcode.hard.slidingwindow;

import java.util.ArrayDeque;

/**
 * 滑动窗口最大值（困难-239）
 * 中文链接：https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * 问题描述：
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7        3
 * 1  3 [-1  -3  5] 3  6  7        5
 * 1  3  -1 [-3  5  3] 6  7        5
 * 1  3  -1  -3 [5  3  6] 7        6
 * 1  3  -1  -3  5 [3  6  7]       7
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * @author ls J
 * @date 2020/5/20 13:37
 */
public class SlidingWindowMaximum239 {

    /**
     * 方法一：记忆化移动滑动窗口
     * <p>
     * 执行用时：2 ms，在所有 Java 提交中击败了 98.59% 的用户
     * 内存消耗：51.5 MB，在所有 Java 提交中击败了 6.67% 的用户
     *
     * @param nums nums
     * @param k    k
     * @return res arr
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        int[] maxes = new int[nums.length - k + 1];

        int i, j;
        // 遍历到当前元素的时候，前一个窗口最大元素的位置
        int maxPos = -1;

        for (i = 0; i <= nums.length - k; ++i) {
            // 以 i 开头，j 结尾的滑动窗口
            j = i + k - 1;

            // 如果新元素大于等于前一个窗口的最大元素，则它肯定是当前滑动窗口的最大值
            if (maxPos != -1 && nums[j] >= nums[maxPos]) {
                maxPos = j;
                maxes[i] = nums[maxPos];
            }
            // 如果新元素小于前一个窗口的最大元素，但是前一个窗口的最大元素还存在于当前窗口中，那么当前窗口的最大元素就是它
            else if (i <= maxPos) {
                maxes[i] = nums[maxPos];
            }
            // 如果新元素小于前一个窗口的最大元素，并且前一个窗口的最大元素不存在于当前窗口中，那么需要重新遍历当前窗口，寻找最大元素
            else {
                int maxWindow = Integer.MIN_VALUE;
                int maxPosWindow = 0;
                for (int z = i; z <= j; ++z) {
                    if (nums[z] > maxWindow) {
                        maxPosWindow = z;
                        maxWindow = nums[z];
                    }
                }
                maxPos = maxPosWindow;
                maxes[i] = nums[maxPos];
            }
        }
        return maxes;
    }

    private ArrayDeque<Integer> deq = new ArrayDeque<>();

    private int[] nums;

    private void cleanDeque(int i, int k) {
        System.out.println("before:" + deq);
        // 只保留当前滑动窗口中的所有元素
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // 移除比 nums[i] 小的所有元素
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }

        System.out.println("after:" + deq);
    }

    /**
     * 方法二：
     * 利用双端队列，该数据结构可以从两端以常数时间压入/弹出元素。
     * 双向队列存储数组的索引值，实现类似于大顶堆的效果，保证队列的第一个元素永远是当前最大的
     * <p>
     * 执行用时：13 ms，在所有 Java 提交中击败了 64.70% 的用户
     * 内存消耗：54.6 MB，在所有 Java 提交中击败了 6.67% 的用户
     * <p>
     * 时间复杂度：O(n)
     *
     * @param nums nums
     * @param k    k
     * @return res arr
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        this.nums = nums;
        int maxIdx = 0;
        // 初始化双端队列
        for (int i = 0; i < k; ++i) {
            // 始终保证队列第一个元素是当前最大的，实现类似于大顶堆的效果，所以每次都要先 clean 一下
            cleanDeque(i, k);
            deq.addLast(i);
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        int[] res = new int[n - k + 1];
        res[0] = nums[maxIdx];
        for (int i = k; i < n; ++i) {
            cleanDeque(i, k);
            deq.addLast(i);
            // 队头保存的是第（i - k + 1）个最大元素的索引
            res[i - k + 1] = nums[deq.getFirst()];
        }
        return res;
    }

    /**
     * 方法三：
     * 动态规划
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetcode-3/
     * <p>
     * 算法的思想是将输入数组分割成有 k 个元素的块。
     * 若 n % k != 0，则最后一块的元素个数可能更少。
     * 开头元素为 i ，结尾元素为 j 的当前滑动窗口可能在一个块内，也可能在两个块中。
     * 情况 1 比较简单。 建立数组 left， 其中 left[j] 是从块的开始到下标 j 最大的元素，方向 左->右。
     * 为了处理更复杂的情况 2，我们需要数组 right，其中 right[j] 是从块的结尾到下标 j 最大的元素，方向 右->左。right 数组和 left 除了方向不同以外基本一致。
     * 两数组一起可以提供两个块内元素的全部信息。考虑从下标 i 到下标 j的滑动窗口。 根据定义，right[i] 是左侧块内的最大元素， left[j] 是右侧块内的最大元素。因此滑动窗口中的最大元素为 max(right[i], left[j])。
     * <p>
     * 算法：
     * 1.从左到右遍历数组，建立数组 left。
     * 2.从右到左遍历数组，建立数组 right。
     * 3.建立输出数组 max(right[i], left[i + k - 1])，其中 i 取值范围为 (0, n - k + 1)。
     * <p>
     * 时间复杂度：O(n)
     *
     * @param nums nums
     * @param k    k
     * @return res arr
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        int[] left = new int[n];
        left[0] = nums[0];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            int j = n - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            res[i] = Math.max(left[i + k - 1], right[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, -1, 3, 2, -3, 5, 3, 6, 7};

        SlidingWindowMaximum239 swm = new SlidingWindowMaximum239();
        int[] res = swm.maxSlidingWindow(arr, 3);
        for (int a : res) {
            System.out.println(a);
        }
    }
}
