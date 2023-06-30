package com.ocat.example.leetcode.question67.question2490;

/**
 * @author shikui@tiduyun.com
 * @date 2023/6/30
 */
class Solution2490 {
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(sentence.length() - 1) != sentence.charAt(0)) {
            return false;
        }
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ' && sentence.charAt(i + 1) != sentence.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution2490 solution = new Solution2490();
        System.out.println(solution.isCircularSentence("a a ba"));
    }
}