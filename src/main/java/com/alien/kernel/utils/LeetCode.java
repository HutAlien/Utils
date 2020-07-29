package com.alien.kernel.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

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
     * 合并两个有序链表
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

    static class TreeNode {
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

    /**
     * N叉树的最大深度
     *
     * @param
     * @return
     */
    public int maxDepth1(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (int i = 0; i < root.children.size(); i++) {
            depth = Math.max(depth, maxDepth1(root.children.get(i)));
        }
        return depth + 1;
    }

    //多叉树
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    //相同的树
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

    /**
     * 二进制相加 （用数组存储两个数和，之后对每个数字进行判断，若大于1则对2取余数，数组的下一个数加一(进位)）
     *
     * @param
     * @return 二进制数
     */
    public static String addBinary(String a, String b) {
        String[] arrA = a.split("");
        String[] arrB = b.split("");
        int[] num = new int[arrA.length > arrB.length ? arrA.length : arrB.length];
        int num1[] = new int[num.length + 1];
        for (int i = arrA.length - 1, j = 0; i >= 0; i--, j++) {
            num[j] = Integer.valueOf(arrA[i]);
        }
        for (int i = arrB.length - 1, j = 0; i >= 0; i--, j++) { //0101 1101  1202
            num[j] += Integer.valueOf(arrB[i]);
        }
        boolean flag = false;
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 1) {
                if (i == num.length - 1) {
                    num[num.length - 1] %= 2;
                    System.arraycopy(num, 0, num1, 0, num.length);
                    num1[num1.length - 1] = 1;
                    flag = true;
                    break;
                }
                num[i] %= 2;
                num[i + 1] += 1;
            }
        }
        String s = "";
        if (flag) {
            for (int i = num1.length - 1; i >= 0; i--) {
                s += String.valueOf(num1[i]);
            }
        } else {
            for (int i = num.length - 1; i >= 0; i--) {
                s += String.valueOf(num[i]);
            }
        }
        return s;
    }

    /**
     * Excel列表序号 NO.171
     *
     * @param
     * @return
     */
    public static int titleToNumber(String s) {
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            int sum = s.charAt(i) - 'A' + 1;
            number = number * 26 + sum;
        }
        return number;
    }

    /**
     * Excel列表名称
     *
     * @param
     * @return
     */
    public static String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while (n != 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n = n / 26;
        }
        return sb.reverse().toString();
    }

    /**
     * 判断链表是否有环
     * <p>
     * 思路：1.可每次判断链表节点是否被访问过进而得知是否有环
     * 2.定义两个指针(慢指针 每次步长为 1，快指针 每次步长为 2) 比较引用节点。
     *
     * @param
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 删除链表中的元素
     *
     * @param
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode header = node;
        while (header.next != null) {
            if (header.next.val == val) {
                header.next = header.next.next;
            } else {
                header = header.next;
            }
        }
        return header.next;
    }

    /**
     * 链表的中间结点
     * <p>
     * 思路：1 将链表存入数组，通过返回数组的size/2 获取中间结点
     * 2 定义两个指针(慢指针 每次步长为 1，快指针 每次步长为 2) 当快指针到达链表尾部时候，慢指针刚好到中间
     *
     * @param
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 二叉树层次遍历
     *
     * @param
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        return null;
    }

    /**
     * 快乐数 No.202
     *
     * @param
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n != 0) {
                sum += Math.pow(n % 10, 2);
                n = n / 10;
            }
            if (sum == 1) {
                return true;
            } else if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                n = sum;
            }
        }
    }

    /**
     * 队列常用操作:Queue接口扩展在Collection接口上，使用时应尽量避免使用Collection的add()和remove()方法
     * 使用offer()方法加入元素 poll()方法来获取并移除元素 它们的优点是可以通过返回值来判断成功于否，而add和remove方法在失败的时候会抛出异常，
     * 如果要访问而不移除第一个元素：使用element()或peek()方法
     *
     * @param
     * @return //todo
     */

    /**
     * 原地反转字符
     *
     * @param
     * @return
     */
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }

    }

    /**
     * 移动0 No.233 在原数组上操作
     *
     * @param
     * @return
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }

    /**
     * 两数组交集
     *
     * @param
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list1 = new ArrayList<>();
        for (int j : nums2) {
            if (list.contains(j)) {
                list.remove(Integer.valueOf(j));
                list1.add(j);
            }
        }
        int[] target = new int[list1.size()];
        int j = 0;
        for (Integer i : list1) {
            target[j++] = i;
        }
        return target;
    }

    /**
     * 翻转二叉树
     *
     * @param
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;
        return root;
    }

    /**
     * 翻转二叉树
     *
     * @param
     * @return
     */
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    /**
     * 二叉搜索树转换为累加树
     *
     * @param
     * @return
     */
    public static int preNum = 0;

    public static TreeNode convertBST(TreeNode root) { //递归找到最右结点
        unPreOrder(root);
        return root;
    }

    public static void unPreOrder(TreeNode root) {
        if (root == null)
            return;
        unPreOrder(root.right);
        root.val += preNum;
        preNum = root.val;
        unPreOrder(root.left);
    }

    /**
     * 二分查找
     *
     * @param
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int hight = nums.length - 1;
        while (low <= hight) {
            int mid = (hight + low) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                hight = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 原地删除数组中的重复项
     *
     * @param
     * @return 数组项数
     */
    public static int deleteArrayItem(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int temp = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[temp] != nums[i]) {
                temp++;
                nums[temp] = nums[i];
            }
        }
        return temp + 1;
    }

    /**
     * 原地移除数组
     *
     * @param
     * @return 数组项数
     */
    public int removeElement(int[] nums, int val) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[temp++] = nums[i];
            }
        }
        return temp;
    }

    /**
     * 返回绝对值为k的整数对
     *
     * @param
     * @return
     */
    public static int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = Arrays.stream(nums).distinct().boxed().sorted().collect(Collectors.toSet());
        int count = 0;
        for (Integer i : set) {
            for (Integer j : set) {
                if (Math.abs(i - j) == k) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    /**
     * 同构字符串
     *
     * @param
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
                continue;
            }
            if (map.containsValue(t.charAt(i))) {
                return false;
            }
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }

    /**
     * 位 1 的个数
     * 我们遍历数字的 32 位。如果某一位是 1 ，将计数器加一。
     * <p>
     * <<左移    >>右移   >>>无符号右移，空位补0
     *
     * >>>：无符号右移。无论是正数还是负数，高位通通补0。
     * 对于正数而言，>>和>>>没区别。
     * 对于负数而言，-2 >>> 1，结果是2147483647（Integer.MAX_VALUE），-1 >>> 1，结果是2147483647（Integer.MAX_VALUE）。
     *
     * >>：带符号右移。正数右移高位补0，负数右移高位补1。比如：
     * 4 >> 1，结果是2；-4 >> 1，结果是-2。-2 >> 1，结果是-1。
     *
     * @param
     * @return
     */
    public static int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((mask & n) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    /**
     * 两整数之和
     *
     * @param
     * @return
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int res = (a & b) << 1;
            a = a ^ b;
            b = res;
        }
        return a;
    }

    public int reverseBits(int n) {
        return 0;
    }

    public static void main(String[] args) {
       /* ClassLoader classLoader = LeetCode.class.getClassLoader();//获取leetcode的类加载器
        System.out.println(classLoader);
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        */
        //System.out.println(getSum(1, 1));
        System.out.println();
    }
}
