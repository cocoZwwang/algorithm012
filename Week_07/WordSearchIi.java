//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法 
// 👍 223 👎 0


package com.shuzijun.leetcode.editor.en;

public class WordSearchIi {
    public static void main(String[] args) {
        Solution solution = new WordSearchIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
                return res;
            }

            Trie root = new Trie();
            for (String word : words) {
                root.insert(word);
            }

            int rowCount = board.length;
            int colCount = board[0].length;

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    helper(board, root, row, col, sb.append(board[row][col]), res);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            return res;
        }

        private void helper(char[][] board, Trie root, int row, int col, StringBuilder sb, List<String> res) {
            int rowCount = board.length;
            int colCount = board[0].length;

            char ch = board[row][col];
            Trie trie = root.next[ch - 'a'];
            if (trie == null) {
                return;
            }

            if (trie.isEnd) {
                res.add(sb.toString());
                if (trie.isLeof()) {
                    root.next[ch - 'a'] = null;
                    return;
                } else {
                    trie.isEnd = false;
                }
            }

            board[row][col] = '#';
            int[] dr = new int[]{1, -1, 0, 0};
            int[] dc = new int[]{0, 0, 1, -1};
            for (int i = 0; i < 4; i++) {
                int r = row + dr[i];
                int c = col + dc[i];
                if (r < 0 || r >= rowCount || c < 0 || c >= colCount || board[r][c] == '#') {
                    continue;
                }

                helper(board, trie, r, c, sb.append(board[r][c]), res);
                sb.deleteCharAt(sb.length() - 1);
            }
            board[row][col] = ch;
        }

        private static class Trie {
            private boolean isEnd;
            private Trie[] next;

            Trie() {
                next = new Trie[26];
            }

            public void insert(String word) {
                Trie cur = this;
                for (int i = 0; i < word.length(); i++) {
                    int index = word.charAt(i) - 'a';
                    if (cur.next[index] == null) {
                        cur.next[index] = new Trie();
                    }
                    cur = cur.next[index];
                }
                cur.isEnd = true;
            }

            public boolean isLeof() {
                for (Trie node : next) {
                    if (node != null) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}