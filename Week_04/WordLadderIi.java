//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法 
// 👍 302 👎 0


package com.shuzijun.leetcode.editor.en;

import javafx.util.Pair;

import java.util.*;

public class WordLadderIi {
    public static void main(String[] args) {
        Solution solution = new WordLadderIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) {
                return Collections.emptyList();
            }

            List<List<String>> res = new ArrayList<>();
            //
            Map<String, List<String>> matchMap = new HashMap<>();
            for (String w : wordList) {
                List<String> keys = getMathchKeys(w);
                for (String key : keys) {
                    List<String> list = matchMap.getOrDefault(key, new ArrayList<>());
                    list.add(w);
                    matchMap.put(key, list);
                }
            }

            Deque<Pair<String, List<String>>> deque = new LinkedList<>();
            deque.offer(new Pair<>(beginWord, Arrays.asList(beginWord)));

            Set<String> visited = new HashSet<>();
            visited.add(endWord);

            boolean hasResult = false;
            while (!deque.isEmpty() && !hasResult) {
                int size = deque.size();
                Set<String> levelSet = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    Pair<String, List<String>> pair = deque.poll();
                    String word = pair.getKey();
                    List<String> list = pair.getValue();
                    List<String> keys = getMathchKeys(word);
                    for (String key : keys) {
                        List<String> matchWords = matchMap.getOrDefault(key, new ArrayList<>());
                        for (String matchWord : matchWords) {
                            if (matchWord.equals(endWord)) {
                                List<String> l = new ArrayList<>(list);
                                l.add(endWord);
                                res.add(l);
                                hasResult  = true;
                            }

                            if (!visited.contains(matchWord)) {
                                levelSet.add(matchWord);
                                List<String> newlist = new ArrayList<>(list);
                                newlist.add(matchWord);
                                deque.offer(new Pair<>(matchWord, newlist));
                            }
                        }
                    }
                }
                visited.addAll(levelSet);
            }

            return res;
        }

        private List<String> getMathchKeys(String word) {
            List<String> list = new ArrayList<>();
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                chars[i] = '*';
                list.add(String.valueOf(chars));
                chars[i] = word.charAt(i);
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}