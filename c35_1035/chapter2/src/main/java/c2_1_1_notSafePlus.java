/**
 * <h1> 线程不安全小实验 </h1>
 *
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/3/17
 **/
public class c2_1_1_notSafePlus {
    private Integer amount = 0;
    // 自增
    public void salfPlus(){
        amount ++;
    }

    public Integer getAmount(){
        return amount;
    }
}
