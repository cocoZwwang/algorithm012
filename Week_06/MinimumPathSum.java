//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划 
// 👍 622 👎 0


package com.shuzijun.leetcode.editor.en;

public class MinimumPathSum {
    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
            }

            int rowCount = grid.length;
            int colCount = grid[0].length;

            int[] da = new int[colCount];
            da[0] = grid[0][0];
            for (int i = 1; i < colCount; i++) {
                da[i] = da[i - 1] + grid[0][i];
            }

            for (int row = 1; row < rowCount; row++) {
                da[0] = da[0] + grid[row][0];
                for (int col = 1; col < colCount; col++) {
                    da[col] = Math.min(da[col], da[col - 1]) + grid[row][col];
                }
            }

            return da[colCount - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}