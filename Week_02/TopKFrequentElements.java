//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 402 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            //key: nums[i]  value: count;
            Map<Integer, Integer> map = new HashMap<>();
            //存储Key，小根堆
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) ->
                    map.get(o1) - map.get(o2));
            for (int n : nums) {
                int count = map.getOrDefault(n, 0);
                map.put(n, ++count);
            }

            for (Integer key : map.keySet()) {
                if (priorityQueue.size() == k) {
                    //这里很容易写成(map.get(key) < priorityQueue.peek()
                    if (map.get(key) < map.get(priorityQueue.peek())) {
                        continue;
                    }
                    priorityQueue.poll();
                }
                priorityQueue.add(key);
            }

            int size = priorityQueue.size();
            int[] result = new int[size];
            for (int i = 0; i < size; i++) {
                result[i] = priorityQueue.poll();
            }
            //题目不要求返回的数组的顺序，不需要反转
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}