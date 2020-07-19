//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 312 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        //迭代的方式
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Deque<TreeNode> deque = new LinkedList<>();
            deque.push(root);
            while (!deque.isEmpty()) {
                TreeNode node = deque.pop();
                if (node == null) {
                    continue;
                }
                result.add(node.val);
                deque.push(node.right);
                deque.push(node.left);
            }
            return result;
        }

        //递归
//        public List<Integer> preorderTraversal(TreeNode root) {
//            List<Integer> result = new ArrayList<>();
//            preorderTraversal(root, result);
//            return result;
//        }
//
//        private void preorderTraversal(TreeNode node, List<Integer> result) {
//            if (node == null) {
//                return;
//            }
//
//            result.add(node.val);
//            preorderTraversal(node.left, result);
//            preorderTraversal(node.right, result);
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}