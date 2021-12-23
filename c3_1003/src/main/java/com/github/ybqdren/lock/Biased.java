package com.github.ybqdren.lock;

import java.util.List;
import java.util.Vector;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/23 16:12
 * @package com.github.ybqdren.lock
 * @description
 * 使用参数：
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay = 0 -client -Xmx512m -Xms512m
 * 其中，-XX:BiasedLockingStartupDelay 表示虚拟机在启动后，立即启用偏向锁，如果不设置该参数，虚拟机默认在启动后 4s 后，才启用偏向锁
 *
 * 使用偏向锁之后的性能提升，在作者的测试中，使用偏向锁简化锁的处理流程，可以获得大约 20 % 左右的性能提升
 *
 * 偏向锁在少竞争的情况下，对系统性能有一定帮助；在大量竞争的情况下会导致持有锁的线程不停地切换，所也很难一直保持在偏向模式，
 * 此时，使用所偏向不仅得不到性能的优化，反而有可能降低系统性能，所以在竞争激烈的场合，可以尝试使用 jvm 参数禁用偏向锁。
 **/
public class Biased {
    // 对 Vector 的访问，其内部都是用同步锁控制，故每次 add() 操作都会请求 numberList 对象的锁
    public static List<Integer> numberList = new Vector<>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startNum = 0;
        while (count < 10000000){
            numberList.add(startNum);
            startNum += 2;
            count++;
        }

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
