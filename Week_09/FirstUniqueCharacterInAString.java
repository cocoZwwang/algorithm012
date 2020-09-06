//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 258 👎 0


package com.shuzijun.leetcode.editor.en;
public class FirstUniqueCharacterInAString{
  public static void main(String[] args) {
       Solution solution = new FirstUniqueCharacterInAString().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstUniqChar(String s) {
        int ans = -1;
        for(char ch = 'a'; ch <= 'z'; ch++){
            int index = s.indexOf(ch);
            if(index != -1 && index == s.lastIndexOf(ch)){
                ans = ans == - 1 || index < ans ?  index : ans;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}