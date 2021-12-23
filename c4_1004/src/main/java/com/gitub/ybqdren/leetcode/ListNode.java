package com.gitub.ybqdren.leetcode;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/


public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }


     // 生成链表
     // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
     public ListNode(int[] arr){
          if(arr == null || arr.length ==0){
               throw  new IllegalArgumentException("arr can not be empty");
          }

          this.val = arr[0]; // 获取第一个元素
          ListNode cur = this;
          for(int i=1 ; i<arr.length; i++){  // 将每个元素接在上一个节点的后面
               cur.next = new ListNode(arr[i]);
               cur = cur.next;
          }
     }

     // 以当前节点为头结点的链表信息字符串
     @Override
     public String toString() {
          StringBuilder res = new StringBuilder();
          ListNode cur = this;
          while(cur != null){
               res.append(cur.val + "->");
               cur = cur.next;
          }
          res.append("NULL");
          return res.toString();
     }
}
