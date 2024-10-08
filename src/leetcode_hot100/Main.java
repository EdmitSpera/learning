package leetcode_hot100;

import java.util.*;

public class Main {

    // 测试用例
    public static void main(String[] args) {
        Main main = new Main();

        // 创建一个未排序的链表
        ListNode head = new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))));


        // 调用sortList方法进行排序
        ListNode sortedHead = main.sortList(head);

        System.out.println();
    }

    public ListNode sortList(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        quickSort(arr, 0, arr.length - 1);

        ListNode dummyNode = new ListNode(0, new ListNode(arr[0], null));
        ListNode cur = dummyNode.next;

        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i], null);
            cur = cur.next;
        }

        return dummyNode.next;
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = randowQuick(arr, left, right);

            quickSort(arr, 0, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    public int randowQuick(int[] arr, int left, int right) {
        int randowIndex = new Random().nextInt(right - left + 1) + left;
        swap(arr, randowIndex, right);
        return partition(arr, left, right);
    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int j = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, ++j, right);
        return j;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class ListNode {
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
