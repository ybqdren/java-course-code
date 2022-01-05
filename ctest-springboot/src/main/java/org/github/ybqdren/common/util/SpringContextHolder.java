package org.github.ybqdren.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.security.auth.callback.Callback;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/5 14:47
 * @package org.github.ybqdren.common.util
 * @description
 **/
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static final List<Callback>

    private static boolean addCallback = true;


    @SuppressWarnings("unchecked")
    public static <T> T  getBean(String name){
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext属性未注入, 请在applicationContext" +
                    ".xml中定义SpringContextHolder或在SpringBoot启动类中注册SpringContextHolder.");
        }

        return applicationContext.getBean()


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
            for(Callback callback : SpringContextHolder){

            }
        }

    }
}
