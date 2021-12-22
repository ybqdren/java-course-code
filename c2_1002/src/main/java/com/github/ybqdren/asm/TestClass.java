package org.github.ybqdren.asm;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description
 **/
public class TestClass {
    public void m1(){
        System.out.println("now in method m1 ----->");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
