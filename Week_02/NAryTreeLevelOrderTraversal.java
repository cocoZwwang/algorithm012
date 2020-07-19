//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 98 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.*;

public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

// Definition for a Node.
//class Node {
//    public int val;
//    public List<Node> children;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, List<Node> _children) {
//        val = _val;
//        children = _children;
//    }
//};


    class Solution {

        //利用队列来实现
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root == null){
                return result;
            }
            Deque<Node> deque = new LinkedList<>();
            deque.offer(root);
            while (!deque.isEmpty()){
                int size = deque.size();
                List<Integer> vals = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node node = deque.poll();
                    vals.add(node.val);
                    deque.addAll(node.children);
                }
                result.add(vals);
            }
            return result;
        }

        //ArrayList来实现
//        public List<List<Integer>> levelOrder(Node root) {
//            List<List<Integer>> result = new ArrayList<>();
//            if (root == null) {
//                return result;
//            }
//
//            List<Node> preLayoutList = Arrays.asList(root);
//            while (!preLayoutList.isEmpty()) {
//                List<Node> currLayoutList = new ArrayList<>();
//                List<Integer> vals = new ArrayList<>();
//                for (Node node : preLayoutList) {
//                    vals.add(node.val);
//                    if (node.children != null) {
//                        currLayoutList.addAll(node.children);
//                    }
//                }
//                result.add(vals);
//                preLayoutList = currLayoutList;
//            }
//            return result;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}