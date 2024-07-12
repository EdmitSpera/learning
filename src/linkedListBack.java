public class linkedListBack {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        linkedListBack linkedListBack = new linkedListBack();
        Node head = linkedListBack.init(new Node(0));
        Node reverse = linkedListBack.reverse(head);
        while(reverse != null) {
            System.out.print(reverse.val +" -> ");
            reverse = reverse.next;
        }
        System.out.println();
    }

    public Node init(Node head) {
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(5);
        return head;
    }

    public Node reverse(Node head) {
        // 双指针  O(n)
        Node pre = null;
        Node cur = head;
        Node temp;

        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
