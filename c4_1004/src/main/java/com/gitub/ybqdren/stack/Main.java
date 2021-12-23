package com.gitub.ybqdren.stack;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        // 入栈测试
        for(int i=0; i<5 ; i++){
            stack.push(i);
            System.out.println(stack);
        }

        //  出栈测试
        stack.pop();
        System.out.print(stack);
    }
}
