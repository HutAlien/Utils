package com.alien.kernel.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Auther: FengYunJun
 * @Date: 2019/1/29 10:18
 * @Description:
 */
@Slf4j
public class LeetCode {
    /**
     * 整数反转 溢出返回0
     *
     * @param
     * @return
     */

    public int reverse(int x) {
        int k = 0;
        while (x != 0) {
            int i = x % 10;
            if (k > Integer.MAX_VALUE / 10 || (k == Integer.MAX_VALUE / 10 && i > 7)) {
                return 0;
            }
            if (k < Integer.MIN_VALUE / 10 || (k == Integer.MIN_VALUE / 10 && i < -8)) {
                return 0;
            }
            k = k * 10 + i;
            x = x / 10;
        }
        return k;
    }

    /**
     * 字符串反转
     *
     * @param
     * @return
     */

    public static String isPalindrome(String x) {
        if (x.length() == 0) {
            return "";
        }
        return x.substring(x.length() - 1) + isPalindrome(x.substring(0, x.length() - 1));
    }

    /**
     * 最长字符串公共前缀
     *
     * @param
     * @return
     */

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    /**
     * 合并连个有序链表
     *
     * @param
     * @return
     */

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        while (l1 != null && l2 != null) {    //2 3 4 5 6
            if (l1.val > l2.val) {
                listNode.next = l2;
                l2 = l2.next;
                listNode = listNode.next;
            } else {
                listNode.next = l1;
                l1 = l1.next;
                listNode = listNode.next;
            }
        }
        if (l1 != null) {
            listNode.next = l1;
        }
        if (l2 != null) {
            listNode.next = l2;
        }
        return head.next;
    }

    /**
     * 加一 NO 66
     *
     * @param
     * @return
     */

    public static int[] plusOne(int[] digits) {
        int[] a = new int[digits.length + 1];
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                break;
            } else {
                digits[i] = 0;
                if (i == 0) {
                    System.arraycopy(digits, 0, a, 1, digits.length);
                    a[0] = 1;
                    return a;
                }
            }
        }
        return digits;
    }

    /**
     * 跳台阶问题（递归）
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int[] fn = new int[n + 3];
        fn[1] = 1;
        fn[2] = 2;
        for (int i = 3; i <= n; i++) {
            fn[i] = fn[i - 1] + fn[i - 2];
        }
        return fn[n];
    }

    /**
     * 合并两个有序的数组，合并后有序 88
     *
     * @param
     * @return
     */

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            nums1[i--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (m >= 0) {
            nums1[i--] = nums1[m--];
        }
        while (n >= 0) {
            nums1[i--] = nums2[n--];
        }
        return nums1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉树的最大深度
     *
     * @param
     * @return
     */

    public int maxDepth(TreeNode root) {
        if (root != null) {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
        return 0;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p.val == q.val && q != null && p != null) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }




    public static void main(String[] args) {
        int[] target = merge(new int[]{1, 3, 5, 0, 0, 0, 0, 0}, 3, new int[]{2, 4, 6, 8}, 4);
        Arrays.stream(target).forEach((o) -> System.out.println(o));
        //
    }
}
