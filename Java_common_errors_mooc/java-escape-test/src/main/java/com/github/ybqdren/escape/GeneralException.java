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

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Manager extends  User{
        private void process(){};
    }

    public static class Worker extends User{}

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
        // 1.并发修改异常
/*        ArrayList<User> users = new ArrayList<>(
                Arrays.asList(new User("wen"),new User("ybqdren"))
        );
//        concurrentModificationException(users);

        System.out.println(concurrentModificationException(users));*/

        // 2.类型转换异常
        User user1 = new Manager();
        User user2 = new Worker();

        // 正确的：父类强制转换为子类
//        user1 = new User();
        User u = user1;
//        user1 = new Manager();

        // 错误的：不想管的对象进行强制类型转换
//        Manager m2 = (Manager) user2;



    }
}
