//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 你可以假设数组中不存在重复元素。 
//
// 示例 1: 
//
// 输入: [3,4,5,1,2]
//输出: 1 
//
// 示例 2: 
//
// 输入: [4,5,6,7,0,1,2]
//输出: 0 
// Related Topics 数组 二分查找 
// 👍 226 👎 0


package com.shuzijun.leetcode.editor.en;

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int n = nums.length;

            while (left <= right) {
                int mid = (right - left) / 2 + left;
                //目标值比较
                int t = nums[mid];
                //数组没被旋转(t > nums[0]  && t < nums[n - 1])，直接返回第一个元素
                if (t > nums[0] && t < nums[n - 1]) {
                    return nums[0];
                }
                //后面是处理数组发生了旋转的情况
                if (t <= nums[(mid - 1 + n) % n] && t <= nums[(mid + 1) % n]) {
                    return nums[mid];
                } else if (t < nums[0]) { //左边包含旋转点（t < nums[0]）
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}