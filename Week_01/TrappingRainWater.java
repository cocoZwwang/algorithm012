//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针 
// 👍 1446 👎 0


package com.shuzijun.leetcode.editor.en;
public class TrappingRainWater{
  public static void main(String[] args) {
       Solution solution = new TrappingRainWater().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int leftMax = 0;
        int rightnMax = 0;
        for (int left = 0, right = height.length - 1; left < right;){
            if(height[left] < height[right]){
                if(height[left] > leftMax){
                    leftMax = height[left++];
                }else {
                    ans += leftMax - height[left++];
                }
            }else {
                if(height[right] > rightnMax){
                    rightnMax = height[right--];
                }else {
                    ans += rightnMax - height[right--];
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}