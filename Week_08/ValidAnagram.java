//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 225 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //如果只包含小写字母那么就可以使用数组来存储
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            int[] counts = new int[26];
            for (char c : t.toCharArray()) {
                counts[c - 'a']++;
            }

            for (char c : s.toCharArray()) {
                int code = c - 'a';
                counts[code]--;
                if (counts[code] < 0) {
                    return false;
                }
            }

            return true;
        }


        //如果包含unicode字符只能使用hashMap来存储
        //字母异位词：所含字母相同，每个字母个数也相同，但是位置不一样
//        public boolean isAnagram(String s, String t) {
//            if (s.length() != t.length()) {
//                return false;
//            }
//
//            Map<Integer, Integer> countMap = new HashMap<>();
//            for (char c : t.toCharArray()) {
//                int code = c - 'a';
//                countMap.put(code, countMap.getOrDefault(code, 0) + 1);
//            }
//
//            for (char c : s.toCharArray()) {
//                int code = c - 'a';
//                countMap.put(code, countMap.getOrDefault(code, 0) - 1);
//                if (countMap.get(code) < 0) {
//                    return false;
//                }
//            }
//
//            return true;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}