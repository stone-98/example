package com.ocat.example.leetcode.全排列_46;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这是一个回溯算法，采用了深度遍历优先
 *
 * @author stone-98
 * @date 2023/8/15
 */
public class 全排列_46_回溯算法_深度遍历优先 {
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 如果nums为空，直接返回空
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        
        int length = nums.length;
        backtrack(length, output, res, 0);
        return res;
    }
    
    
    /**
     * 回溯算法
     *
     * @param length
     * @param output
     * @param res
     * @param first
     */
    public void backtrack(int length, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数据都填完了，在res中添加这个组合
        if (first == length) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < length; i++) {
            // 填完当前元素
            Collections.swap(output, first, i);
            // 填下一个元素
            backtrack(length, output, res, first + 1);
            // 去除状态
            Collections.swap(output, first, i);
        }
        
    }
    
    
    @Test
    public void main() {
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> res = permute(nums);
        for (List<Integer> line : res) {
            for (Integer i : line) {
                System.out.printf(i + "\t");
            }
            System.out.printf("\n");
        }
    }
    
}