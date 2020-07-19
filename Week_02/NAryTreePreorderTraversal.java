//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 89 👎 0


package com.shuzijun.leetcode.editor.en;

import java.util.*;

public class NAryTreePreorderTraversal{
  public static void main(String[] args) {
       Solution solution = new NAryTreePreorderTraversal().new Solution();
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
    public List<Integer> preorder(Node root) {
        if(root == null){
            return Collections.emptyList();
        }
        Deque<Node> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        deque.push(root);
        while (!deque.isEmpty()){
            Node node = deque.pop();
            //前序遍历，首先读取根节点的值
            result.add(node.val);
            //从右边开始把儿子节点压进栈里面，因为栈是后进先出
            //所以最后面进去的最左边儿子，会最早出栈
            //符合前序遍历 根-左-右 的顺序
            if(node.children != null && node.children.size() > 0){
                for (int i = node.children.size() - 1;i >= 0; i--){
                    deque.push(node.children.get(i));
                }
            }
        }
        return result;
    }

    //使用递归的方式
//    public List<Integer> preorder(Node root) {
//        List<Integer> result = new ArrayList<>();
//        preorder(root,result);
//        return result;
//    }
//
//    private void preorder(Node root, List<Integer> result){
//        if(root == null){
//            return;
//        }
//        result.add(root.val);
//        for(Node node : root.children){
//            preorder(node,result);
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}