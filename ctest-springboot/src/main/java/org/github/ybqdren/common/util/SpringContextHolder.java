package org.github.ybqdren.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/5 14:47
 * @package org.github.ybqdren.common.util
 * @description
 **/
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static final List<CallBack> CALL_BACKS = new ArrayList<>();

    private static boolean addCallback = true;


    public static <T> T getBean(Class<T> requiredType){
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }

        return (T) applicationContext.getBean(requiredType);
    }

    /**
     * 从静态变量 applicaitonContext 中取 Bean，强制转型为所赋值对象的类型
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T  getBean(String name){
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }

        return (T) applicationContext.getBean(name);


    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextHolder.applicationContext != null) {
            System.out.println("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext;
        if(addCallback){
            for(CallBack callback : SpringContextHolder.CALL_BACKS){
                callback.excutor();
            }

            CALL_BACKS.clear();
        }

        SpringContextHolder.addCallback = true;

    }
}
