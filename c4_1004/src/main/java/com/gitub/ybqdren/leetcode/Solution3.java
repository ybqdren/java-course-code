package com.gitub.ybqdren.leetcode;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/15
 */
public class Solution3 {

    /**
     * 完成1个递归算法：
     * 1.最基本的情况（位置规模最小）
     * 2.要完成的功能
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 1.最基本的情况（位置规模最小）：整个链表为空
       if(head == null) {return null;}

        // 从head.next，找到val
        ListNode res = removeElements(head.next,val);
       // 如果head.val == val 就返回head后面的链表
        if(head.val == val){
            return res;
        }else{
            head.next = res; // 将要删除元素后面的链表接在head后面
            return head; // 返回head，也相当于返回整个链表
        }
    }



    public static void main(String[] args) {
        int nums[] = {1,1,3,3,5,6,6,4,23,2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
    }
}
