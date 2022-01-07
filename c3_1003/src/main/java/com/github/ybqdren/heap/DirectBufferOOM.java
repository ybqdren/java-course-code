package com.github.ybqdren.heap;

import java.nio.ByteBuffer;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 10:20
 * @package com.github.ybqdren.heap
 * @description 不断地申请直接内存，并最终可能导致内存溢出（OOM）
 *
 * -XX:+PrintGCDetails
 *
 * 在 64位机器上使用 jdk17 的时候没有溢出，在书中使用的是 JDK1.7U40 32 位 JDK，这是因为 32 位计算机系统对应用程序的可用最大内存有限制。
 *
 *
 * ** 直接内存不一定能够触发 GC **（除非直接内存使用量达到了 -XX:MaxDirectMemorySize 的设置），
 * 所以保证直接内存不溢出的方法是合理地进行 Full GC 的执行，
 *
 * 1. 或者设定一个系统实际可达的 -XX:MaxDirectMemorySize 值（默认情况下等于 -Xmx 的设置）。
 * 因此，如果系统的堆内存少有 GC 发生，而直接内存申请频繁，会比较容易导致直接内存溢出（这个问题在32位机器上尤为明显）。
 *
 * 2. 使 GC 显式生效，那么程序可以正常结束，最大可用直接内存等于 -Xmx 的值。  -Xmx512m -XX:+PrintGCDetails
 *                                // 这里将最大堆限制在 512 MB，而非 1 GB，在这种情况下，最大可用直接内存也为 512 MB，操作系统可以同时为堆和
 *                                 // 直接内存提供足够的空间，当直接内存使用量达到 512 MB 时，也会进行 GC 释放无用内存空间。
 *
 * 3. 此外，显示设置 -XX: MaxDirectMemorySize 也是解决这一问题的方法。
 * 只要设置一个系统实际可达的最大直接内存值，那么像这种实际上不应该触发的内存溢出就不会发生
 *
 *
 * 综上所述，为避免直接内存溢出，在确保空间不浪费的基础上，合理得执行显式 GC，可以降低直接内存溢出的概率，设置合理的 -XX:MaxDirectMemorySize
 * 也可以避免意外的内存溢出发生，而设置一个较小的堆在32位虚拟机上可以使得更多的内存用于直接内存
 **/
public class DirectBufferOOM {
    public static void main(String[] args) {
        for(int i=0 ; i < 1024 ;i++){
            ByteBuffer.allocateDirect(1024 * 1024 * 1024);
            System.out.println(i);
//            System.gc();     // 使 GC 显式生效，那么程序可以正常结束，最大可用直接内存等于 -Xmx 的值。  -Xmx512m -XX:+PrintGCDetails
                               // 这里将最大堆限制在 512 MB，而非 1 GB，在这种情况下，最大可用直接内存也为 512 MB，操作系统可以同时为堆和
                                // 直接内存提供足够的空间，当直接内存使用量达到 512 MB 时，也会进行 GC 释放无用内存空间。
        }
    }
}
