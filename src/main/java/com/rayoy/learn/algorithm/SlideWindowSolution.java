package com.rayoy.learn.algorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 滑动窗口
// 时间复杂度: O(len(s))
// 空间复杂度: O(len(charset))
public class SlideWindowSolution {

    /**
     * 【题目描述】：
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回滑动窗口最大值。
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * <p>
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * <p>
     * 【题目解析】：
     * 利用一个 双端队列，在队列中存储元素在数组中的位置， 并且维持队列的严格递减,，也就说维持队首元素是 最大的 ，
     * 当遍历到一个新元素时, 如果队列里有比当前元素小的，就将其移除队列，以保证队列的递减。当队列元素位置之差大于 k，就将队首元素移除。
     * <p>
     * 【双端队列】（Dqueue）
     * Deque 的含义是 “double ended queue”，即双端队列，它具有队列和栈的性质的数据结构。顾名思义，它是一种前端与后端都支持插入和删除操作的队列。
     * Deque 继承自 Queue（队列），它的直接实现有 ArrayDeque、LinkedList 等。
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //有点坑，题目里都说了数组不为空，且 k > 0。但是看了一下，测试用例里面还是有nums = [], k = 0，所以只好加上这个判断
        if (nums == null || nums.length < k || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //在尾部添加元素，并保证左边元素都比尾部大
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            //在头部移除元素
            if (deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            //输出结果
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return res;
    }

    /**
     * 题目描述
     * <p>
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 题目解析
     * <p>
     * 建立一个256位大小的整型数组 freg ，用来建立字符和其出现位置之间的映射。
     * <p>
     * 维护一个滑动窗口，窗口内的都是没有重复的字符，去尽可能的扩大窗口的大小，窗口不停的向右滑动。
     * <p>
     * （1）如果当前遍历到的字符从未出现过，那么直接扩大右边界；
     * （2）如果当前遍历到的字符出现过，则缩小窗口（左边索引向右移动），然后继续观察当前遍历到的字符；
     * （3）重复（1）（2），直到左边索引无法再移动；
     * （4）维护一个结果res，每次用出现过的窗口大小来更新结果 res，最后返回 res 获取结果。
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        char[] chars = s.toCharArray();
        int l = 0, r = -1; //滑动窗口为s[l...r]
        int res = 0;
        // 整个循环从 l == 0; r == -1 这个空窗口开始
        // 到l == s.size(); r == s.size()-1 这个空窗口截止
        // 在每次循环里逐渐改变窗口, 维护freq, 并记录当前窗口中是否找到了一个新的最优值
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[chars[r + 1]] == 0) {
                r++;
                freq[chars[r]]++;
            } else {   //r已经到头 || freq[s[r+1]] == 1
                freq[chars[l]]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        // maxSlidingWindow
        int[] nums = {1, 2, 3, 4, 5, 6};
        Arrays.stream(maxSlidingWindow(nums, 3)).forEach(System.out::println);

        // lengthOfLongestSubstring
        System.out.println(lengthOfLongestSubstring("abcabcde23434123456"));
    }
}