//给定一个无序的整数数组，找到其中最长上升子序列的长度。 
//
// 示例: 
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4 
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。 
//
// 说明: 
//
// 
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 
// 你算法的时间复杂度应该为 O(n2) 。 
// 
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗? 
// Related Topics 二分查找 动态规划 
// 👍 952 👎 0


package com.shuzijun.leetcode.editor.en;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            int[] dp = new int[n];
            int len = 0;
            dp[0] = nums[0];
            for (int i = 1; i < n; i++) {
                if (nums[i] > dp[len]) {
                    dp[++len] = nums[i];
                    continue;
                }
                int left = 0;
                int right = len;
                int pos = -1;
                while (left <= right) {
                    int mid = (right - left) / 2 + left;
                    if (dp[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];
            }
            return len + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}