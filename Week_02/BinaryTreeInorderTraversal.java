//给定一个二叉树，返回它的中序 遍历。 
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 581 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.*;

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
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
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode(int x) {
//            val = x;
//        }
//    }

    class Solution {
        //迭代的方式
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Deque<TreeNode> deque = new LinkedList<>();
            TreeNode curr = root;
            while(curr != null || !deque.isEmpty()){
                while(curr != null){
                    deque.push(curr);
                    curr = curr.left;
                }
                TreeNode node = deque.pop();
                result.add(node.val);
                curr = node.right;
            }
            return result;
        }

        //递归的方式
//        public List<Integer> inorderTraversal(TreeNode root) {
//            List<Integer> result = new ArrayList();
//            inorderTraversal(root,result);
//            return result;
//        }
//
//        private void inorderTraversal(TreeNode root,List<Integer> result){
//            if(root == null){
//                return;
//            }
//            inorderTraversal(root.left,result);
//            result.add(root.val);
//            inorderTraversal(root.right,result);
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}