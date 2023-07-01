package com.ocat.example.leetcode.question67.question2;


import java.util.List;

public class Question2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + ", next=" + next + '}';
        }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        int sum = l1.val + l2.val;
        ListNode head = new ListNode(sum % 10);
        head.next = addTwoNumbers(l1.next, l2.next);
        if(sum > 9) head.next = addTwoNumbers(head.next, new ListNode(1));
        return head;
    }
    
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        Question2 question2 = new Question2();
        System.out.println(question2.addTwoNumbers(l1, l2));
        
    }
    
}
