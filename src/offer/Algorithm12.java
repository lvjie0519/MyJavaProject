package offer;

import java.util.Stack;

/**
 * 二叉树 前中后序
 *
 *              A
 *          B        C
 *        D   E    F   G
 *         H   I  J K
 *
 *  前序： A B D H E I C F J K G
 *  中序： D H B E I A J F K C G
 *  后序： H D I E B J K F G C A
 */
public class Algorithm12 {

    public static void main(String args[]) {


    }

    /**
     * 前序 递归
     * @param treeNode
     */
    public static void preOrderTraverse1(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        System.out.println(treeNode.val);
        preOrderTraverse1(treeNode.left);
        preOrderTraverse1(treeNode.right);
    }

    /**
     * 前序 非递归
     * @param root
     */
    public static void preOrderTraverse2(TreeNode root){
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode tempNode = root;

        while (tempNode != null || !stack.empty()){
            if(tempNode != null){
                System.out.println(tempNode.val);
                stack.push(tempNode);
                tempNode = tempNode.left;
            }else{
                tempNode = stack.pop().right;
            }
        }

    }

    /**
     * 中序 递归
     * @param root
     */
    public static void midOrderTraverse1(TreeNode root){
        if(root == null){
            return;
        }
        midOrderTraverse1(root.left);
        System.out.println(root.val);
        midOrderTraverse1(root.right);
    }

    /**
     * 中序 非递归
     * @param root
     */
    public static void midOrderTraverse2(TreeNode root){
        if(root == null){
            return;
        }

    }




    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
