//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组 
// 👍 759 👎 0


package com.shuzijun.leetcode.editor.en;
public class JumpGame{
  public static void main(String[] args) {
       Solution solution = new JumpGame().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

      //从后面往前贪心
      public boolean canJump(int[] nums) {
          if(nums == null){
              return false;
          }

          int end = nums.length - 1;
          for(int i = nums.length - 1; i >= 0 && end > 0; i--){
              if(i + nums[i] >= end){
                  end = i;
              }
          }

          return end == 0;
      }

      // public boolean canJump(int[] nums) {
      //     int most = 0;
      //     for(int i = 0; i < nums.length && i <= most; i++){
      //         most = Math.max(most,i + nums[i]);
      //         if(most >= nums.length - 1){
      //             return true;
      //         }
      //     }
      //     return false;
      // }

      // private int canJump(int nums[],int dest){
      //     if(dest <= 0){
      //         return dest;
      //     }

      //     int next = -1;
      //     for(int i = dest - 1; i >= 0; i--){
      //         if(i + nums[i] >= dest){
      //             next = i;
      //         }
      //     }

      //     return canJump(nums,next);
      // }

      // public boolean canJump(int[] nums) {
      //     return canJump(nums,nums.length -1) == 0;
      // }

      //暴力法
      // public boolean canJump(int[] nums) {
      //     if(nums == null || nums.length == 0){
      //         return false;
      //     }

      //     boolean[] flag = new boolean[nums.length];
      //     for(int i = nums.length - 1; i >= 0; i--){
      //         for(int j = nums[i]; j >=0 && !flag[i];  j--){
      //             flag[i] = i + j >= nums.length - 1 || flag[i + j];
      //         }
      //     }
      //     return flag[0];
      // }
}
//leetcode submit region end(Prohibit modification and deletion)

}