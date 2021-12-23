package com.gitub.ybqdren.leetcode;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 对链表中的元素进行删除操作
 **/

class Solution_203 {
    public ListNode removeElements(ListNode head, int val) {

        // 把链表开始部分的节点删除  待删除的元素可能存在多个，所以需要使用循环
        // 在leetcode提交可以不用考虑将被删除的节置为空，只需要排除出链表就可
        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode = null;   // 将要删除的节点指向 null
        }


        if(head == null){
            return null;
        }

        // 把链表中所有的节点都遍历了一遍
        ListNode prev = head;
        while(prev.next != null){  // 还没有遍历到最后一个节点
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return head;
    }



    // 使用虚拟头结点
    public ListNode removeElements_2(ListNode head, int val) {

        // 创建一个虚拟头结点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 把链表开始部分的节点删除  待删除的元素可能存在多个，所以需要使用循环
        // 在leetcode提交可以不用考虑将被删除的节置为空，只需要排除出链表就可
        // 每一个元素都有前置节点，所以可以不执行这段逻辑
/*        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode = null;   // 将要删除的节点指向 null
        }


        if(head == null){
            return null;
        }*/

        // 把链表中所有的节点都遍历了一遍
        ListNode prev = dummyHead;
        while(prev.next != null){  // 还没有遍历到最后一个节点
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }

        return dummyHead.next;   // 返回真正的head节点
    }


    public static void main(String[] args) {
        int nums[] = {1,1,3,3,5,6,6,4,23,2};
        ListNode head = new ListNode(nums);
        System.out.println(head);
    }
}
