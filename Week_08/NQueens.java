//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 501 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            helper(n, 0, 0, 0, new ArrayList<>(), res);
            return res;
        }

        private void helper(int n, int row, int ld, int rd, List<String> list, List<List<String>> res) {
            int size = (1 << n) - 1;
            if ((row & size) == size) {
                res.add(new ArrayList<>(list));
                return;
            }

            int bits = (~(row | ld | rd)) & size;
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            while (bits != 0) {
                int p = bits & (-bits);
                bits = bits & (bits - 1);

                int col = getCol(p);
                chars[col] = 'Q';

                list.add(String.valueOf(chars));
                helper(n, row | p, (ld | p) << 1, (rd | p) >> 1, list, res);

                chars[col] = '.';
                list.remove(list.size() - 1);
            }
        }

        private int getCol(int p) {
            int cnt = -1;
            while (p != 0) {
                p = p >> 1;
                cnt++;
            }
            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}