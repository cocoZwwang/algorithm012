//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
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
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 394 👎 0


package com.shuzijun.leetcode.editor.en;

import javafx.util.Pair;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //BFS
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) {
                return 0;
            }

            //构造字典图
            Map<String, List<String>> wordMatchMap = new HashMap<>();
            for (String word : wordList) {
                for (String matchKey : getMatchKeys(word)) {
                    List<String> macthList = wordMatchMap.getOrDefault(matchKey, new ArrayList<>());
                    macthList.add(word);
                    wordMatchMap.put(matchKey, macthList);
                }
            }

            //BFS
            Deque<Pair<String, Integer>> deque = new LinkedList<>();
            deque.offer(new Pair<>(beginWord, 1));

            Set<String> visited = new HashSet<>();
            visited.add(endWord);
            while (!deque.isEmpty()) {
                Pair<String, Integer> pair = deque.poll();
                String w = pair.getKey();
                int level = pair.getValue();
                List<String> matchKeys = getMatchKeys(w);
                for (String key : matchKeys) {
                    for (String matchWord : wordMatchMap.getOrDefault(key, new ArrayList<>())) {
                        if (matchWord.equals(endWord)) {
                            return level + 1;
                        }
                        if (!visited.contains(matchWord)) {
                            visited.add(matchWord);
                            deque.offer(new Pair<>(matchWord, level + 1));
                        }
                    }
                }
            }
            return 0;
        }

        private List<String> getMatchKeys(String word) {
            List<String> list = new ArrayList<>();
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                chars[i] = '*';
                String macth = String.valueOf(chars);
                list.add(macth);
                chars[i] = word.charAt(i);
            }
            return list;
        }
        //dfs
//        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//            Set<String> wordSet = new HashSet<>(wordList);
//            if (!wordSet.contains(endWord)) {
//                return 0;
//            }
//
//            //构造字典图
//            Map<String, List<String>> wordMatchMap = new HashMap<>();
//            for (String word : wordList) {
//                for (String matchKey : getMatchKeys(word)) {
//                    List<String> macthList = wordMatchMap.getOrDefault(matchKey, new ArrayList<>());
//                    macthList.add(word);
//                    wordMatchMap.put(matchKey, macthList);
//                }
//            }
//            return dfs(beginWord, endWord, 1, 0,new HashSet<>(), wordSet, wordMatchMap);
//        }
//
//        private int dfs(String benginWord, String endWord, int level, int min, Set<String> visited,
//                        Set<String> wordSet, Map<String, List<String>> wordMatchMap) {
//            if(min != 0 && level >= min){
//                return min;
//            }
//
//            if (benginWord.equals(endWord)) {
//                return level;
//            }
//
//            List<String> matchKeys = getMatchKeys(benginWord);
//            for (String key : matchKeys) {
//                List<String> matchWords = wordMatchMap.getOrDefault(key, new ArrayList<>());
//                for (String mathchWord : matchWords) {
//                    if (visited.contains(mathchWord)) {
//                        continue;
//                    }
//                    visited.add(mathchWord);
//                    min = dfs(mathchWord, endWord, level + 1, min,visited, wordSet, wordMatchMap);
//                    visited.remove(mathchWord);
//                }
//            }
//            return min;
//        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}