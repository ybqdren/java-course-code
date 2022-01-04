package org.github.ybqdren.common.bean;

import org.github.ybqdren.common.annotation.PermissionMeta;
import org.github.ybqdren.common.annotation.PermissionModule;
import org.github.ybqdren.common.enumeration.UserLevel;
import org.github.ybqdren.common.util.AnnotationUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 12:01
 * @package org.github.ybqdren.common.bean
 * @description
 **/
public class PermissionMetaCollector implements BeanPostProcessor {

    // <key: 方法，value:metaInfo>
    private Map<String,MetaInfo> metaMap = new ConcurrentHashMap<>();

    // <key: 类名称，value:metaInfo>
    private Map<String,Map<String , Set<String>>> structuralMeta = new ConcurrentHashMap<>();

    // 扫描注解信息，并提取
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("开始提取注解");
//        System.out.println("bean :"+bean+"~~"+beanName);

        Controller controllerAnnotation = AnnotationUtils.findAnnotation(bean.getClass(),Controller.class);
        if(controllerAnnotation == null){ return bean; }

        // 获取 springboot 中所有 bean 对象
        Method[] metheds = ReflectionUtils.getAllDeclaredMethods(bean.getClass());

        for(Method method : metheds){

            PermissionMeta permissionMeta = AnnotationUtils.findAnnotation(method,PermissionMeta.class);

            if(permissionMeta != null && permissionMeta.mount()){
                System.out.println("找到一个 PermissionMeta 注解: " + permissionMeta.value());

                String permission = StringUtils.isEmpty(permissionMeta.value())
                                            ? permissionMeta.permission() : permissionMeta.value();
                UserLevel level = AnnotationUtil.findRequired(method.getAnnotations());

                this.putOneMetaInfo(method,permission,permissionMeta.module(),level);
            }
        }

        return bean;
    }

    /**
     *
     * @param method 发现注解的 bean
     * @param permission permission.value
     * @param module 暂不知 但是是一个布尔值的值
     * @param userLevel 用户的等级，对应的就是用户的权限
     */
    private void putOneMetaInfo(Method method,String permission,String module,UserLevel userLevel) {
        if(StringUtils.isEmpty(module)){
            PermissionModule permissionModule = AnnotationUtils.findAnnotation(
                    method.getDeclaringClass(),PermissionModule.class
            );

            if(permissionModule != null){
                module = StringUtils.isEmpty(permissionModule.value())
                            ? method.getDeclaringClass().getName() : permissionModule.value();
            }
        }

        // get metainfo
        String methodName = method.getName();

        String className = method.getDeclaringClass().getName();

        String identity = className + "#" + methodName;

        MetaInfo metaInfo = new MetaInfo(permission,module,identity,userLevel);

        metaMap.put(identity,metaInfo);

        this.putMetaIntoStrUcturalMeta(identity,metaInfo);

    }

    /**
     *
     * @param identity  类全路径 # 方法
     * @param metaInfo 上一个方法中拼接的内容
     */
    private void putMetaIntoStrUcturalMeta(String identity, MetaInfo metaInfo) {
        String module = metaInfo.getModule();

        String permission = metaInfo.getPermission();

        // 判断当前的 module 是否已经存在
        if(structuralMeta.containsKey(module)){

        }else{
            Map<String, Set<String>> moduleMap = new HashMap<>();
            this.putIntoModuleMap(moduleMap,identity,permission);
        }

    }


    /**
     * 将参数封装进 map
     * @param moduleMap 目标 map
     * @param identity value
     * @param permission key
     */
    private void putIntoModuleMap(Map<String, Set<String>> moduleMap, String identity, String permission) {
        if(moduleMap.containsKey(permission)){
            moduleMap.get(permission).add(identity);
        }else{
            Set<String> eps = new HashSet<>();
            eps.add(identity);
            moduleMap.put(permission,eps);
        }
    }
}
