package com.lidong.algorithm.leetcode.hard.array;

import java.util.Stack;

/**
 * 接雨水（困难-42）
 * 中文链接：https://leetcode-cn.com/problems/trapping-rain-water
 * <p>
 * 问题描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author ls J
 * @date 2020/5/14 11:44
 */
public class TrappingRainWater42 {

    /**
     * 方法一：暴力解法，针对每个元素，都去找它左侧和右侧比它大的元素，得出它与两个元素的差值，这样的话，两个差值的最小值就是当前元素位置所能盛放的水量
     * <p>
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param heights height arr
     * @return res
     */
    public static int trap(int[] heights) {
        int len = heights.length;
        int res = 0;
        // 从左往右查找当前元素与它右边最高的元素差
        for (int i = 1; i < len - 1; ++i) {
            int maxLeft = heights[i], maxRight = heights[i];
            for (int j = i + 1; j < len; ++j) {
                maxRight = Math.max(maxRight, heights[j]);
            }
            if (maxRight == heights[i]) {
                continue;
            }
            for (int j = i - 1; j >= 0; --j) {
                maxLeft = Math.max(maxLeft, heights[j]);
            }
            res += maxLeft > maxRight ? maxRight - heights[i] : maxLeft - heights[i];
        }

        return res;
    }

    /**
     * 方法二：利用动态规划思想求解每个元素左侧和右侧（包含）的最大值
     * 递推公式：
     * leftMax[i] = Math.max(heights[i], leftMax[i - 1])
     * rightMax[i] = Math.max(heights[i], rightMax[i + 1])
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param heights height arr
     * @return res
     */
    public static int trap2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] leftMax = new int[len], rightMax = new int[len];
        leftMax[0] = heights[0];
        for (int i = 1; i < len - 1; ++i) {
            leftMax[i] = Math.max(heights[i], leftMax[i - 1]);
        }
        rightMax[len - 1] = heights[len - 1];
        for (int i = len - 2; i >= 1; --i) {
            rightMax[i] = Math.max(heights[i], rightMax[i + 1]);
        }
        int res = 0;
        for (int i = 1; i < len - 1; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }

        return res;
    }

    /**
     * 方法三：利用（单调递减栈）
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param heights height arr
     * @return res
     */
    public static int trap3(int[] heights) {
        int res = 0, curIdx = 0, len = heights.length;
        Stack<Integer> stack = new Stack<>();
        while (curIdx < len) {
            // 栈非空并且 heights[curIdx] > heights[stack.peek()]
            while (!stack.isEmpty() && heights[curIdx] > heights[stack.peek()]) {
                // 栈中的元素可以被弹出
                int top = stack.peek();
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 计算当前元素和栈顶元素的距离（宽度）
                int distance = curIdx - stack.peek() - 1;
                // 找出界定高度（高度）
                int boundedHeight = Math.min(heights[curIdx], heights[stack.peek()]) - heights[top];
                res += distance * boundedHeight;
            }
            stack.push(curIdx++);
        }
        return res;
    }

    /**
     * 方法四：使用双指针
     * 来自 leetcode 官方题解：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode
     * 我们可以认为如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。
     * 当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从相反的方向遍历（从右到左）。
     *
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param heights height arr
     * @return res
     */
    public static int trap4(int[] heights) {
        int left = 0, right = heights.length - 1;
        int res = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];
                } else {
                    res += (leftMax - heights[left]);
                }
                ++left;
            } else {
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];
                } else {
                    res += (rightMax - heights[right]);
                }
                --right;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
    }
}
