//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 805 👎 0


package com.shuzijun.leetcode.editor.en;
public class Permutations{
  public static void main(String[] args) {
       Solution solution = new Permutations().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      public List<List<Integer>> permute(int[] nums) {
          List<List<Integer>> result = new ArrayList<>();
          Set<Integer> set = new HashSet<>();
          permute(nums,set,result,new ArrayList<>());
          return result;
      }

      private void permute(int[] nums,Set<Integer> set,List<List<Integer>> result,List<Integer> list){
          //终结条件
          if(list.size() == nums.length){
              result.add(new ArrayList<>(list));
              return;
          }
          for(int i = 0; i < nums.length; i++){
              //处理逻辑
              if(set.contains(i)){
                  continue;
              }
              list.add(nums[i]);
              set.add(i);
              permute(nums,set,result,list);
              list.remove(list.size() - 1);
              set.remove(i);
          }
      }
}
//leetcode submit region end(Prohibit modification and deletion)

}