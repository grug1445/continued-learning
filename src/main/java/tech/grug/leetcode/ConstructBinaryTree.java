package tech.grug.leetcode;

/**
 * Created by feichen on 2018/6/11.
 */
public class ConstructBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return new TreeNode(1);
    }


}
