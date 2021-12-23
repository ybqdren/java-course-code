package com.gitub.ybqdren.leetcode;

import java.util.Stack;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/7
 */
public class Solution {
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }

                char topChar = stack.pop();
                if(c == ')' && topChar != '('){
                    return false;
                }

                if(c == ']' && topChar != '['){
                    return false;
                }

                if(c == '}' && topChar != '{'){
                    return false;
                }
            }
        }

        // 检查栈里面是否还有字符，如果不为空，匹配还是失败的
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String tmpStr = "()[]{}";
        isValid(tmpStr);
    }
}
