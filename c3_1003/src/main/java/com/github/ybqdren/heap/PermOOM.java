package com.github.ybqdren.heap;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/7 17:16
 * @package com.github.ybqdren.heap
 * @description 永久区溢出 ： 永久区（Perm） 是存放类元数据的区域。如果一个系统定了太多的类型，那么永久区是有可能溢出的。
 *
 * 在 JDK 1.8 中，永久区被一块称为元空间的区域替代。
 *
 * 示例 7-4 如果一个系统不断地产生新的类，而没有回收，那最终非常有可能导致永久区溢出，下面用代码每次循环都生成一个新的类（注意是类，而不是对象实例）
 * p209
 **/
public class PermOOM {
    public static void main(String[] args) {
        try {
            for(int i=0 ; i<100000 ;i++){

            }
        }catch (Error e){
            e.printStackTrace();
        }
    }
}
