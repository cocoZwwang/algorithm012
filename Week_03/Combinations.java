//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 320 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        Solution solution = new Combinations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(1, n, k, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int first, int n, int k, List<Integer> list, List<List<Integer>> result) {
            if (list.size() == k) {
                result.add(new ArrayList<>(list));
                return;
            }

            for (int i = first; i < n + 1; i++) {
                list.add(i);
                backtrack(i + 1, n, k, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}