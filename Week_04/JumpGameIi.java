//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组 
// 👍 644 👎 0


package com.shuzijun.leetcode.editor.en;

public class JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new JumpGameIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            int steps = 0;
            int i = 0;
            int end = 0;
            int maxPostion;
            //每次跳跃到end，steps++
            //直到end > nums.length - 1
            while (end < nums.length - 1) {
                // 假如上一次跳跃点是lastEnd，当前跳跃点是end
                // 跳跃完后在区间（lastend,end]找到最远的跳跃点作为下一次的跳跃点
                maxPostion = end;
                while (i <= end) {
                    if (i + nums[i] > maxPostion) {
                        maxPostion = nums[i] + i;
                    }
                    i++;
                }
                steps++;
                end = maxPostion;
            }
            return steps;
        }

        // public int jump(int[] nums){
        //     int n = nums.length;
        //     int steps = 0;
        //     int end = 0;
        //     int maxPostion = 0;
        //     for(int i = 0; i < n - 1; i++){
        //         maxPostion = Math.max(maxPostion,nums[i] + i);
        //         if(i == end){
        //             end = maxPostion;
        //             steps++;
        //         }
        //     }
        //     return steps;
        // }

    }
//leetcode submit region end(Prohibit modification and deletion)

}