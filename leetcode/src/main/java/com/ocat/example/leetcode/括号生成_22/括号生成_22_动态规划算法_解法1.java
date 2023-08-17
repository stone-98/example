package com.ocat.example.leetcode.括号生成_22;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 22.数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * 示例一：
 *      输入：n = 3
 *      输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例二：
 *      输入：n = 1
 *      输出：["()"]
 * 动态规划解题思路：
 *      dp[n]:表示n对括号生成的有效括号的组合
 * 递推计算公式：
 *      dp[n] = "(" + dp[m] + ")" + dp[k]
 * 其中:
 *      m + k = n -1
 *
 * 那么我们可以推算出：
 *  for (int m = 0; m < n; m++) {
 *      int k = n - 1 -m;
 *      List<String> str1 = dp[m];
 *      List<String> str2 = dp[k];
 *      for (String s1 : str1) {
 *          for (String s2 : str2) {
 *              cur.add("(" + s1 + ")" + s2);
 *          }
 *      }
 *  }
 *
 * </pre>
 *
 * @author stone-98
 * @date 2023/8/16
 */
public class 括号生成_22_动态规划算法_解法1 {
    
    public List<String> generateParenthesis(int n) {
        // 用于存储所有个数的括号的所有可能
        List<String>[] dp = new ArrayList[n + 1];
        // 初始化dp[0]，因为dp[0]是确认的，没有括号就是空字符串
        dp[0] = initDp0();
        // 从1开始算，算出所有可能性
        for (int i = 1; i <= n; i++) {
            // 这个cur集合用于存储每一个可能性
            List<String> cur = new ArrayList<>();
            for (int m = 0; m < i; m++) {
                int k = i - 1 - m;
                List<String> str1 = dp[m];
                List<String> str2 = dp[k];
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp[i] = cur;
        }
        return dp[n];
    }
    
    private List<String> initDp0() {
        return Collections.singletonList("");
    }
    
    @Test
    public void main() {
        List<String> list = generateParenthesis(4);
        for (String s : list) {
            System.out.println(s);
        }
    }
}