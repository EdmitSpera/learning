package leetcode_hot100;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        int[] height = {1,8,6,2,5,4,8,3,7};

    }

    public String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        // 初始化 need，记录 t 中每个字符的出现次数
        for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int left = 0, right = 0; // 窗口的左右边界
        int valid = 0; // 已经匹配上的字符数量
        int start = 0, minLen = Integer.MAX_VALUE; // 最小窗口的起始位置和长度

        while (right < s.length()) {
            char r = s.charAt(right);
            right++;

            // 更新窗口内字符的计数
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) valid++;
            }

            // 当窗口内的字符已经完全包含了 t 中的所有字符时
            while (valid == need.size()) {
                // 更新最小窗口的起始位置和长度
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }

                char l = s.charAt(left);
                // 缩小窗口，移动左边界
                if (need.containsKey(l)) {
                    window.put(l, window.get(l) - 1);
                    if (window.get(l) < need.get(l)) valid--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
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
