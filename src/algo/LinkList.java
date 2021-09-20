package algo;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class LinkList {

    // 61
    public ListNode rotateRight(ListNode head, int k) {

        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        int count = 0;
        while (fast.next != null) {
            count++;
            fast = fast.next;
        }
        for (int i=count-k%count;i>0;i--) {
            slow = slow.next;
        }
        fast.next = dummy.next;
        ListNode res = slow.next;
        slow.next = null;
        return res;

    }

    // 83
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return null;
        ListNode curr = head, next = head.next;
        int pre = curr.val;

        while (next!=null) {
            if (pre!= next.val) {
                curr.next = next;
                pre = next.val;
                curr = curr.next;
            }
            next = next.next;
        }
        curr.next = null;
        return head;
    }

    // 82
    public ListNode deleteDuplicates2(ListNode head) {
        if (head==null) return head;
        ListNode dummy = new ListNode(200);
        dummy.next = head;
        ListNode fast = head, slow = dummy;
        while (fast!=null) {
            while (fast.next!=null&&fast.val==fast.next.val) {
                fast = fast.next;
            }
            if (slow.next!=fast) {
                slow.next = fast.next;
                fast = fast.next;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return dummy.next;
    }

    // 138
    public Node copyRandomList(Node head) {
        if (head==null) return head;
        Node curr = head, next;
        while (curr!=null) {
            Node copyNode = new Node(curr.val);
            copyNode.next = curr.next;
            copyNode.random = curr.random;
            curr.next = copyNode;
            curr = curr.next.next;
        }
        curr = head;
        while (curr!=null) {
            if (curr.random!=null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node copy = head.next;
        Node copyHead = copy;
        while (copy.next!=null) {
            curr.next = curr.next.next;
            curr = curr.next;

            copy.next = copy.next.next;
            copy = copy.next;
        }
        curr.next = curr.next.next;
        return copyHead;

    }

    // 143
    public void reorderList(ListNode head) {
        if (head.next==null) return;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next!=null&&fast.next.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rev = reverseLink(slow.next);
        slow.next = null;
        ListNode curr = head;
        while (curr!=null&&rev!=null) {
            ListNode temp1 = curr.next;
            ListNode temp2 = rev.next;
            curr.next = rev;
            rev.next = temp1;
            curr = temp1;
            rev = temp2;
        }
    }

    public ListNode reverseLink(ListNode head) {
        if (head.next==null) return head;
        ListNode last = reverseLink(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverseLink2(ListNode head) {
        ListNode pre = new ListNode();
        ListNode curr = head;
        while (curr!=null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        LinkList ln = new LinkList();
        ln.rotateRight(new ListNode(1, new ListNode(2)), 2);
    }

}
