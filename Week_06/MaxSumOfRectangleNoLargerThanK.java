//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。 
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 
// 👍 114 👎 0


package com.shuzijun.leetcode.editor.en;
public class MaxSumOfRectangleNoLargerThanK{
  public static void main(String[] args) {
       Solution solution = new MaxSumOfRectangleNoLargerThanK().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      public int maxSumSubmatrix(int[][] matrix, int k) {
          int rowCount = matrix.length;
          int colCount = matrix[0].length;


          int max = Integer.MIN_VALUE;
          for(int i1 = 1; i1 <= rowCount; i1++){
              for(int j1 = 1; j1 <= colCount; j1++){
                  int[][] da = new int[rowCount + 1][colCount + 1];
                  da[i1][j1] = matrix[i1 - 1][j1 - 1];
                  for(int i2 = i1; i2 <= rowCount; i2++){
                      for(int j2 = j1; j2 <= colCount; j2++){
                          da[i2][j2] = da[i2 - 1][j2] + da[i2][j2 - 1] - da[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                          if(da[i2][j2]  == k){
                              return da[i2][j2];
                          }else if(da[i2][j2] < k && da[i2][j2] > max){
                              max = da[i2][j2];
                          }
                      }
                  }
              }
          }

          return max;
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}