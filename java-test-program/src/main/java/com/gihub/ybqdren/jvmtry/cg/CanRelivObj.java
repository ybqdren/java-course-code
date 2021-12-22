package com.gihub.ybqdren.jvmtry.cg;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description r1 示例 4-1  (p69~p71)
 *
 *
 * @output
 * CanReliveObj finalize called
 * obj 可用
 * 第2次 gc
 * obj 是 null
 **/
public class CanRelivObj {
    public static CanRelivObj obj;

    // finalize() 是一个非常糟糕的模式，所以不推荐使用这个方法来释放资源   p71
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanRelivObj();
        obj = null;   // 将 obj 设置为 null
        System.gc(); // 进行 GC 操作后，发现 obj 对象被复活了
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
        System.out.println("第2次 gc");
        obj = null; // 再次释放对象引用
        System.gc(); // 进行 GC 操作，这里的 obj 对象才被真正的回收掉
        Thread.sleep(1000);
        if(obj == null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}
