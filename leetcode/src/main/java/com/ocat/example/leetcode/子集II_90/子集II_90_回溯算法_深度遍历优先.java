package com.ocat.example.leetcode.子集II_90;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author stone-98
 * @date 2023/8/16
 */
public class 子集II_90_回溯算法_深度遍历优先 {
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 如果nums为空，直接返回空
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> outputs = new ArrayList<>();
        for (int num : nums) {
            outputs.add(num);
        }
        
        // 每一个循环代表判断长度为多少的结果
        for (int i = 0; i < nums.length + 1; i++) {
            backtrack(i, outputs, res, 0);
        }
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
            res.add(new ArrayList<>(output.subList(0, length)));
        }
        for (int i = first; i < output.size(); i++) {
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
        int[] nums = new int[] {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(nums);
        for (List<Integer> line : res) {
            for (Integer i : line) {
                System.out.printf(i + "\t");
            }
            System.out.printf("\n");
        }
    }
}