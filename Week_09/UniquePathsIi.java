//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 说明：m 和 n 的值均不超过 100。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
// Related Topics 数组 动态规划 
// 👍 402 👎 0


package com.shuzijun.leetcode.editor.en;

public class UniquePathsIi {
    public static void main(String[] args) {
        Solution solution = new UniquePathsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
                return 0;
            }

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[] dp = new int[n];
            dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
            for (int i = 1; i < n; i++) {

                dp[i] = obstacleGrid[0][i] == 0 ? dp[i - 1] : 0;
            }
            for (int row = 1; row < m; row++) {
                dp[0] = obstacleGrid[row][0] == 0 ? dp[0] : 0;
                for (int col = 1; col < n; col++) {
                    dp[col] = obstacleGrid[row][col] == 0 ? dp[col - 1] + dp[col] : 0;
                }
            }
            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}