//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 358 👎 0


package com.shuzijun.leetcode.editor.en;

public class SurroundedRegions {
    public static void main(String[] args) {
        Solution solution = new SurroundedRegions().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
                return;
            }

            int rowCount = board.length;
            int colCount = board[0].length;

            for (int col = 0; col < colCount; col++) {
                if (board[0][col] == 'O') {
                    helper(board, 0, col);
                }

                if (board[rowCount - 1][col] == 'O') {
                    helper(board, rowCount - 1, col);
                }
            }

            for (int row = 0; row < rowCount; row++) {
                if (board[row][0] == 'O') {
                    helper(board, row, 0);
                }

                if (board[row][colCount - 1] == 'O') {
                    helper(board, row, colCount - 1);
                }
            }

            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    if (board[row][col] == '*') {
                        board[row][col] = 'O';
                    } else {
                        board[row][col] = 'X';
                    }
                }
            }
        }

        private void helper(char[][] board, int row, int col) {
            int rowCount = board.length;
            int colCount = board[0].length;
            if (row < 0 || row >= rowCount || col < 0 || col >= colCount || board[row][col] != 'O') {
                return;
            }

            board[row][col] = '*';
            helper(board, row + 1, col);
            helper(board, row - 1, col);
            helper(board, row, col + 1);
            helper(board, row, col - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}