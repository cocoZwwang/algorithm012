//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 505 👎 0


package com.shuzijun.leetcode.editor.en;

public class SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new SudokuSolver().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solveSudoku(char[][] board) {
            boolean[][] rows = new boolean[9][9];
            boolean[][] cols = new boolean[9][9];
            boolean[][] blocks = new boolean[9][9];
            //初始化
            for (int i = 0; i < 81; i++) {
                int row = i / 9;
                int col = i % 9;
                char ch = board[row][col];
                if (ch != '.') {
                    int num = ch - '1';
                    int index = (row / 3) * 3 + col / 3;
                    rows[row][num] = cols[col][num] = blocks[index][num] = true;
                }
            }
            helper(board, 0, rows, cols, blocks);
        }

        private boolean helper(char[][] board, int level, boolean[][] rows, boolean[][] cols, boolean[][] blocks) {
            if (level == 81) {
                return true;
            }

            int row = level / 9;
            int col = level % 9;
            int blockIntex = (row / 3) * 3 + col / 3;
            char ch = board[row][col];

            //如果不是空格，什么都不需要做，直接递归下一层
            if (ch != '.') {
                return helper(board, level + 1, rows, cols, blocks);
            }
            //空格尝试每一个数字，如果走不通，就停止
            for (int i = 0; i < 9; i++) {
                if (rows[row][i] || cols[col][i] || blocks[blockIntex][i]) {
                    continue;
                }
                board[row][col] = (char) (i + '1');
                rows[row][i] = cols[col][i] = blocks[blockIntex][i] = true;
                //如果找到了一个答案就停止递归
                if (helper(board, level + 1, rows, cols, blocks)) {
                    return true;
                } else {
                    //如果没找到就恢复当前层的数据状态
                    board[row][col] = '.';
                    rows[row][i] = cols[col][i] = blocks[blockIntex][i] = false;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}