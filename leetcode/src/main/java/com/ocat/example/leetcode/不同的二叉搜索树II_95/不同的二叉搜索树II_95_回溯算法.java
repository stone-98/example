package com.ocat.example.leetcode.不同的二叉搜索树II_95;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author stone-98
 * @date 2023/8/18
 */
public class 不同的二叉搜索树II_95_回溯算法 {
    
    /**
     * 生成搜索二叉树
     *
     * @param n 节点数量
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }
    
    /**
     * 返回从start到end生成的可行的搜索二叉树
     *
     * @param start 开始值
     * @param end   结束值
     */
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // i是根节点
        for (int i = start; i <= end; i++){
            // 左子树
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 右子树
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftTree;
                    treeNode.right = rightTree;
                    allTrees.add(treeNode);
                }
            }
        }
        return allTrees;
    }
    
    @Test
    public void main() {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }
}