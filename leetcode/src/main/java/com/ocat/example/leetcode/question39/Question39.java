package com.ocat.example.leetcode.question39;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author stone-98
 * @date 2023/8/14
 */
class Question39 {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }
    
    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
    
        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
        
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);
        
            // 状态重置
            path.removeLast();
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[] {1, 2, 3, 4, 5, 6}, 13);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.printf(integer + "\t");
            }
            System.out.printf("\n");
        }
    }
}