package com.gitub.ybqdren.linkedlist;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class Main {
    public static void main(String[] args) {
        LinkedList_dummyHead<Integer> linkedList = new LinkedList_dummyHead<>();
        for(int i=0 ; i<5 ;i++){
            linkedList.addFirst(i);
            System.out.print(linkedList.toString());
        }

        linkedList.add(2,666);
        System.out.print(linkedList);

        linkedList.remove(2);
        System.out.print(linkedList);

        linkedList.removeFirst();
        System.out.print(linkedList);

        linkedList.removeLast();
        System.out.print(linkedList);
    }
}
