//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 333 👎 0


package com.shuzijun.leetcode.editor.en;
public class UglyNumberIi{
  public static void main(String[] args) {
       Solution solution = new UglyNumberIi().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        int a = 0,b = 0,c = 0;
        int[] da = new int[n];
        da[0] = 1;
        for(int i = 1; i < n; i++){
            int na = da[a] * 2;
            int nb = da[b] * 3;
            int nc = da[c] * 5;
            da[i] = Math.min(Math.min(na,nb),nc);
            if(na == da[i]) a++;
            if(nb == da[i]) b++;
            if(nc == da[i]) c++;
        }
        return da[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}