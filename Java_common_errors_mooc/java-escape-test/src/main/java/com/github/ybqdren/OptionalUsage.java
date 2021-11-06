package com.github.ybqdren;

import java.util.Optional;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/6
 */

@SuppressWarnings("all")
public class OptionalUsage {

    public static class User{
        private String name;

        public String getName(){
            return name;
        }
    }

    private static User anoymos_2(){
        System.out.println("User is null");
        return new User();
    }

    // 对常见方法进行修改
    private static void isUserEqualNull_2_1(){
        User user = null;
        Optional<User> optionalUser = Optional.ofNullable(user);

//        optionalUser.
        //optionalUser.map(u -> {System.out.println("User is not null");return u;}).orElseGet(() -> anoymos_2());
        //optionalUser.ifPresent(u -> System.out.println("User is not null"));
    }

    // 常见的一种判空方法
    private static void isUserEqualNull_2(){
        User user = null;

        if(user != null){
            System.out.println("User is not null");
        }else{
            System.out.println("User is null");
        }
    }


    // 下面这种判空的方法和jdk8之前的判空方法（类似 isUserEqualNull_2）没有任何区别，所以不应该这样使用
    // 与直接判断是否是NULL几乎一样，所以，使用新的API意义不大
    private static void isUserEqualNull(){
        // Java8 的特性 如果jdk版本>8 需要将项目语言版本设置为java 8
        Optional<User> optional = Optional.empty();

        if(optional.isPresent()){  // 是否为空，为空返回false  不为空返回true
            System.out.println("User is not null");
        }else{
            System.out.println("User is null");
        }
    }

    private static User anoymos(){
        return new User();
    }

    public static void main(String[] args) {
        // 没有意义的使用方法
        // 什么才是有意义的？ 根据API提供的方法特性 正确合理的使用API
/*        isUserEqualNull();

        User user = null;

        // 使用.ofNullable() 如果为空就返回一个空的Optoinal实例 如果不为空就返回User的示例
        Optional<User> optionalUser = Optional.ofNullable(user);

        // 存在即返回传入的对象，空则提供默认值
        optionalUser.orElse(new User());

        // 存在即返回，空则由函数区产生 此方法复用性更强
        optionalUser.orElseGet(() -> anoymos());

        // 存在即返回，否则抛出异常
        optionalUser.orElseThrow(RuntimeException::new);

        // 存在才去做相应的处理
        optionalUser.ifPresent(u -> System.out.println(u.getName()));

        // map可以对Optional中的对象执行某种操作，且会返回一个Optional对象
        // map 获取optionalUser的name属性 获取到之后被map方法封装为一个optional对象
        // 获取到的optional对象是一个string类型 这个string类型的optional有可能也是空
        // 对这个对象使用orElse()方法进行判定，如果为空就返回默认值"anymos"
        optionalUser.map(u -> u.getName()).orElse("anymos");

        // map是可以无限级联操作的
        optionalUser.map(u->u.getName()).map(name->name.length()).orElse(0);*/

        User user = new User();
        isUserEqualNull_2_1();
    }
}
