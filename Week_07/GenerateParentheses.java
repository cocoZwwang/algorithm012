//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1228 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            dfs(n, 0, 0, "", res);
            return res;
        }

        private void dfs(int n, int left, int right, String s, List<String> res) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }

            if (left < n) {
                dfs(n, left + 1, right, s + "(", res);
            }

            if (right < left) {
                dfs(n, left, right + 1, s + ")", res);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}