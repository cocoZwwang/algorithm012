//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找 
// 👍 861 👎 0


package com.shuzijun.leetcode.editor.en;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] == target) {
                    return mid;
                }
                // 如果[left,mid]是升序
                if (nums[mid] >= nums[0] &&
                        // target在右区间
                        (target > nums[mid] || target < nums[0])) {
                    left = mid + 1;
                } else if (// 如果[left,mid]不是升序，包含旋转点
                    // target在右区间
                        target > nums[mid] && target < nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }


        //二分法先找出旋转点的位置
        //二分法找目标
        // public int search(int[] nums, int target) {
        //     if(nums == null || nums.length == 0){
        //         return -1;
        //     }
        //     int n= nums.length;
        //     int leftP = changedPostion(nums,0,n- 1);
        //     int rightP = (leftP +  n) % n - 1;
        //     int left = 0;
        //     int right = n - 1;
        //     while(left <= right){
        //         int mid = (right - left) / 2 + left;
        //         int midIndex = (mid + leftP) % n;
        //         if(nums[midIndex] == target){
        //             return midIndex;
        //         }else if(nums[midIndex] > target){
        //             right = mid - 1;
        //         }else{
        //             left = mid + 1;
        //         }
        //     }
        //     return -1;
        // }

        // private int changedPostion(int[] nums,int left,int right){
        //     int n = nums.length;
        //     while(left <= right){
        //         int mid = (right - left ) / 2 + left;
        //         if(nums[mid] <= nums[(mid + n - 1) % n] && nums[mid] <= nums[(mid + 1) % n]){
        //             return mid;
        //         } if(nums[mid] >= nums[left] && nums[mid] >= nums[right]){
        //             left = mid + 1;
        //         }else {
        //             right = mid - 1;
        //         }
        //     }
        //     return -1;
        // }
    }
//leetcode submit region end(Prohibit modification and deletion)

}