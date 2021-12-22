package com.github.ybqdren.escape;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/12
 * <h1>异常处理</h1>
 */
public class ExceptionProcess {
    private static class User{}

    /**
     * <h2>Java 异常本质 -- 抛出异常 </h2>
     */
    private void throwException() {
        User user = null;
        // ...
        if (null == user) {
            throw new NullPointerException();
            /** 方法出现异常的本质：抛出异常
             * 当出现了异常 并且这个异常不允许你向下执行时，要么你就在这里处理它，要么你就抛出它丢给调用当前方法的调用方

             调用方法出现异常本质：捕获异常
             寻找异常处理器，当抛出的异常和异常处理器相符时，就代表着找到了合适的异常处理器。

             异常捕获的终点是main函数
             **/
        }
    }

    /**
     * <h2>不能捕获的异常</h2>
     */
    private void canNotCatchNpeException(){
        try{
            throwException();   // 抛出一个空指针异常 NullPointException
        }catch (ClassCastException cce){ // 只能捕获 ClassCastException
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }
    }

    /**
     * <h2>能够捕获异常</h2>
     */
    private void canCatchNpeException(){
        try{
            throwException();   // 抛出一个空指针异常 NullPointException
        }catch (ClassCastException cce){ // 只能捕获 ClassCastException
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }catch (NullPointerException npe){ // 只能捕获 NullPointerException
            System.out.println(npe.getMessage());
            System.out.println(npe.getClass().getName());
        }
    }

    public static void main(String[] args) {
        ExceptionProcess process = new ExceptionProcess();
//        process.canCatchNpeException();
        process.canNotCatchNpeException();
    }

}
