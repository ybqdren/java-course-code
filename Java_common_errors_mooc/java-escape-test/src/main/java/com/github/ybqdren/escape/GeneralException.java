package com.github.ybqdren.escape;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/12
 * <h1>编码中常见的异常</h1>
 */

@SuppressWarnings("all")
public class GeneralException {
    public static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // 直接使用for循环会触发并发修改异常
//    private static void concurrentModificationException(ArrayList<User> users){
    private static List concurrentModificationException(ArrayList<User> users){

/*        for(User user:users){
            if(user.getName().equals("ybqdren")){
                users.remove(user);
            }
        }*/

/*        // 使用迭代器则不会触发并发修改异常
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()){
            User user = iter.next(); // 一定在remove之前调用 如果在其之后调用同样会报并发修改异常
            if(user.getName().equals("ybqdren")){
                iter.remove();
            }
        }*/

        // 使用过滤器不会触发并发修改异常
        Stream userStream = users.stream().filter(item -> item.equals(null));

        List<User> userList = (ArrayList<User>) userStream.collect(Collectors.toList());

        return userList;
    }



    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>(
                Arrays.asList(new User("wen"),new User("ybqdren"))
        );
//        concurrentModificationException(users);

        System.out.println(concurrentModificationException(users));
    }
}
