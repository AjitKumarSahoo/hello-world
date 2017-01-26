package ProgQue.Practice;

import ProgQue.LeetCode.ListNode;

/**
 * Author: Ajit Ku. Sahoo
 * Date: 11/3/2016.
 */
public class TwoSum {


    /*private ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode p1 = l1, p2 = l2;
        int count1 =0, count2 = 0;
        while(p1 != null) {
            p1 = p1.next;
            count1++;
        }

        while(p2 != null) {
            p2 = p2.next;
            count2++;
        }

        ListNode head = add(l1, count1, l2, count2);
        if (head.next != null && head.next.val > 9) {
            head.next.val %= 10;
            head.val++;
        }
        if (head.val > 9) {
            ListNode node = new ListNode(1);
            head.val %= 10;
            node.next = head;
            head = node;
        }
        return head;
    }

    private ListNode add(ListNode l1, int count1, ListNode l2, int count2) {
        ListNode node;
        if(count1 > count2) {
            node = add(l1.next, count1 - 1, l2, count2);
        } else if(count1 < count2) {
            node = add(l1, count1, l2.next, count2 - 1);
        } else {
            node = add(l1, l2);
        }

        if (node.next != null && node.next.val > 9) {
            node.next.val %= 10;
            if (count1 > count2) {
                l1.val++;
            } else if (count2 > count1) {
                l2.val++;
            } else {
                node.val++;
            }
        }
        if (count1 > count2) {
            l1.next = node;
            node = l1;
        } else if (count2 > count1) {
            l2.next = node;
            node = l2;
        }

        return node;
    }

    private ListNode add(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode nextNode = add(l1.next, l2.next);
        ListNode node = new ListNode(l1.val + l2.val);

        if(nextNode != null && nextNode.val > 9) {
            nextNode.val %= 10;
            node.val++;
        }
        node.next = nextNode;

        return node;
    }*/


    public static void main(String[] args) {
        ListNode l1 = getLinkedList(new int[]{9, 3, 6, 9, 2});
        ListNode l2 = getLinkedList(new int[]{9, 7, 9, 6, 4});
        ListNode head = new TwoSum().addTwoNumbers(l1, l2);
//        ListNode head = new TwoSum().reverse(l1);
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print(" ");
            }
        }
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode rev1 = reverse(l1);
        ListNode rev2 = reverse(l2);

        ListNode p1 = rev1;
        ListNode p2 = rev2;
        ListNode result = null, head = null;
        int carry = 0, sum;
        while (rev1 != null && rev2 != null) {
            sum = rev1.val + rev2.val + carry;
            ListNode node = new ListNode((sum) % 10);
            carry = (sum) / 10;
            if (result == null) {
                head = result = node;
            } else {
                result.next = node;
                result = node;
            }
            rev1 = rev1.next;
            rev2 = rev2.next;
        }

        if (rev1 != null) {
            join(result, rev1, carry);
        }

        if (rev2 != null) {
            join(result, rev2, carry);
        }

        if (carry != 0) {
            result.next = new ListNode(carry);
        }

        head = reverse(head);

        return head;
    }

    private void join(ListNode result, ListNode rev, int carry) {
        while (rev != null) {
            int sum = rev.val + carry;
            carry = (sum) / 10;
            ListNode node = new ListNode((sum) % 10);

            result.next = node;
            result = node;

            rev = rev.next;
        }

        if (carry != 0) {
            result.next = new ListNode(carry);
        }
    }

    public static ListNode getLinkedList(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            temp.next = node;
            temp = node;
        }
        return head;
    }

    private ListNode reverse(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode temp = null, prev = null, curr = head;

        while (curr != null) {
            temp = prev;
            prev = curr;
            curr = curr.next;
            prev.next = temp;
        }

//        prev.next = temp;
        return prev;
    }
}
