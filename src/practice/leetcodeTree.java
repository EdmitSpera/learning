package practice;

import java.util.ArrayDeque;

public class leetcodeTree {

    public static void main(String[] args) {
        // 构建根节点
        TreeNode root = new TreeNode(1);
        // 构建第二层节点
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // 构建第三层节点
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        leetcodeTree main = new leetcodeTree();
        main.levelOrderOutput(root);
        System.out.println();

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static ArrayDeque<Integer> queue = new ArrayDeque<>();
    // 前序遍历
    public void levelOrderOutput(TreeNode root) {
        // 结束条件
        if(root.left == null || root.right == null) return;
        // 首次条件
        if(queue.isEmpty())
            queue.offer(root.val);

        // 下一层
        queue.offer(root.left.val);
        queue.offer(root.right.val);
        levelOrderOutput(root.left);
        levelOrderOutput(root.right);
    }
}
