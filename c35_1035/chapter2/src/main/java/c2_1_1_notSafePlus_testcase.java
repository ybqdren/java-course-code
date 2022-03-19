import java.util.concurrent.CountDownLatch;

/**
 * <h1> 线程不安全小实验 </h1>
 *
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/3/17
 *
 * 10个线程并行运行，对一个共享数据进行自增运算，每个线程自增运算1000次
 **/
public class c2_1_1_notSafePlus_testcase {
    final static int MAX_TREAD = 10;
    final static int MAX_TURN = 1000;

    public static void testNotSafePlus() throws InterruptedException {
        // 倒数闩，需要倒数 MAX_TREAD 次
        CountDownLatch latch = new CountDownLatch(MAX_TREAD);
        c2_1_1_notSafePlus counter = new c2_1_1_notSafePlus();
        Runnable runnable = () -> {
            for (int i = 0; i < MAX_TURN; i++) {
                counter.salfPlus();
            }
            // 倒数闩减少一次
            latch.countDown();
        };

        for (int i = 0; i < MAX_TREAD; i++) {
            new Thread(runnable).start();
        }

        // 等待倒数闩的次数减少到 0，所有的线程执行完毕
        latch.await();
        System.out.println("理论结果：" + MAX_TURN * MAX_TREAD);
        System.out.println("实际结果：" + counter.getAmount());
        System.out.println("差距：" + (MAX_TURN * MAX_TREAD - counter.getAmount()));
    }

    private Integer amount = 0;

    // 自增
    public void salfPlus() {
        amount++;
    }

    public Integer getAmount() {
        return amount;
    }

    public static void main(String[] args) throws InterruptedException {
        testNotSafePlus();
    }
}
