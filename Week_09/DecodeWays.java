//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 491 👎 0


package com.shuzijun.leetcode.editor.en;

public class DecodeWays {
    public static void main(String[] args) {
        Solution solution = new DecodeWays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] chars = s.toCharArray();
            if (chars[0] == '0') {
                return 0;
            }
            int n = chars.length;
            int pre1 = 1;
            int pre2 = 1;
            int ans = pre1;
            for (int i = 1; i < n; i++) {
                if (chars[i] == '0') {
                    if (chars[i - 1] == '1' || chars[i - 1] == '2') {
                        ans = pre2;
                    } else {
                        ans = 0;
                    }
                } else if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                    ans = pre2 + pre1;
                } else {
                    ans = pre1;
                }
                pre2 = pre1;
                pre1 = ans;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}