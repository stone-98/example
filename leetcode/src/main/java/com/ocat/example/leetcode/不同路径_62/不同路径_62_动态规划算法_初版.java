package com.ocat.example.leetcode.不同路径_62;

import org.junit.Test;

/**
 * <pre>
 *     * * * * * * *
 *     * * * * * * *
 *     * * * * * * *
 * </pre>
 * @author stone-98
 * @date 2023/8/17
 */
public class 不同路径_62_动态规划算法_初版 {
    
    /**
     * 计算出有多少路径
     *
     * @param m 行
     * @param n 列
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化第一列
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 初始化第一行
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 遍历数组，算出每一个点的路径数，行和列都从1开始算，因为边已经在上面初始化完成了
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
    
    @Test
    public void main() {
        System.out.println(uniquePaths(3, 7));
    }
    
}