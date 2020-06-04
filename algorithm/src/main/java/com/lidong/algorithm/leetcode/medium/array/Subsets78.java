package com.lidong.algorithm.leetcode.medium.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集（中等-78）
 * 中文链接：https://leetcode-cn.com/problems/subsets
 * <p>
 * 问题描述：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * <p>  [3],
 * <p>  [1],
 * <p>  [2],
 * <p>  [1,2,3],
 * <p>  [1,3],
 * <p>  [2,3],
 * <p>  [1,2],
 * <p>  []
 * ]
 *
 * @author Ls J
 * @date 2020/5/16 10:54 PM
 */
public class Subsets78 {

    /**
     * 结果集
     */
    private List<List<Integer>> result = new LinkedList<>();

    /**
     * 原始数组
     */
    private int[] nums;

    /**
     * 方法一：递归
     *
     * @param nums nums
     * @return list
     */
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        result.add(new ArrayList<>());
        preOrder(0, new LinkedList<>());
        return result;
    }

    /**
     * DFS 前序遍历：每次针对当前的元素都有两种情况：不插入或者插入（相当于构成了树的左右节点，其中左子节点代表不选，右子节点代表选）
     * 类似的，也可以用中序遍历和后续遍历的方法
     *
     * @param i      当前遍历到数组下标
     * @param subset 当前元组
     */
    private void preOrder(int i, LinkedList<Integer> subset) {
        if (i >= nums.length) {
            return;
        }

        subset = new LinkedList<>(subset);
        // 当前列表先保存到结果列表中
        result.add(subset);
        // 继续递归
        preOrder(i + 1, subset);
        // 添加下一个元素
        subset.add(nums[i]);
        // 继续递归
        preOrder(i + 1, subset);
    }

    /**
     * 方法二：利用二进制位掩码
     * 来自题解：https://leetcode-cn.com/problems/subsets/solution/er-jin-zhi-wei-zhu-ge-mei-ju-dfssan-chong-si-lu-9c/
     *
     * @param nums num arr
     * @return res
     */
    public List<List<Integer>> subsets2(int[] nums) {
        for (int i = 0; i < (1 << nums.length); i++) {
            List<Integer> sub = new LinkedList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }

            result.add(sub);
        }
        return result;
    }

    /**
     * 方法三：利用二进制位掩码
     * 来自官方题解：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode/
     *
     * @param nums num arr
     * @return res
     */
    public List<List<Integer>> subsets3(int[] nums) {
        int n = nums.length;

        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
            // 从 00..0 到 11..1 构造二进制位
            // 如果当前位是 0，代表存放当前数字，否则相反
            String bitmask = Integer.toBinaryString(i).substring(1);

            // 通过二进位掩码构造当前数组
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            result.add(curr);
        }
        return result;
    }

    /**
     * 方法四：回溯
     * 来自题解：https://leetcode-cn.com/problems/subsets/solution/er-jin-zhi-wei-zhu-ge-mei-ju-dfssan-chong-si-lu-9c/
     *
     * @param nums nums arr
     * @param i    current index
     * @param sub  subset
     * @param res  res
     */
    public static void backtrack(int[] nums, int i, List<Integer> sub, List<List<Integer>> res) {
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            sub.add(nums[j]);
            res.add(new ArrayList<>(sub));
            backtrack(nums, j + 1, sub, res);
            sub.remove(sub.size() - 1);
        }
    }

    /**
     * 方法五：枚举
     * 逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
     *
     * @param nums num arr
     * @return res
     */
    public static List<List<Integer>> enumerate(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (Integer n : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSub = new ArrayList<>(res.get(i));
                newSub.add(n);
                res.add(newSub);
            }
        }
        return res;
    }
}
