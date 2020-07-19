//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 395 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            //如果不用lambda表达式，用原始的for往map里面添加元素会快上2秒左右
            //但是在时间复杂度相同的情况下，我更加愿意用下面这种，更加优雅，颜值比较重要！！！
            Map<Integer,List<String>> map = Stream.of(strs)
                    .collect(Collectors.groupingBy(s -> anagramsCode(s), Collectors.toList()));
            return new ArrayList<>(map.values());
        }

        private int anagramsCode(String s) {
            int[] alp = new int[26];
            for (int i = 0; i < s.length(); i++) {
                alp[s.charAt(i) - 'a']++;
            }
            return Arrays.hashCode(alp);
        }

//        private static final int[] PRIMES = new int[]{2, 3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
//                53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};

//        private int anagramsCode(String s) {
//            int[] alp = new int[26];
//            int code = 1;
//            for (int i = 0; i < s.length(); i++) {
//                code *= PRIMES[s.charAt(i) - 'a'];
//            }
//            return code;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}