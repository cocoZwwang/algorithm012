//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 358 👎 0


package com.shuzijun.leetcode.editor.en;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Set<List<Integer>> result = new HashSet<>();
            Set<Integer> set = new HashSet<>();
            Arrays.sort(nums);
            permute(nums, set, result, new ArrayList<>());
            return new ArrayList<>(result);
        }

        private void permute(int[] nums, Set<Integer> set, Set<List<Integer>> result, List<Integer> list) {
            //终结条件
            if (list.size() == nums.length) {
                result.add(new ArrayList<>(list));
                return;
            }

            Set<Integer> used = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                //处理逻辑
                if (set.contains(i) || used.contains(nums[i])) {
                    continue;
                }
                list.add(nums[i]);
                set.add(i);
                used.add(nums[i]);
                permute(nums, set, result, list);
                list.remove(list.size() - 1);
                set.remove(i);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}