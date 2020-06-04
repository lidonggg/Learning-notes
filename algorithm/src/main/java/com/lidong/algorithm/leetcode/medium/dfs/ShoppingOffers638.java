package com.lidong.algorithm.leetcode.medium.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 大礼包（中等-638）
 * 中文链接：https://leetcode-cn.com/problems/shopping-offers
 * <p>
 * 问题描述：
 * 在LeetCode商店中，有许多在售的物品。
 * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
 * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
 * 任意大礼包可无限次购买。
 * <p>
 * 示例 1:
 * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 输出: 14
 * 解释:
 * 有A和B两种物品，价格分别为¥2和¥5。
 * 大礼包1，你可以以¥5的价格购买3A和0B。
 * 大礼包2， 你可以以¥10的价格购买1A和2B。
 * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
 * <p>
 * 示例 2:
 * 输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * 输出: 11
 * 解释:
 * A，B，C的价格分别为¥2，¥3，¥4.
 * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
 * 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B，¥4购买1C。
 * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
 * <p>
 * 说明:
 * 最多6种物品， 100种大礼包。
 * 每种物品，你最多只需要购买6个。
 * 你不可以购买超出待购清单的物品，即使更便宜。
 *
 * @author ls J
 * @date 2020/5/8 10:05
 */
public class ShoppingOffers638 {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        return shopping(price, special, needs, map);
    }

    /**
     * 记忆化搜索，对于相同的 needs，最终的结果都是一致的
     * 对于每种商品都可以用原价购买，这个时候的总价即为 price 与 needs 对应位置之和
     * 对于每个商品我们也可以买大礼包，如果礼包中的每种物品的数量不超过需要的数量，那么就可以购买这个大礼包
     * 基于以上思路，我们可以递归搜索
     *
     * @param price   price
     * @param special special
     * @param needs   needs
     * @param map     map
     * @return res
     */
    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        if (map.containsKey(needs)) {
            return map.get(needs);
        }
        int i;
        int res = dot(needs, price);
        for (List<Integer> is : special) {
            // 对每个 special 都考虑买一个的情况，这里每次都深拷贝一个 needs 来处理
            List<Integer> clone = new ArrayList<>(needs);
            for (i = 0; i < needs.size(); ++i) {
                int diff = clone.get(i) - is.get(i);
                if (diff < 0) {
                    break;
                }
                // 如果买了大礼包，那么对应的商品所需的数量要减少
                // 下一次递归需要把调整之后的所需商品数量传进去
                clone.set(i, diff);
            }
            // 此时说明这个大礼包可以买
            if (i == needs.size()) {
                res = Math.min(res, is.get(i) + shopping(price, special, clone, map));
            }
        }
        map.put(needs, res);
        return res;
    }

    private int dot(List<Integer> a, List<Integer> b) {
        int sum = 0;
        for (int i = 0; i < a.size(); ++i) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }
}
