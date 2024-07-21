package leetcode_hot100;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.addStrings("123", "456"));  // 输出 "579"
        System.out.println(main.addStrings("999", "1"));    // 输出 "1000"
    }

    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry != 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = n1 + n2 + carry;
            carry = sum / 10;
            result.append(sum % 10);

            i--;
            j--;
        }

        return result.reverse().toString();
    }



    public ListNode reverseList(ListNode node){
        ListNode pre = null;
        ListNode cur = node;
        ListNode next;

        while(cur != null){
            next = cur.next;

            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public boolean checkBounding(ListNode node,int k){
        for(int i = 0; i < k; i++){
            if(node == null){
                return false;
            }
            node = node.next;
        }

        return true;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
