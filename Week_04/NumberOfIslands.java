//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 687 👎 0


package com.shuzijun.leetcode.editor.en;

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rowCount = grid.length;
            int colCount = grid[0].length;
            if (colCount == 0 || rowCount == 0) {
                return 0;
            }
            int cnt = 0;
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    if (grid[row][col] == '1') {
                        cnt++;
                        dfs(grid, row, col);
                    }
                }
            }
            return cnt;
        }

        private void dfs(char[][] grid, int row, int col) {
            int rowCount = grid.length;
            int colCount = grid[0].length;
            if (row < 0 || row == rowCount || col < 0 || col == colCount || grid[row][col] == '0') {
                return;
            }

            grid[row][col] = '0';
            dfs(grid, row + 1, col);
            dfs(grid, row - 1, col);
            dfs(grid, row, col + 1);
            dfs(grid, row, col - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}