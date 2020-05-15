package com.lidong.algorithm.leetcode.middling.twopointers;

/**
 * 删除排序数组中的重复项 II（中等-80）
 * 中文链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * <p>
 * 问题描述：
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 * @author ls J
 * @date 2020/5/15 9:41
 */
public class RemoveDuplicatesFromSortedArrayII80 {

    /**
     * 方法一：从前往后遍历
     * 这种方法在移动的时候会同时移动后面很多重复的元素，耗时较高
     *
     * @param nums nums
     * @return len
     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        // 初始化两个指针
        int left = 0, right = 0;
        // 要前移的开始位置
        int offset;
        // start 指向要被替换的第一个位置
        int start;
        while (right < len) {
            while (nums[left] == nums[right] && right + 1 < len && nums[left] == nums[right + 1]) {
                right++;
            }
            // right - left > 1 时说明相同的值超过了 2 个
            if (right - left > 1) {
                // right + 1 指向第一个不等于当前元素的值，它是要前移的开始位置
                offset = right + 1;
                // start 指向要被替换的第一个位置
                start = left + 2;
                while (offset < len) {
                    nums[start++] = nums[offset++];
                }
                // 减少剩余数组长度
                len = len - (right - (left + 1));
                // 遍历下一个数字
                left = left + 2;
                right = left;
            } else {
                // 当前数字
                left = right + 1;
                right = left;
            }
        }

        return len;
    }

    /**
     * 方法二：从后往前遍历
     * 执行用时：1 ms，在所有 Java 提交中击败了 98.43% 的用户
     * 内存消耗：40 MB，在所有 Java 提交中击败了 8.33% 的用户
     *
     * @param nums nums
     * @return len
     */
    public static int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        // 初始化两个指针
        // 选择从后往前遍历，可以很大程度上减少元素移动的次数，因为从后往前能够保证移动的都是留下来的元素
        // 否则从前往后的话，后面会有很多多余的元素也需要移动
        int left = len - 1, right = len - 1;
        // right + 1 指向第一个不等于当前元素的值，它是要前移的开始位置
        int offset;
        // start 指向要被替换的第一个位置
        int start;
        while (left >= 0) {
            while (nums[left] == nums[right] && left - 1 >= 0 && nums[right] == nums[left - 1]) {
                left--;
            }
            if (right - left > 1) {
                // right + 1 指向第一个不等于当前元素的值，它是要前移的开始位置
                offset = right + 1;
                // start 指向要被替换的第一个位置
                start = left + 2;
                while (offset < len) {
                    nums[start++] = nums[offset++];
                }
                // 减少剩余数组长度
                len = len - (right - (left + 1));
                // 遍历下一个数字
                left = left - 1;
                right = left;
            } else {
                right = left - 1;
                left = right;
            }
        }

        return len;
    }

    /**
     * 方法三：
     * 官方题解：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/shan-chu-pai-xu-shu-zu-zhong-de-zhong-fu-xiang-i-7
     *
     * @param nums nums
     * @return len
     */
    public int removeDuplicates3(int[] nums) {

        // j：构造出的新数组的长度
        // count: 每个数字出现次数
        int j = 1, count = 1;

        for (int i = 1; i < nums.length; i++) {

            // 如果当前元素与前一个相同，当前元素 count + 1
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                // 否则代表是第一个不同的数，count 置为 1
                count = 1;
            }

            // 如果 count 不大于 2，则将其添加到新数组的末尾
            // 否则不做任何操作，遍历下一个数字
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    /**
     * 方法四：来自官方题解的评论，方法三的简化版本
     * 从前遍历，原地替换
     *
     * @param nums nums
     * @return len
     */
    public static int removeDuplicates4(int[] nums) {
        int i = 0;
        for (int n : nums) {
            // 如果当前元素比它前面隔一个位置的元素要大（不相等），说明它需要添加到数组中
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 3, 4, 4, 4};
        System.out.println(removeDuplicates2(nums));
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
