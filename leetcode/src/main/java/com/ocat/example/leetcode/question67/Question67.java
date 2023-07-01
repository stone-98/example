package com.ocat.example.leetcode.question67;

/**
 * @author shikui@tiduyun.com
 * @date 2023/5/12
 */
public class Question67 {

    public static void main(String[] args) {
        String value = addBinary("10100111", "100");
        System.out.println(value);
    }


    private static String addBinary(String a, String b){
        StringBuilder re = new StringBuilder();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; i++) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            re.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            re.append('1');
        }

        return re.reverse().toString();
    }
}