import java.util.ArrayList;
import java.util.List;

public class LeetCode24 {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode point = head;
        list.add(point);
        while (point.next != null) {
            list.add(point.next);
            point = point.next;
        }
        for (int i = 0; i + k - 1 < list.size(); i += k) {
            int left = i;
            int right = i + k - 1;
            while (left < right) {
                ListNode temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }
        for (int i = 0; i + 1 < list.size(); i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.getLast().next = null;
        return list.get(0);
    }
}
