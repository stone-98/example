package com.ocat.example.leetcode.question67.question1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author stone-98
 * @date 2023/6/30
 */
public class Question1 {

    public int[] twoSum(int[] nums, int target) {
        // key为指，value为索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;
            if (map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }
            map.put(num, i);
        }
        return null;
    }

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        int[] arr = question1.twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}