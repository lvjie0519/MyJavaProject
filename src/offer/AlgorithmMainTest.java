package offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lvjie on 2019/10/26.
 */
public class AlgorithmMainTest {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String args[]) {
        Solution solution = new Solution();
        solution.push(1);
        System.out.println(solution.pop());

        solution.push(2);
        solution.push(3);
        System.out.println(solution.pop());

        solution.push(4);
        solution.push(5);
        System.out.println(solution.pop());
    }


    public static class Solution {
        // in
        Stack<Integer> stack1 = new Stack<Integer>();
        // out
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if(stack2.empty()){
                while (!stack1.empty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }


    /*****************   二叉树前序和中序  返回二叉树  ******************/
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        TreeNode root=new TreeNode(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }

}
